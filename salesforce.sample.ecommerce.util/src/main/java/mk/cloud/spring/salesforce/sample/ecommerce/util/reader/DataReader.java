package mk.cloud.spring.salesforce.sample.ecommerce.util.reader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.AuthError;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceError;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResult;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResultRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResultRecordList;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.AuthResponse;

public class DataReader {
	
	private static final Logger LOGGER = LogManager.getLogger(DataReader.class.getName());
	
	@Autowired
	private ObjectMapper jacksonObjectMapper;
	
	public SalesforceResultRecord<AuthResponse> readAuthRecord(Response response) {
		
		SalesforceResultRecord<AuthResponse> salesforceResultRecord = new SalesforceResultRecord<AuthResponse>();
		
		if(response.status() > 299) 
			salesforceResultRecord.setErrors(readAuthErrors(response));
		
		else {
			JavaType javaType = jacksonObjectMapper.getTypeFactory()
					.constructType(AuthResponse.class);
			salesforceResultRecord.setRecord(read(javaType, response));
		}
		salesforceResultRecord.setStatus(response.status());
		return salesforceResultRecord;
	}
	
	
	
	public <M> SalesforceResultRecord<M> readRecord(Class<M> model, Response response) {
		
		SalesforceResultRecord<M> salesforceResultRecord = new SalesforceResultRecord<M>();
		
		if(response.status() > 299) 
			salesforceResultRecord.setErrors(readErrors(response));
		else {
			JavaType javaType = jacksonObjectMapper.getTypeFactory()
					.constructType(model);
			salesforceResultRecord.setRecord(read(javaType, response));
		}
		salesforceResultRecord.setStatus(response.status());
		return salesforceResultRecord;
	}
	
	public SalesforceResult readResult(Response response) {
		SalesforceResult salesforceResult = new SalesforceResult();
		if(response.status() > 204) 
			salesforceResult.setErrors(readErrors(response));
		salesforceResult.setStatus(response.status());
		return salesforceResult;
	}
	
	public <M> SalesforceResultRecordList<M> readRecordList(Class<M> model, Response response) {
		
		SalesforceResultRecordList<M> salesforceResultRecordList = new SalesforceResultRecordList<M>();
		
		if(response.status() > 299) 
			salesforceResultRecordList.setErrors(readErrors(response));
		else {
			JavaType javaType = jacksonObjectMapper.getTypeFactory()
					.constructParametricType(SalesforceResultRecordList.class, model);
			salesforceResultRecordList = read(javaType, response);
			
		}
		salesforceResultRecordList.setStatus(response.status());
		return salesforceResultRecordList;
	}
	
	private List<SalesforceError> readErrors(Response response) {
		
		JavaType javaType = jacksonObjectMapper.getTypeFactory()
				.constructParametricType(List.class, SalesforceError.class);
		return read(javaType, response);
	}
	
	private List<SalesforceError> readAuthErrors(Response response) {
		
		JavaType javaType = jacksonObjectMapper.getTypeFactory()
				.constructType(AuthError.class);
		AuthError authError = read(javaType, response);
		SalesforceError salesforceError = new SalesforceError(
				authError.getErrorDescription(), authError.getError());
		return Arrays.asList(salesforceError);
	}
	
	private <M> M read(JavaType javaType, Response response) {
		
		try {
			String x = IOUtils.toString(response.body().asInputStream(), "UTF-8");
			M m = jacksonObjectMapper.readValue(x, javaType);
			return m;
		} catch (JsonParseException e) {
			LOGGER.error("Unable to parse the salesforce response");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			LOGGER.error("Unable to map the salesforce response to SalesforceError list");
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error("Unable to read the salesforce response");
			e.printStackTrace();
		}
		return null;
	}
	
	public String write(Object response) {
		
		try {
			return jacksonObjectMapper.writeValueAsString(response);
		} catch (Exception e) {
			LOGGER.error("Unable to write the salesforce response");
			e.printStackTrace();
		}
		return null;
	}
}
