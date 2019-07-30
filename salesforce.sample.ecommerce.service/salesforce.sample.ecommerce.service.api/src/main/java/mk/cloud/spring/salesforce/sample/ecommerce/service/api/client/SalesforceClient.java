package mk.cloud.spring.salesforce.sample.ecommerce.service.api.client;

import java.net.URI;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;
import mk.cloud.spring.salesforce.sample.ecommerce.util.model.composite.SalesforceCompositeRequest;

public interface SalesforceClient {
	
	@RequestLine("POST /services/data/v20.0/sobjects/{object}")
	@Headers("Content-Type: application/json")
	Response create(URI baseUri, @Param("object") String object, Object newObject);
	
	@RequestLine("GET /services/data/v20.0/sobjects/{object}/{key}")
    Response get(URI baseUri, @Param("object") String object, @Param("key") String key);
	
	@RequestLine("GET /services/data/v20.0/query")
    Response list(URI baseUri, @QueryMap Map<String, String> query);
	
	@RequestLine("PATCH /services/data/v20.0/sobjects/{object}/{key}")
	@Headers("Content-Type: application/json")
	Response update(URI baseUri, @Param("object") String object, @Param("key") String key, Object updates);
	
	@RequestLine("DELETE /services/data/v20.0/sobjects/{object}/{key}")
	Response delete(URI baseUri, @Param("object") String object, @Param("key") String key);
	
	@RequestLine("POST /services/data/v43.0/composite")
	@Headers("Content-Type: application/json")
	Response composite(URI baseUri, SalesforceCompositeRequest salesforceCompositeRequest);
}
