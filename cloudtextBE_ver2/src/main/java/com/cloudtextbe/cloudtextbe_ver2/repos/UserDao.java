package com.cloudtextbe.cloudtextbe_ver2.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cloudtextbe.cloudtextbe_ver2.domain.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUname(String uname);
    User findByUnameAndPassword(String uname, String password);
}
