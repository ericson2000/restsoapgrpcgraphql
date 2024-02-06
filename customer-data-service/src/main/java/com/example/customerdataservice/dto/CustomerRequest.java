package com.example.customerdataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author Eric Wouwo Tionang
 * @licence
 */

@Data @NoArgsConstructor @AllArgsConstructor
public class CustomerRequest {

    private String name;

    private String email;
}
