package mk.cloud.spring.salesforce.sample.ecommerce.util.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class EcommerceVariables {

	private String username;
	private String password;
	private String authUrl;
	private String apiUrl;
	private String itemApiUrl;
	private String recipientApiUrl;
	private String orderApiUrl;
	private String tokenExpiry;

}