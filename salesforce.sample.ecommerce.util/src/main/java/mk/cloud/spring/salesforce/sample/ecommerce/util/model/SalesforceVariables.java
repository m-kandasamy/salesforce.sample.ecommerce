package mk.cloud.spring.salesforce.sample.ecommerce.util.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SalesforceVariables {

	private String clientId;
	private String clientSecret;
	private String authUrl;
	private String tokenExpiry;

}