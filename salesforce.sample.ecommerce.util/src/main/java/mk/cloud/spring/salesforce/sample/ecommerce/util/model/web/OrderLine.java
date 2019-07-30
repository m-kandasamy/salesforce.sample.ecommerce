package mk.cloud.spring.salesforce.sample.ecommerce.util.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class OrderLine {
	
	private String key;
	private Long id;
	private String code;
	private Item item;
	private Double count;
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
