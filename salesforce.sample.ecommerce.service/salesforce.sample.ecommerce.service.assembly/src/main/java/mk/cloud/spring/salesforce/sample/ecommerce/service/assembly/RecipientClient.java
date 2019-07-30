package mk.cloud.spring.salesforce.sample.ecommerce.service.assembly;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Item;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Recipient;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.RecordCount;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface RecipientClient {
	
	@RequestLine("GET /v1/recipient/{key}")
	ResourceRecord<Recipient> get(@Param("key") String key);
	
	@RequestLine("GET /v1/recipient_count")
	ResourceRecord<RecordCount> count();
	
	@RequestLine("GET /v1/recipient/name/{name}")
	ResourceRecord<Recipient> getByName(@Param("name") String name);
}
