package mk.cloud.spring.salesforce.sample.ecommerce.service.auth.client;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

@Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
public interface SalesforceAuthClient {
	
	@RequestLine("POST /oauth2/token")
	@Body("username={username}&password={password}&grant_type=password")
	Response login(
			@Param("username") String username,
			@Param("password") String password);
}