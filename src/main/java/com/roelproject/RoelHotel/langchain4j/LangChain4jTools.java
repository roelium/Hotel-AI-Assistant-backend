package com.roelproject.RoelHotel.langchain4j;

import com.roelproject.RoelHotel.dto.BookingDTO;
import com.roelproject.RoelHotel.dto.Response;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;
import com.roelproject.RoelHotel.service.impl.BookingService;

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
    public BookingDTO findBookingByConfirmationCode(String confirmationCode) {
        return service.findBookingByConfirmationCode(confirmationCode).getBooking();
    }

    @Tool("""
            Cancel an existing booking
            """)
    public String cancelBooking(Long bookingId) {
        return service.cancelBooking(bookingId).getMessage();
    }

}
