package io.petstore.api.model.pet;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PetsListResponseItem{

	@SerializedName("photoUrls")
	private List<String> photo_urls;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("category")
	private Category category;

	@SerializedName("tags")
	private List<TagsItem> tags;

	@SerializedName("status")
	private String status;

	public List<String> getPhotoUrls(){
		return photo_urls;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public Category getCategory(){
		return category;
	}

	public List<TagsItem> getTags(){
		return tags;
	}

	public String getStatus(){
		return status;
	}

	public void setPhoto_urls(List<String> photo_urls) {
		this.photo_urls = photo_urls;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setTags(List<TagsItem> tags) {
		this.tags = tags;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}