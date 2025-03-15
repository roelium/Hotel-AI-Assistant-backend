package com.roelproject.RoelHotel.langchain4j;

import com.roelproject.RoelHotel.dto.Response;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;
import com.roelproject.RoelHotel.service.impl.BookingService;
import com.roelproject.RoelHotel.service.impl.RoomService;

import java.time.LocalDate;

@Component
public class LangChain4jTools {

    private final BookingService service;

    public LangChain4jTools(BookingService service) {
        this.service = service;
    }

    @Tool("""
            Retrieves information about an existing booking,
            such as the check in date, check out date, total number of guest, confirmation code, room type, room price.
            """)
    public Response findBookingByConfirmationCode(String confirmationCode) {
        return service.findBookingByConfirmationCode(confirmationCode);
    }

   /* @Tool("""
            Modifies an existing booking.
            This includes making changes to the flight date, and the departure and arrival airports.
            """)
    public void changeBooking(
        String bookingNumber,
        String firstName,
        String lastName,
        LocalDate newFlightDate,
        @P("3-letter code for departure airport") String newDepartureAirport,
        @P("3-letter code for arrival airport") String newArrivalAirport
    ) {
        service.changeBooking(bookingNumber, firstName, lastName, newFlightDate, newDepartureAirport, newArrivalAirport);
    }*/

    @Tool
    public Response cancelBooking(Long bookingId) {
        return service.cancelBooking(bookingId);
    }

}
