package br.ufpe.vinicius.projectsniffer.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/suspects")
    public String suspects() {
        return "suspects";
    }
}
