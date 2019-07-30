package mk.cloud.spring.salesforce.sample.ecommerce.service.api.client;

import feign.Headers;
import feign.RequestLine;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.AuthResponse;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface EcommerceAuthClient {
	
	@RequestLine("POST /v1/token")
	ResourceRecord<AuthResponse> login();
}
