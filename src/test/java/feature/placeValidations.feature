Feature: Place API's Validation

@AddPlace
Scenario Outline: Verify place is added succesfully by addPlace API
 Given Add place payload with "<Name>" "<Address>" "<Language>"
 When user calls "addPlaceAPI" with "POST" http request
 Then the API call is successful with statuscode 200
 And "status" in response body is "OK"
 And "scope" in response body is "APP"
 And verify "place_id" created maps to "<Name>" using "getPlaceAPI"
 
 Examples:
 
 	| Name | Address | Language |
 	| SanHouse | 14,MTR | TAM |
 	| SorHouse | 13,MDI | TAM |
 	| VaiHouse | 12,CBE | TAM |
 
 @DeletePlace	
 Scenario: Verify delete place functionality
 	Given Deletle place payload
 	When user calls "deletePlaceAPI" with "POST" http request
 	Then the API call is successful with statuscode 200
 	And "status" in response body is "OK"