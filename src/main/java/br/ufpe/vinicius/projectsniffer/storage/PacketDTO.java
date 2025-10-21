package br.ufpe.vinicius.projectsniffer.storage;

import br.ufpe.vinicius.projectsniffer.packet.frames.FrameEthernet;
import br.ufpe.vinicius.projectsniffer.packet.frames.FrameIpv4;
import br.ufpe.vinicius.projectsniffer.packet.frames.FrameTcp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class PacketDTO {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String timestamp;

    @Embedded
    private FrameEthernet ethernet;

    @Embedded
    private FrameIpv4 ipv4;

    @Embedded
    private FrameTcp tcp;

}
