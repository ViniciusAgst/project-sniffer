package br.ufpe.vinicius.projectsniffer.controllers;

import br.ufpe.vinicius.projectsniffer.services.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    public CookieService cookieService;

    @PostMapping("/auth")
    public String auth(
            HttpServletResponse request,
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        if ("admin".equals(username) && "1234".equals(password)) {
            cookieService.addCookie(request, "session", "logado", -1, null, null, true, true);
            return "redirect:/";
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos!");
            return "login";
        }
    }
}
