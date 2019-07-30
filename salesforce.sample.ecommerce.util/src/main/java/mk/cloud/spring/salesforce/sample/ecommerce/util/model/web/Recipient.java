package mk.cloud.spring.salesforce.sample.ecommerce.util.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class Recipient {
	
	private String key;
	private Long id;
	private String code;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
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
