package com.example.customerdataservice.config;


import com.example.customerdataservice.web.soap.CustomerSoapService;
import lombok.RequiredArgsConstructor;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.EndpointException;
import org.apache.cxf.jaxws.EndpointImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author Eric Wouwo Tionang
 * @licence
 */

@Configuration
@RequiredArgsConstructor
public class CxfConfig {

    private final Bus bus ;

    private final CustomerSoapService customerSoapService;

    @Bean
    public EndpointImpl endpoint() throws EndpointException {
        EndpointImpl endpoint = new EndpointImpl(bus,  customerSoapService);
        endpoint.publish("/CustomerSaopService");
        return endpoint;
    }


}
