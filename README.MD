### Spring Boot Using diferent kind of request and JDBC 

#### Simple run these commands or import this project using your favorite IDE

```sh
sh mvnw clean install
sh mvnw spring-boot:run
```
Url: http://localhost:8099/api/todos


- http://localhost:8099/api/send1

```java
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
```

- http://localhost:8099/api/send2

```java
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
```

- http://localhost:8099/api/send3

```java
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
```

- http://localhost:8099/api/send4

```java
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
```

- http://localhost:8099/api/send5

```java
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
```


- http://localhost:8099/api/send6

```java
    @RequestMapping(value = "/send6", method = RequestMethod.POST)
    public void send6(@RequestParam("name") String name, @RequestParam("lastName") String lastName) {
        logger.info("send6 name: "+name);
        logger.info("send6 lastName: "+lastName);
    }
```


- http://localhost:8099/api/send7

```java
    @RequestMapping(value = "/send7/{id}", method = RequestMethod.PUT)
    public void send7(@PathVariable("id") Integer detailId,
    		@RequestParam("name") String name, 
    		@RequestParam("lastName") String lastName) {
    	
    	logger.info("send7 id: "+detailId);
        logger.info("send7 name: "+name);
        logger.info("send7 lastName: "+lastName);
    }
```



- http://localhost:8099/api/send8

```java
    @RequestMapping(value = "/send8", method = RequestMethod.GET)
    public void send8(@RequestParam("name") String name, 
    		@RequestParam("lastName") String lastName) {
    	
        logger.info("send8 name: "+name);
        logger.info("send8 lastName: "+lastName);
    }
```


```
- Config for auto init DB
```
spring.datasource.initialize=true
spring.datasource.schema=classpath:/schema.sql
spring.datasource.data=classpath:/data.sql
```