package com.chz.demo2;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
public class Test {

    @org.junit.jupiter.api.Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encode = passwordEncoder.encode("123");
        System.out.println(encode);//$2a$10$OHOzZsC9RMCqJdWWpBzgfOfQZlEVedDXrUqHhp3HSINu4HghI59kq
		/*
			加密后的信息是动态变化的，因为我们要使用，matches()来进行比较。
		*/
        boolean matches = passwordEncoder.matches("123", encode);
        System.out.println(matches);//true

        boolean matches2 = passwordEncoder.matches("12345", encode);
        System.out.println(matches2);//false
    }
}
