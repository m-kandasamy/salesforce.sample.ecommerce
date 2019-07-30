package mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class Recipient {
	
	@JsonProperty("Id")
	private String key;
	@JsonProperty("Name")
	private Long id;
	@JsonProperty("Code__c")
	private String code;
	@JsonProperty("FirstName__c")
	private String firstName;
	@JsonProperty("Lastname__c")
	private String lastName;
	@JsonProperty("DateOfBirth__c")
	private String dateOfBirth;
	@JsonProperty("Email__c")
	private String email;
	
	public static Recipient create(String key, Long id, String code, String firstName, String lastName, String dateOfBirth, String email) {
		Recipient recipient = new Recipient();
		recipient.setId(id);
		recipient.setKey(key);
		recipient.setCode(code);
		recipient.setFirstName(firstName);
		recipient.setLastName(lastName);
		recipient.setDateOfBirth(dateOfBirth);
		recipient.setEmail(email);
		return recipient;
	}
}
