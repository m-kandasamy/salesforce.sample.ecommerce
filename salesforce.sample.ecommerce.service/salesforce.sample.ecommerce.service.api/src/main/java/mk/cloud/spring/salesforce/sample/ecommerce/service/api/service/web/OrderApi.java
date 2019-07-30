package mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.EcommerceService;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Order;

@RequestMapping(
		value = "/v1", 
		consumes = "application/json",
		produces = "application/json")
@RestController
public class OrderApi {
	
	@Autowired
	private EcommerceService ecommerceService;
	
	@RequestMapping(
			value = "/order/{key}", 
			method = RequestMethod.GET)
	public @ResponseBody ResourceRecord<Order> get(@PathVariable String key) {
		return ecommerceService.getOrder(key);
	}
	
	@RequestMapping(
			value = "/order_count", 
			method = RequestMethod.GET)
	public @ResponseBody ResourceRecord<?> count() {
		return ecommerceService.getOrderCount();
	}
	
	@RequestMapping(
			value = "/order", 
			method = RequestMethod.POST)
	public @ResponseBody ResourceRecord<?> get(@RequestBody Order order) {
		return ecommerceService.createOrder(order);
	}

}
