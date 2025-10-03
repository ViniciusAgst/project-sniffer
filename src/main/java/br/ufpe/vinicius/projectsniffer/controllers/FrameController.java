package br.ufpe.vinicius.projectsniffer.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrameController {

    @RequestMapping("/")
    public String index() {
        if(AuthController.logged){
            return "forward:/index.html"; // procura em templates/index.html
        }
        return "forward:/login.html";
    }

    @RequestMapping("/login")
    public String login() {
        return "forward:/login.html";
    }
}
