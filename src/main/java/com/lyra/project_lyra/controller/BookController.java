package com.lyra.project_lyra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {
    @GetMapping("/category")
    public void category() {
        System.out.println("오긴 옴");
    }

}
