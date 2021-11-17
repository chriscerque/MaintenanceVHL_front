package com.example.maintenancevhl_front.model.entities;

import com.example.maintenancevhl_front.view.security.UserRole;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class User implements Serializable {
    @Getter
    @Setter
    private String login;
    //    @Getter
//    @Setter
//    private String pwd;
    @Getter
    @Setter
    private UserRole role;
}
