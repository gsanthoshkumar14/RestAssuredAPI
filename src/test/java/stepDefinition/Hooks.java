package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		
		if(StepDefinition.placeId==null) {
			StepDefinition sd = new StepDefinition();
		sd.add_place_payload_with("Guru", "KOL", "TEL");
		sd.user_calls_with_http_request("addPlaceAPI", "POST");
		sd.verify_created_maps_to_using("place_id", "Guru", "getPlaceAPI");
		}
	}
}
