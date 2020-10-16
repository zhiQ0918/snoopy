package com.example.snoopy;


import com.example.snoopy.annotation.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "shopcenter")
public interface RemoteService {

    @GetMapping("/shop/list")
    void feignMethod(String param);
}
