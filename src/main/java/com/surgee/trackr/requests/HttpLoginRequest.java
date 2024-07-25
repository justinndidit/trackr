package com.surgee.trackr.requests;

import lombok.Data;

@Data
public class HttpLoginRequest {
   private String email;
   private String password;
}
