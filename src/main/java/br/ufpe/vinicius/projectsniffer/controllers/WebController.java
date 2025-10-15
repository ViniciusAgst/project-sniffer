package br.ufpe.vinicius.projectsniffer.controllers;

import br.ufpe.vinicius.projectsniffer.services.CookieService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    public CookieService cookieService;

    @GetMapping("/")
    public String dashboard(HttpServletRequest request) {
        if(cookieService.getCookieValue(request, "session").isEmpty()) {
            return "redirect:/login";
        }

        return "dashboard";
    }

    @GetMapping("/suspects")
    public String suspects() {
        return "suspects";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        if(cookieService.getCookieValue(request, "session").isPresent()) {
            return "redirect:/";
        }

        return "login";
    }
}
