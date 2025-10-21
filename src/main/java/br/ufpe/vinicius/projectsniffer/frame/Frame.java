package br.ufpe.vinicius.projectsniffer.frame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Frame {

    private String timestamp;

    private FrameEthernet ethernet;
    private FrameIpv4 ipv4;
    private FrameTcp tcp;

}
