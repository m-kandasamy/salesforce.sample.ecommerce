package mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Item {
	
	@JsonProperty("Id")
	private String key;
	@JsonProperty("Name")
	private Long id;
	@JsonProperty("Code__c")
	private String code;
	@JsonProperty("Description__c")
	private String description;
	@JsonProperty("Unit__c")
	private String unit;
	@JsonProperty("Price__c")
	private Double price;
	
	public static Item create(String key, Long id, String code, String description, String unit, Double price) {
		Item item = new Item();
		item.setId(id);
		item.setKey(key);
		item.setCode(code);
		item.setDescription(description);
		item.setUnit(unit);
		item.setPrice(price);
		return item;
	}
}
