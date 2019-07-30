package mk.cloud.spring.salesforce.sample.ecommerce.service.item;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Item;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Recipient;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.RecordCount;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface EcommerceClient {
	
	@RequestLine("GET /v1/item/{key}")
	ResourceRecord<Item> get(@Param("key") String key);
	
	@RequestLine("GET /v1/item/name/{name}")
	ResourceRecord<Item> getByName(@Param("name") String name);
	
	@RequestLine("GET /v1/item_count")
	ResourceRecord<RecordCount> count();
}
