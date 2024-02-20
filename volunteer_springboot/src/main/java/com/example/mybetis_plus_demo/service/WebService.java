package com.example.mybetis_plus_demo.service;

import com.example.mybetis_plus_demo.pojo.User;

import java.util.List;

public interface WebService {

    User login(User user);

    User register(User user);

    User resetPassword(User user);
}
