package com.mountain.controller;

import com.mountain.service.MountainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    MountainService mountainService;
    @GetMapping("/") // 메인 페이지로 이동
    public String main() {
        return "home";
    }
}
