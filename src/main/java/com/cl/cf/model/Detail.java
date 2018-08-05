package com.cl.cf.model;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Detail {
	
	@JsonProperty("_id")
    private Long id;
	
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date requestTime;

    public Detail() {
    }

    
    
    public Detail(Long id, Date requestTime) {
		super();
		this.id = id;
		this.requestTime = requestTime;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }
}