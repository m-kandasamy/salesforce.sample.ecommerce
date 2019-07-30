package mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import feign.Response;
import mk.cloud.spring.salesforce.sample.ecommerce.service.api.client.SalesforceClient;
import mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.SalesforceApiService;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.RecordCount;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResult;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResultRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResultRecordList;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.composite.SalesforceCompositeRequest;
import mk.cloud.spring.salesforce.sample.ecommerce.util.reader.DataReader;

public class SalesforceApiServiceImpl<T> implements SalesforceApiService<T> {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private DataReader dataReader;
	
	private String type;
	
	private Class<T> model;
	
	public SalesforceApiServiceImpl(String type, Class<T> model){
		this.type = type;
		this.model = model;
	}
	
	@Autowired
	private SalesforceClient salesforceClient;
	
	public Object composite(String url, SalesforceCompositeRequest salesforceCompositeRequest) {
		Response response = salesforceClient.composite(URI.create(url), salesforceCompositeRequest);
		return dataReader.readRecord(Object.class, response);
	}
	
	@Override
	public SalesforceResult create(String url, T newObject) {
		Response response = salesforceClient.create(URI.create(url), type, newObject);
		return dataReader.readRecord(SalesforceResult.class, response);
	}
	
	@Override
	public SalesforceResultRecord<T> get(String url, String key) {
		Response response = salesforceClient.get(URI.create(url), type, key);
		return dataReader.readRecord(model, response);
	}
	
	@Override
	public SalesforceResultRecordList<T> list(String url, String query) {
		Map<String, String> params = new HashMap<>();
		params.put("q", query);
		Response response = salesforceClient.list(URI.create(url), params);
		return dataReader.readRecordList(model, response);
	}
	
	@Override
	public SalesforceResultRecord<T> listById(String url, String query) {
		Map<String, String> params = new HashMap<>();
		params.put("q", query);
		Response response = salesforceClient.list(URI.create(url), params);
		SalesforceResultRecordList<T> records = dataReader.readRecordList(model, response);
		
		SalesforceResultRecord<T> record = new SalesforceResultRecord<T>(); 
		record.setStatus(records.getStatus());
		record.setErrors(records.getErrors());
		if(!records.getRecords().isEmpty())
			record.setRecord(records.getRecords().get(0));
		
		return record;
		
	}
	
	@Override
	public SalesforceResult update(String url, String key, T updates) {
		Response response = salesforceClient.update(URI.create(url), type, key, updates);
		return dataReader.readResult(response);
		
	}

	@Override
	public SalesforceResult delete(String url, String key) {
		Response response = salesforceClient.delete(URI.create(url), type, key);
		return dataReader.readResult(response);
	}
	
	@Override
	public SalesforceResultRecord<RecordCount> count(String url) {
		Map<String, String> params = new HashMap<>();
		params.put("q", "SELECT COUNT(Name), MIN(Name), MAX(Name) from " + type);
		Response response = salesforceClient.list(URI.create(url), params);
		SalesforceResultRecordList<RecordCount> records = 
				dataReader.readRecordList(RecordCount.class, response);
		
		SalesforceResultRecord<RecordCount> record = new SalesforceResultRecord<RecordCount>(); 
		
		record.setStatus(records.getStatus());
		record.setErrors(records.getErrors());
		record.setRecord(records.getRecords().get(0));
		
		return record;
	}
}