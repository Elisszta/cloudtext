package com.cloudtextbe.cloudtextbe_ver2.services.serviceimpl;

import com.cloudtextbe.cloudtextbe_ver2.domain.User;
import com.cloudtextbe.cloudtextbe_ver2.repos.UserDao;
import com.cloudtextbe.cloudtextbe_ver2.services.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User loginService(User user) {
        User findUser = userDao.findByUnameAndPassword(user.getUname(), user.getPassword());
        if(findUser != null) {
            findUser.setPassword("");
        }
        return findUser;
    }

    @Override
    public User registerService(User user) {
        if (userDao.findByUname(user.getUname()) != null) {
            return null;
        } else {
            User newUser = userDao.save(user);
            if (newUser != null) {
                newUser.setPassword("");
            }
            return newUser;
        }
    }


}
