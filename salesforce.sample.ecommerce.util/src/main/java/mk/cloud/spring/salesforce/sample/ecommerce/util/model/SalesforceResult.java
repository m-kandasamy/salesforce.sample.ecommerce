package mk.cloud.spring.salesforce.sample.ecommerce.util.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SalesforceResult {
	
	private int status;
	private List<SalesforceError> errors = new ArrayList<SalesforceError>();
	
}
