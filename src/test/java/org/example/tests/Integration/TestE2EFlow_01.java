package org.example.tests.Integration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.Pojos.BookingResponse;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.testng.ITestContext;
import org.testng.annotations.Test;


public class TestE2EFlow_01 extends BaseTest {

    //  Test E2E Scenario 1

    //  1. Create a Booking -> bookingID

    // 2. Create Token -> token

    // 3. Verify that the Create Booking is working - GET Request to bookingID

    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request

    // 5. Delete the Booking - Need to get the token, bookingID from above request


    // Create A Booking, Create a Token
    // Verify that Get booking -
    // Update the Booking
    // Delete the Booking

    @Test(groups = "qa", priority = 1)
    @Owner("Meenu")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString())
                .post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Pramod");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());

        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());




    }

}
