package br.ufpe.vinicius.projectsniffer.frame;

import br.ufpe.vinicius.projectsniffer.enums.Ports;

public record FrameTcp(int srcPort, int dstPort, String hexString) {

    public String detectSrcPort(){
        return Ports.getPort(srcPort).name();
    }

    public String detectDstPort(){
        return Ports.getPort(dstPort).name();
    }
}
