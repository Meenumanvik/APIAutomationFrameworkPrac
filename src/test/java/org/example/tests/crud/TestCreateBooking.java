package org.example.tests.crud;

import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.example.Pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;


public class TestCreateBooking extends BaseTest {

    // Create A Booking, Create a Token
    // Verify that Get booking -
    // Update the Booking
    // Delete the Booking

    @Test(groups = "reg", priority = 1)
    @TmsLink("https://bugz.atlassian.net/browse/TS-1")
    @Owner("Meenu")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBookingPOST() {

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString())
                .post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Pramod");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());


    }
}
