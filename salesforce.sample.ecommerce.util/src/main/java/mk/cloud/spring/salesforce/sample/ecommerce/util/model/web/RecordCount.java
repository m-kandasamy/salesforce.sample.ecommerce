package mk.cloud.spring.salesforce.sample.ecommerce.util.model.web;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class RecordCount {
	private Integer min;
	private Integer max;
	private Integer total;
}
