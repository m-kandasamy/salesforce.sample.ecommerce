package mk.cloud.spring.salesforce.sample.ecommerce.service.api.service;

import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.AuthResponse;

public interface EcommerceAuthService {
	
	public ResourceRecord<AuthResponse> login();

}
