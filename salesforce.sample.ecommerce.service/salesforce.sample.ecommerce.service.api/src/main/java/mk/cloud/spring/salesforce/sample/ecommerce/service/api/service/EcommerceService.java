package mk.cloud.spring.salesforce.sample.ecommerce.service.api.service;

import org.springframework.util.StringUtils;

import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.RecordCount;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Item;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Order;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Recipient;

public interface EcommerceService {
	
	public ResourceRecord<RecordCount> getItemCount();
	
	public ResourceRecord<RecordCount> getRecipientCount();
	
	public ResourceRecord<RecordCount> getOrderCount();
	
	public ResourceRecord<Item> getItem(String key);
	
	public ResourceRecord<Item> getItemByName(String name);
	
	public ResourceRecord<Recipient> getRecipientByName(String name);
	
	public ResourceRecord<Recipient> getRecipient(String key); 
	
	public ResourceRecord<Order> getOrder(String key); 
	
	public ResourceRecord<?> createOrder(mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Order order);
}
