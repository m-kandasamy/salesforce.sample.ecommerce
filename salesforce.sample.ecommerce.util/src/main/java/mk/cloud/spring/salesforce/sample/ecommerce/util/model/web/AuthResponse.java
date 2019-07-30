package mk.cloud.spring.salesforce.sample.ecommerce.util.model.web;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class AuthResponse {
	
	private String accessToken;
	private String instanceUrl;
	private String id;
	private String tokenType;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date issuedAt;
	private String signature;
}
