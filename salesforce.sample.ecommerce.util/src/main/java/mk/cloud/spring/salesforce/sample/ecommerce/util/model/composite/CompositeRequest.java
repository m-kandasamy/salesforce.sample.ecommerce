
package mk.cloud.spring.salesforce.sample.ecommerce.util.model.composite;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CompositeRequest {

    @JsonProperty("method")
    private String method;
    @JsonProperty("referenceId")
    private String referenceId;
    @JsonProperty("url")
    private String url;
    @JsonProperty("body")
    private Map<String, Object> body = new HashMap<String, Object>();

}
