package mk.cloud.spring.salesforce.sample.ecommerce.util.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SalesforceResultRecord<T> extends SalesforceResult {

	T record;

}
