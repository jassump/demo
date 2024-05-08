package com.example.demo.domain.user.entity;

public class Role {


    private Long id;

    private String name;

    public Role(Long id, String name) {
    	this.id = id;
    	this.name = name;
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
