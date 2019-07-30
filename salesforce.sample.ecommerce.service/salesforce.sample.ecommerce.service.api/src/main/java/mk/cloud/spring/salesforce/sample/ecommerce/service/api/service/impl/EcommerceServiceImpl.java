package mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.EcommerceAuthService;
import mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.EcommerceService;
import mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.SalesforceApiService;
import mk.cloud.spring.salesforce.sample.ecommerce.util.mapper.DataMapper;
import mk.cloud.spring.salesforce.sample.ecommerce.util.mapper.OrderCompositeGenerator;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResultRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.composite.SalesforceCompositeRequest;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Item;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Order;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.Recipient;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao.RecordCount;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.AuthResponse;
import mk.cloud.spring.salesforce.sample.ecommerce.util.reader.DataReader;

@Service
public class EcommerceServiceImpl implements EcommerceService {
	
	@Autowired
	private EcommerceAuthService ecommerceAuthService;
	
	@Autowired
	private SalesforceApiService<Item> itemSalesforceApiService;
	
	@Autowired
	private SalesforceApiService<Recipient> recipientSalesforceApiService;
	
	@Autowired
	private SalesforceApiService<Order> orderSalesforceApiService;
	
	@Autowired
	private DataMapper dataMapper;
	
	@Autowired
	private OrderCompositeGenerator orderCompositeGenerator;

	@Override
	public ResourceRecord<mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.RecordCount> getItemCount(){
		ResourceRecord<RecordCount> result = executeForRecord(url -> itemSalesforceApiService.count(url));
		return convert(result, f -> dataMapper.daoToWeb(f));
	}
	
	@Override
	public ResourceRecord<mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.RecordCount> getRecipientCount(){
		ResourceRecord<RecordCount> result = executeForRecord(url -> recipientSalesforceApiService.count(url));
		return convert(result, f -> dataMapper.daoToWeb(f));
	}
	
	@Override
	public ResourceRecord<mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.RecordCount> getOrderCount(){
		ResourceRecord<RecordCount> result = executeForRecord(url -> orderSalesforceApiService.count(url));
		return convert(result, f -> dataMapper.daoToWeb(f));
	}
	
	@Override
	public ResourceRecord<mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Item> getItem(String key) {
		ResourceRecord<Item> result = executeForRecord(url -> itemSalesforceApiService.get(url, key));
		return convert(result, f -> {
			mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Item i = 
					dataMapper.daoToWeb(f);
			return i;
		});
	}
	
	@Override
	public ResourceRecord<?> createOrder(mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Order order){
		
		SalesforceCompositeRequest salesforceCompositeRequest = 
				orderCompositeGenerator.generate(order);
		ResourceRecord<?> result = executeForRecord(url -> {
			SalesforceResultRecord<Object> record = new SalesforceResultRecord<>();
			Object object = orderSalesforceApiService.composite(url, salesforceCompositeRequest);
			record.setRecord(object);
			return record;
		});
		
		return result;
	}
	
	@Override
	public ResourceRecord<mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Recipient> getRecipient(String key) {
		ResourceRecord<Recipient> result = executeForRecord(url -> recipientSalesforceApiService.get(url, key));
		return convert(result, f -> dataMapper.daoToWeb(f));
	}

	@Override
	public ResourceRecord<mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Order> getOrder(String key) {
		ResourceRecord<Order> result = executeForRecord(url -> orderSalesforceApiService.listById(
				url, "select Name, (select Name, Id, Code__c, Item__r.Name, Item__r.Id, Item__r.Code__c, "
						+ "Item__r.Description__c, Item__r.Unit__c, Item__r.Price__c, Count__c, Total__c from OrderLines__r), "
						+ "Id, Code__c, Total__c, CreatedDate, Recipient__r.Name, Recipient__r.Id, Recipient__r.Code__c, "
						+ "Recipient__r.FirstName__c, Recipient__r.LastName__c, Recipient__r.DateOfBirth__c, Recipient__r.Email__c from Order__c "
						+ "where Id = '" + key + "' Limit  1"));
		return convert(result, f -> dataMapper.daoToWeb(f));
	}
	
	@Override
	public ResourceRecord<mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Item> getItemByName(String name) {
		ResourceRecord<Item> result = executeForRecord(url -> itemSalesforceApiService.listById(
				url, "select id, Name, Code__c, Description__c, Unit__c, Price__c from Item__c where Name = '" + name + "' limit 1"));
		return convert(result, f -> dataMapper.daoToWeb(f));
	}
	
	@Override
	public ResourceRecord<mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Recipient> getRecipientByName(String name) {
		ResourceRecord<Recipient> result = executeForRecord(url -> recipientSalesforceApiService.listById(
				url, "select id, Name, Code__c, FirstName__c, LastName__c, DateOfBirth__c, Email__c from Recipient__c where Name = '" + name + "' limit 1"));
		return convert(result, f -> dataMapper.daoToWeb(f));
	}
	
	private <T> ResourceRecord<T> executeForRecord(Function<String, SalesforceResultRecord<T>> executor) {
		ResourceRecord<AuthResponse> auth = ecommerceAuthService.login();
		ResourceRecord<T> resourceRecord = new ResourceRecord<T>();
		if(!auth.isSuccess()) {
			resourceRecord.setSuccess(false);
			resourceRecord.setSalesforceResult(auth.getSalesforceResult());
			return resourceRecord;
		}
		AuthResponse authResponse = auth.getRecord();
		SalesforceResultRecord<T> result = executor.apply(authResponse.getInstanceUrl());
		resourceRecord.setSuccess(result.getErrors().isEmpty());
		
		if(resourceRecord.isSuccess()) 
			resourceRecord.setRecord(result.getRecord());
		else
			resourceRecord.setSalesforceResult(result);
		
		return resourceRecord;
	}
	
	private <F, T> ResourceRecord<T> convert(ResourceRecord<F> rf, Function<F, T> converter) {
		ResourceRecord<T> rt = new ResourceRecord<T>();
		rt.setSuccess(rf.isSuccess());
		rt.setSalesforceResult(rf.getSalesforceResult());
		rt.setRecord(converter.apply(rf.getRecord()));
		return rt;
	}
}
