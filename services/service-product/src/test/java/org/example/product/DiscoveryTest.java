package org.example.product;

import org.example.OrderMainApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest(classes = OrderMainApplication.class)
public class DiscoveryTest {

    @Autowired
    private DiscoveryClient discoveryClient;


    @Test
    void test(){
            discoveryClient.getServices().forEach(s->{
                System.out.println("service:"+s);
                discoveryClient.getInstances(s).forEach(s2->{
                    System.out.println("ip:"+s2.getHost()+" port:"+s2.getPort());
                });
            });
    }

}
