package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GMap;
import pojo.Location;

public class TestDataBuild {
	
	public GMap addPlacePayLoad(String name, String address, String language) {
		GMap gm = new GMap();
		Location l = new Location();
		
		
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		
		List<String> typeList = new ArrayList<String>();
		typeList.add("shoe park");
		typeList.add("shop");
		
		gm.setAccuracy(50);
		gm.setName(name);
		gm.setPhone_number("(+91) 983 893 3937");
		gm.setAddress(address);
		gm.setWebsite("http://google.com");
		gm.setLanguage(language);
		gm.setTypes(typeList);
		gm.setLocation(l);
		
		return gm;
	}
	
	public String deletePlacePayLoad(String placeId) {
		String deletePayLoad = "{\"place_id\":\""+placeId+"\"}";
		return deletePayLoad;
	}

}
