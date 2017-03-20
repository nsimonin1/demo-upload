package org.simon.pascal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by simon on 20/03/2017.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "redirect:/index.htm";
    }

    @GetMapping("/index.htm")
    public String home() {
        return "index";
    }

}
