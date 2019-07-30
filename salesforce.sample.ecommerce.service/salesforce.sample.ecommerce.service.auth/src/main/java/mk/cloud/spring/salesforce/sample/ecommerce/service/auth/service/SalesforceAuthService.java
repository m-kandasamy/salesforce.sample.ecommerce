package mk.cloud.spring.salesforce.sample.ecommerce.service.auth.service;

import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.AuthResponse;

public interface SalesforceAuthService {
	
	ResourceRecord<AuthResponse> login(String username, String password);

}
