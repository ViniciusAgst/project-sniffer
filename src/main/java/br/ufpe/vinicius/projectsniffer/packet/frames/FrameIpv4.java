package br.ufpe.vinicius.projectsniffer.packet.frames;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FrameIpv4{

    private String srcIp;
    private String dstIp;
    private String protocol;
    private int length;

}
