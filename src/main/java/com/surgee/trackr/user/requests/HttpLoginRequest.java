package com.surgee.trackr.user.requests;

import lombok.Data;

@Data
public class HttpLoginRequest {
   private String email;
   private String password;
}
