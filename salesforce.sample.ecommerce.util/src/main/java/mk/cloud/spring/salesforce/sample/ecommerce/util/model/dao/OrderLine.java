package mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class OrderLine {
	
	@JsonProperty("Id")
	private String key;
	@JsonProperty("Name")
	private Long id;
	@JsonProperty("Code__c")
	private String code;
	@JsonProperty("Item__r")	
	private Item item;
	@JsonProperty("Count__c")
	private Double count;
	@JsonProperty("Total__c")
	private Double total;
	
	public static OrderLine create(String key, Long id, String code, Item item, Double count, Double total) {
		OrderLine orderLine = new OrderLine();
		orderLine.setCode(code);
		orderLine.setId(id);
		orderLine.setKey(key);
		orderLine.setItem(item);
		orderLine.setCount(count);
		orderLine.setTotal(total);
		return orderLine;
	}
}
