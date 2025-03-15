package com.roelproject.RoelHotel.service.interfac;

import com.roelproject.RoelHotel.dto.LoginRequest;
import com.roelproject.RoelHotel.dto.Response;
import com.roelproject.RoelHotel.entity.User;

public interface IUserService {
    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

}
