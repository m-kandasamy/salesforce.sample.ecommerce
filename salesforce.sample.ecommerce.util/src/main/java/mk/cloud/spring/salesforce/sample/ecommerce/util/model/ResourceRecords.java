package mk.cloud.spring.salesforce.sample.ecommerce.util.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResult;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ResourceRecords<T> {
	
	private boolean success;
	private List<T> record;
	private SalesforceResult salesforceResult;

}
