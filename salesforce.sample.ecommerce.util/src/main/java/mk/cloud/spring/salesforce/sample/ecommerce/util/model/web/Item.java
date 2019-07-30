package mk.cloud.spring.salesforce.sample.ecommerce.util.model.web;

import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class Item  {
	
	private String key;
	private Long id;
	private String code;
	private String description;
	private String unit;
	private String price;
	
	public static Item create(String key, Long id, String code, String description, String unit, String price) {
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
