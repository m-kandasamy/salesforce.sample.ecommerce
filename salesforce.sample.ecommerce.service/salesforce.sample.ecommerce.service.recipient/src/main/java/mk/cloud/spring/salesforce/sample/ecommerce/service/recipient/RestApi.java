package mk.cloud.spring.salesforce.sample.ecommerce.service.recipient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.RecordCount;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Recipient;

@RestController
@RequestMapping(
		value = "/v1", 
		consumes = "application/json",
		produces = "application/json")
public class RestApi {
	
	@Autowired
	private EcommerceClient ecommerceClient;
	
	@RequestMapping(
			value = "/recipient/{key}", 
			method = RequestMethod.GET)
	public @ResponseBody ResourceRecord<Recipient> get(@PathVariable String key) {
		return ecommerceClient.get(key);
	}
	
	@RequestMapping(
			value = "/recipient/name/{name}", 
			method = RequestMethod.GET)
	public @ResponseBody ResourceRecord<Recipient> getByName(@PathVariable String name) {
		return ecommerceClient.getByName(name);
	}
	
	@RequestMapping(
			value = "/recipient_count", 
			method = RequestMethod.GET)
	public @ResponseBody ResourceRecord<RecordCount> count() {
		return ecommerceClient.count();
	}

}
