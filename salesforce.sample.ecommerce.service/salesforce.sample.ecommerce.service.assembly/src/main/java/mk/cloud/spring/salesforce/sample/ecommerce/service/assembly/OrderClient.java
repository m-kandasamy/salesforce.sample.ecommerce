package mk.cloud.spring.salesforce.sample.ecommerce.service.assembly;


import feign.Headers;
import feign.Param;
import feign.RequestLine;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Item;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Order;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.RecordCount;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface OrderClient {
	
	@RequestLine("GET /v1/order/{key}")
	ResourceRecord<Order> get(@Param("key") String key);
	
	@RequestLine("GET /v1/order_count")
	ResourceRecord<RecordCount> count();
	
	@RequestLine("POST /v1/order")
	ResourceRecord<?> create(Order order);
	
}
