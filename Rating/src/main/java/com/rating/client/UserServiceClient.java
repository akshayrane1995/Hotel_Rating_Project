package com.rating.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rating.dto.UserDto;

//@FeignClient(name = "user-service", url = "${user.service.url}")
@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {

    @GetMapping("/user/id/{id}")
    UserDto getUserById(@PathVariable Long id);
}