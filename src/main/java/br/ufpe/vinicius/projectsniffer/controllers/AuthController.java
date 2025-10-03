package br.ufpe.vinicius.projectsniffer.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    public static boolean logged;

    @PostMapping("/auth")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if(username.equals("admin") && password.equals("admin")){
            logged = true;
            return ResponseEntity.ok("success");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Usuário ou senha incorretos");
    }
}
