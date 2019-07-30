package mk.cloud.spring.salesforce.sample.ecommerce.service.api.service;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.RecordCount;

import java.net.URI;

import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResult;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResultRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResultRecordList;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.composite.SalesforceCompositeRequest;

public interface SalesforceApiService<T> {
	
	SalesforceResult create(String url, T newObject);
	
	SalesforceResultRecord<T> get(String url, String key);
	
	SalesforceResultRecordList<T> list(String url, String query);
	
	SalesforceResultRecord<T> listById(String url, String query);
	
	SalesforceResultRecord<RecordCount> count(String url);
	
	SalesforceResult update(String url, String key, T updates);
	
	SalesforceResult delete(String url, String key);
	
	Object composite(String url, SalesforceCompositeRequest salesforceCompositeRequest);
	
}