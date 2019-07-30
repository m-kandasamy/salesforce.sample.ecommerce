package mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class AuthRequest {
	
	private String username;
	private String password;
	
}
