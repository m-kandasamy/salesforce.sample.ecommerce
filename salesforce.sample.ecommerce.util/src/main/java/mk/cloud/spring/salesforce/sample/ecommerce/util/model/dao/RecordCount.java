package mk.cloud.spring.salesforce.sample.ecommerce.util.model.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class RecordCount {
	@JsonProperty("expr1")
	private Integer min;
	@JsonProperty("expr2")
	private Integer max;
	@JsonProperty("expr0")
	private Integer total;
}
