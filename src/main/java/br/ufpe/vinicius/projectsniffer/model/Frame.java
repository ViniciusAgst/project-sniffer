package br.ufpe.vinicius.projectsniffer.model;

import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "frames")
@Getter @Entity(name = "frames")
public class Frame {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String mac;
    private String protocol;
    private String host;

    protected Frame(){

    }

    public Frame(long id, String mac, String protocol, String host) {
        this.id = id;
        this.mac = mac;
        this.protocol = protocol;
        this.host = host;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
