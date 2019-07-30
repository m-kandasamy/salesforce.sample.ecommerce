package mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class AuthResponse {
	
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("instance_url")
	private String instanceUrl;
	@JsonProperty("id")
	private String id;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("issued_at")
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Date issuedAt;
	@JsonProperty("signature")
	private String signature;
}
