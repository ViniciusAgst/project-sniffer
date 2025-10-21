package br.ufpe.vinicius.projectsniffer.packet;

import br.ufpe.vinicius.projectsniffer.packet.frames.FrameEthernet;
import br.ufpe.vinicius.projectsniffer.packet.frames.FrameIpv4;
import br.ufpe.vinicius.projectsniffer.packet.frames.FrameTcp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IPacket {

    private String timestamp;

    private FrameEthernet ethernet;
    private FrameIpv4 ipv4;
    private FrameTcp tcp;

}
