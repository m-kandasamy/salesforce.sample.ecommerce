package mk.cloud.spring.salesforce.sample.ecommerce.util.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SalesforceResultRecordList<T> extends SalesforceResult {
	
	private int totalSize;
	private List<T> records = new ArrayList<T>();

}
