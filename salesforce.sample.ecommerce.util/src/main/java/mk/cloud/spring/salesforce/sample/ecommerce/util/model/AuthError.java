package mk.cloud.spring.salesforce.sample.ecommerce.util.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class AuthError {
	
	@JsonProperty("error")
	private String error;
	@JsonProperty("error_description")
	private String errorDescription;

}
