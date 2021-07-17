package resources;

//enum is special class in java which contains collection of constant or methods
public enum APIResources {

	addPlaceAPI("/maps/api/place/add/json"),
	deletePlaceAPI("/maps/api/place/delete/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	putPlaceAPI("/maps/api/place/update/json");
	private String resource;

	APIResources(String resource) {
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
}
