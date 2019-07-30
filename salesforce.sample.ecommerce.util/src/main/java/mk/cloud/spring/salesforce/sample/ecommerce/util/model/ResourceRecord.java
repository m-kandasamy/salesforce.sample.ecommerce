package mk.cloud.spring.salesforce.sample.ecommerce.util.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResult;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ResourceRecord<T> {
	
	private boolean success;
	private T record;
	
	@JsonProperty("info")
	private SalesforceResult salesforceResult;

}
