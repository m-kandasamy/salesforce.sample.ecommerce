package mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.SalesforceResultRecordList;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Order {
	
	@JsonProperty("Id")
	private String key;
	@JsonProperty("Name")
	private Long id;
	@JsonProperty("Code__c")
	private String code;
	@JsonProperty("Recipient__r")
	private Recipient recipient;
	@JsonProperty("CreatedDate")
	private String createdDate;
	@JsonProperty("Total__c")
	private Double total;
	@JsonProperty("OrderLines__r")
	private SalesforceResultRecordList<OrderLine> orderLines;
}
