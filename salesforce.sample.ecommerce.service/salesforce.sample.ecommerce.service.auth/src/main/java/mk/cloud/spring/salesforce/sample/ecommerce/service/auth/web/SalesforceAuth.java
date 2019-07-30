package mk.cloud.spring.salesforce.sample.ecommerce.service.auth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mk.cloud.spring.salesforce.sample.ecommerce.service.auth.service.SalesforceAuthService;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.AuthRequest;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.AuthResponse;

@RestController
public class SalesforceAuth {
	
	@Autowired
	private SalesforceAuthService salesforceAuthService;

	@RequestMapping(
			value = "/v1/token", 
			method = RequestMethod.POST, 
			consumes = "application/json",
			produces = "application/json")
	public @ResponseBody ResourceRecord<AuthResponse> token(@RequestBody AuthRequest authRequest) {
		return salesforceAuthService.login(authRequest.getUsername(), authRequest.getPassword());
	}
	
}