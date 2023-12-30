package com.cloudtextbe.cloudtextbe_ver2.services;

import com.cloudtextbe.cloudtextbe_ver2.domain.User;

public interface UserService {
    User loginService(User user);
    User registerService(User user);
}
