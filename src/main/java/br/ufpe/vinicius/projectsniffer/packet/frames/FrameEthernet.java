package br.ufpe.vinicius.projectsniffer.packet.frames;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable @Getter @AllArgsConstructor @NoArgsConstructor
public class FrameEthernet {

    private String srcMac;
    private String dstMac;
    private String etherType;

}
