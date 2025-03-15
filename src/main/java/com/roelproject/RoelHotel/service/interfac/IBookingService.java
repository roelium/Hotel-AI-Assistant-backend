package com.roelproject.RoelHotel.service.interfac;

import com.roelproject.RoelHotel.dto.Response;
import com.roelproject.RoelHotel.entity.Booking;

public interface IBookingService {

    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);

}
