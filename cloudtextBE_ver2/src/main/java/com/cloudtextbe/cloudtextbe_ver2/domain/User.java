package com.cloudtextbe.cloudtextbe_ver2.domain;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "user")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    // 用户名属性varchar对应String
    private String uname;

    // 密码属性varchar对应String
    private String password;
}
