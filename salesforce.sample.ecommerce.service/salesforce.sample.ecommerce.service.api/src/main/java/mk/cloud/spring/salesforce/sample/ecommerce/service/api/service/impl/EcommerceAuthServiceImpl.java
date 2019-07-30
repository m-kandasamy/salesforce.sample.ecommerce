package mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import mk.cloud.spring.salesforce.sample.ecommerce.service.api.client.EcommerceAuthClient;
import mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.EcommerceAuthService;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.AuthResponse;

@Service
public class EcommerceAuthServiceImpl implements EcommerceAuthService {
	
	@Autowired
	private EcommerceAuthClient ecommerceAuthClient;

	@Override
	@Cacheable("ecommerceToken")
	public ResourceRecord<AuthResponse> login() {
		return ecommerceAuthClient.login();
	}

}
