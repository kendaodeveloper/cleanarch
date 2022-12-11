package com.example.cleanarch.entrypoint.webpage.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerWebPageImpl {
  @RequestMapping({"/", "/docs", "/swagger", "/swagger-ui"})
  public String redirect() {
    return "redirect:/swagger-ui.html";
  }
}
