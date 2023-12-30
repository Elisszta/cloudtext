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

    /** User's name */
    private String uname;
    /** User's Password */
    private String password;
}
