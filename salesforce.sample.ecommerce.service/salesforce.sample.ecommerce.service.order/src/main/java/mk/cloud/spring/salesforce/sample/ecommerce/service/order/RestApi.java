package mk.cloud.spring.salesforce.sample.ecommerce.service.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Order;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.RecordCount;

@RestController
@RequestMapping(
		value = "/v1", 
		consumes = "application/json",
		produces = "application/json")
public class RestApi {
	
	@Autowired
	private EcommerceClient ecommerceClient;
	
	@RequestMapping(
			value = "/order/{key}", 
			method = RequestMethod.GET)
	public @ResponseBody ResourceRecord<Order> get(@PathVariable String key) {
		return ecommerceClient.get(key);
	}
	
	@RequestMapping(
			value = "/order", 
			method = RequestMethod.POST)
	public @ResponseBody ResourceRecord<?> create(@RequestBody Order order) {
		return ecommerceClient.create(order);
	}
	
	@RequestMapping(
			value = "/order_count", 
			method = RequestMethod.GET)
	public @ResponseBody ResourceRecord<RecordCount> count() {
		return ecommerceClient.count();
	}

}
