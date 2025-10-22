package br.ufpe.vinicius.projectsniffer.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Ports {

    HTTP(80),
    HTTP1(8080),
    HTTPS(443),
    SSH(22),
    MYSQL(3306);

    private final int port;

    Ports(int port) {
        this.port = port;
    }

    public static Ports getPort(int port) {
        return Arrays.stream(values()).filter(e -> e.port == port).findFirst().orElse(null);
    }
}
