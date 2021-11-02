package io.petstore.api.model.pet;

import com.google.gson.annotations.SerializedName;

public class Category{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}