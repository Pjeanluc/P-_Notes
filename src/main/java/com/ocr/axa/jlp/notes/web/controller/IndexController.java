package com.ocr.axa.jlp.notes.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class IndexController {
    @GetMapping("/")
    public RedirectView index() {
        return new RedirectView("/swagger-ui.html");
    }
}

