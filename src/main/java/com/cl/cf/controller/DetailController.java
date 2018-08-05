package com.cl.cf.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cl.cf.model.Detail;
import com.cl.cf.model.RootDetail;
import com.cl.cf.service.DetailService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/")
public class DetailController {

	@Autowired
    private DetailService detalleService;

    private static final Logger logger = LoggerFactory.getLogger(DetailController.class);

    @RequestMapping(value = "/todos", produces = MediaType.APPLICATION_XML_VALUE)
    public RootDetail getAll() {
        logger.info("detalle getAll received");
        try {
        	RootDetail root = new RootDetail();
        	root.setItems(detalleService.getAll());
            return root;
        } catch (Exception e) {
            logger.error("Error occurred while trying to process api request", e);
        }

        return null;
    }
    
    /*
    {
	 "_id":  5,
	 "requestTime": 1185937200000
	}
     */
    @RequestMapping(value = "/send1", method = RequestMethod.PUT, 
    		consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public void send1(@RequestBody Detail detalle) {
        logger.info("detalle send1 getId: "+detalle.getId());
        logger.info("detalle send1 getRequestTime: "+detalle.getRequestTime());
    }
    
    /*
    [{
	 "_id":  5,
	 "requestTime": 1185937200000
	},{
	 "_id":  8,
	 "requestTime": 1285937200000
	}]
     */
    @RequestMapping(value = "/send2", method = RequestMethod.POST, 
    		consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public void send2(@RequestBody List<Detail> detalles) {
        logger.info("detalle send2 getIdc(0): "+detalles.get(0).getId());
        logger.info("detalle send2 getIdc(1): "+detalles.get(1).getId());
    }
    
    /*
     {
	   "other":  "Dyn text 1",
	   "other2": "Other property"
	  }
     */
    @RequestMapping(value = "/send3", method = RequestMethod.POST, 
    		consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void send3(@RequestBody String body) throws JSONException {
    	Map<String, String> properties = new HashMap<>();
    	try {
    	    ObjectMapper mapper = new ObjectMapper();
    	    properties = mapper.readValue(body, new TypeReference<Map<String, String>>() {
    	    });
    	 
    	} catch (IOException e) {   
    	    e.printStackTrace();
    	}
        logger.info("detalle send3 other: "+properties.get("other"));
        logger.info("detalle send3 other2: "+properties.get("other2"));
    }
    
    /*
       [
		{
		 "other":  "Dyn text 1",
		 "other2": "Dyn prop 1"
		},
		{
		 "other":  "Dyn text 2",
		 "other2": "Dyn prop 2"
		}
	   ]
     */
    @RequestMapping(value = "/send4", method = RequestMethod.POST, 
    		consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void send4(@RequestBody String body) throws JSONException {
    	List<Map<String, String>> properties = new ArrayList<Map<String, String>>();
    	try {
    	    ObjectMapper mapper = new ObjectMapper();
    		JavaType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Map.class);
    	    properties = mapper.readValue(body, collectionType);
    	 
    	} catch (IOException e) {   
    	    e.printStackTrace();
    	}
        logger.info("send4 other: "+properties.get(0).get("other"));
        logger.info("send4 other2: "+properties.get(1).get("other"));
    }
    
    
    /*
	{
	 "other":  "Dyn text 1",
	 "other2": "Other prop",
	 "mydetail": {
		 "_id":  11,
		 "requestTime": 1185937200000
		}
	}
     */
    @RequestMapping(value = "/send5", method = RequestMethod.POST, 
    		consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void send5(@RequestBody String body) throws JSONException {
    	Map<String, Object> properties = new HashMap<>();
    	try {
    	    ObjectMapper mapper = new ObjectMapper();
    	    properties = mapper.readValue(body, new TypeReference<Map<String, Object>>() {
    	    });
    	    
    	    logger.info("properties.get(\"mydetail\"): " + properties.get("mydetail"));
    	    
    	    Map<String, Object> detalle = (Map<String, Object>) properties.get("mydetail");
    	       	    
	        logger.info("send5 getId: " + detalle.get("_id"));
	        logger.info("send5 getRequestTime: " + detalle.get("requestTime"));
    	 
    	} catch (IOException e) {   
    	    e.printStackTrace();
    	}
 
    }
    
    
    
    @RequestMapping(value = "/send6", method = RequestMethod.POST)
    public void send6(@RequestParam("name") String name, @RequestParam("lastName") String lastName) {
        logger.info("send6 name: "+name);
        logger.info("send6 lastName: "+lastName);
    }
    
    
    @RequestMapping(value = "/send7/{id}", method = RequestMethod.PUT)
    public void send7(@PathVariable("id") Integer detailId,
    		@RequestParam("name") String name, 
    		@RequestParam("lastName") String lastName) {
    	
    	logger.info("send7 id: "+detailId);
        logger.info("send7 name: "+name);
        logger.info("send7 lastName: "+lastName);
    }
    
    
    @RequestMapping(value = "/send8", method = RequestMethod.GET)
    public void send8(@RequestParam("name") String name, 
    		@RequestParam("lastName") String lastName) {
    	
        logger.info("send8 name: "+name);
        logger.info("send8 lastName: "+lastName);
    }
}	