package io.petstore.api.model.pet;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PetsListResponse{

	@SerializedName("PetsListResponse")
	private List<PetsListResponseItem> pets_list_response;

	public List<PetsListResponseItem> getPetsListResponse(){
		return pets_list_response;
	}
}