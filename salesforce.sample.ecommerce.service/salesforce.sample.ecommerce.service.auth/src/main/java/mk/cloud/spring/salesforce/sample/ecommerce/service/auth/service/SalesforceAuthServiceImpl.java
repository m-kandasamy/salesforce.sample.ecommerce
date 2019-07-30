package mk.cloud.spring.salesforce.sample.ecommerce.service.auth.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import feign.Response;
import mk.cloud.spring.salesforce.sample.ecommerce.service.auth.client.SalesforceAuthClient;
import mk.cloud.spring.salesforce.sample.ecommerce.util.mapper.DataMapper;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResultRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.AuthResponse;
import mk.cloud.spring.salesforce.sample.ecommerce.util.reader.DataReader;

@Service
public class SalesforceAuthServiceImpl implements SalesforceAuthService {
	
	private static final Logger LOGGER = LogManager.getLogger(SalesforceAuthServiceImpl.class.getName());
	
	@Autowired
	private SalesforceAuthClient salesforceAuthClient;
	
	@Autowired
	private DataReader dataReader;
	
	@Autowired
	private DataMapper dataMapper;
	
	public SalesforceAuthServiceImpl() {

	}
	
	
	@Cacheable("salesforceToken")
	public ResourceRecord<AuthResponse> login(String username, String password) {
		
		Response response = salesforceAuthClient.login(username, password);
		
		SalesforceResultRecord<mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.AuthResponse> salesforceResultRecord = 
				dataReader.readAuthRecord(response);
		
		ResourceRecord<AuthResponse> resourceRecord = new ResourceRecord<>();
		
		if(salesforceResultRecord.getErrors().isEmpty()) {
			resourceRecord.setRecord(dataMapper.daoToWeb(salesforceResultRecord.getRecord()));
			resourceRecord.setSuccess(true);
			return resourceRecord;
		}
		
		LOGGER.error(dataReader.write(salesforceResultRecord.getErrors()));
		resourceRecord.setSuccess(false);
		resourceRecord.setSalesforceResult(salesforceResultRecord);
		
		return resourceRecord;
	}
}
