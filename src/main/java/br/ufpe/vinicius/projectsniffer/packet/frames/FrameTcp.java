package br.ufpe.vinicius.projectsniffer.packet.frames;

import br.ufpe.vinicius.projectsniffer.enums.Ports;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FrameTcp {

    private int srcPort;
    private int dstPort;

    @Column(columnDefinition = "LONGTEXT")
    private String hexString;

    public String detectSrcPort(){
        return Ports.getPort(srcPort).name();
    }

    public String detectDstPort(){
        return Ports.getPort(dstPort).name();
    }
}
