package br.ufpe.vinicius.projectsniffer.controllers;

import br.ufpe.vinicius.projectsniffer.services.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final CookieService cookieService;

    @Autowired
    public LoginController(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    @PostMapping("/auth")
    public String auth(
            HttpServletResponse response,
            @RequestParam String username,
            @RequestParam String password) {

        if ("admin".equals(username) && "1234".equals(password)) {
            cookieService.addCookie(response, "session", "logado", 1000, null, null, false, true);
            return "redirect:/";
        }

        return "redirect:/login?error=true";
    }


    @PostMapping("/exit")
    public String auth(
            HttpServletResponse response) {

        cookieService.deleteCookie(response, "session", null, null);

        return "redirect:/login";
    }
}
