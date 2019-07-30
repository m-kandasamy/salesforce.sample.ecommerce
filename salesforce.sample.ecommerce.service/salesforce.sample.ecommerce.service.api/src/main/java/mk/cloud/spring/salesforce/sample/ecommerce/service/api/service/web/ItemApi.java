package mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mk.cloud.spring.salesforce.sample.ecommerce.service.api.service.EcommerceService;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.ResourceRecord;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.RecordCount;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.web.Item;

@RestController
@RequestMapping(
		value = "/v1", 
		consumes = "application/json",
		produces = "application/json")
public class ItemApi {
	
	@Autowired
	private EcommerceService ecommerceService;
	
	@RequestMapping(
			value = "/item/{key}", 
			method = RequestMethod.GET)
	public @ResponseBody ResourceRecord<Item> get(@PathVariable String key) {
		return ecommerceService.getItem(key);
	}
	
	@RequestMapping(
			value = "/item/name/{name}", 
			method = RequestMethod.GET)
	public @ResponseBody ResourceRecord<Item> getByName(@PathVariable String name) {
		return ecommerceService.getItemByName(name);
	}
	
	@RequestMapping(
			value = "/item_count", 
			method = RequestMethod.GET)
	public @ResponseBody ResourceRecord<RecordCount> count() {
		return ecommerceService.getItemCount();
	}

}
