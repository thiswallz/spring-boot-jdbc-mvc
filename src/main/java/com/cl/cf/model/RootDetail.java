package com.cl.cf.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="detalles")
public class RootDetail {
	@JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "detalle")
    private List<Detail> items = new ArrayList<Detail>();

	public List<Detail> getItems() {
		return items;
	}

	public void setItems(List<Detail> items) {
		this.items = items;
	}

}
