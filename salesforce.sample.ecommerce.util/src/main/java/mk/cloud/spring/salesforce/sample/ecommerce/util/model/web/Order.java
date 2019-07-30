package mk.cloud.spring.salesforce.sample.ecommerce.util.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResultRecordList;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Order {
	
	private String key;
	private Long id;
	private String code;
	private Recipient recipient;
	private String createdDate;
	private Double total;
	private SalesforceResultRecordList<OrderLine> orderLines;
}
