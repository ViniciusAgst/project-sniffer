package br.ufpe.vinicius.projectsniffer.packetlister;

import br.ufpe.vinicius.projectsniffer.packet.IPacket;
import br.ufpe.vinicius.projectsniffer.packet.frames.FrameEthernet;
import br.ufpe.vinicius.projectsniffer.packet.frames.FrameIpv4;
import br.ufpe.vinicius.projectsniffer.packet.frames.FrameTcp;

import br.ufpe.vinicius.projectsniffer.services.PacketService;
import br.ufpe.vinicius.projectsniffer.utils.SpringContext;
import org.pcap4j.core.PacketListener;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.namednumber.EtherType;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class InterfaceListener implements PacketListener {

    private final DateTimeFormatter formatter;
    private final PacketService packetService;

    public InterfaceListener() {
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS 'UTC'");
        this.packetService = SpringContext.getBean(PacketService.class);
    }

    @Override
    public void gotPacket(Packet packet) {

        FrameEthernet ethernet = null;

        final EthernetPacket ethernetPacket;

        if ((ethernetPacket = packet.get(EthernetPacket.class)) != null) {

            String srcMAC = ethernetPacket.getHeader().getSrcAddr().toString();
            String dstMAC = ethernetPacket.getHeader().getDstAddr().toString();
            EtherType etherType = ethernetPacket.getHeader().getType();

            ethernet = new FrameEthernet(srcMAC, dstMAC, etherType.toString());
        }

        FrameIpv4 ipv4 = null;

        final IpV4Packet ipPacket;

        if ((ipPacket = packet.get(IpV4Packet.class)) != null) {
            String srcIP = ipPacket.getHeader().getSrcAddr().getHostAddress();
            String dstIP = ipPacket.getHeader().getDstAddr().getHostAddress();
            String protocol = ipPacket.getHeader().getProtocol().valueAsString();
            int lenght = ipPacket.getHeader().getTotalLengthAsInt();

            ipv4 = new FrameIpv4(srcIP, dstIP, protocol, lenght);
        }

        FrameTcp tcp = null;

        final TcpPacket tcpPacket;

        if ((tcpPacket = packet.get(TcpPacket.class)) != null) {
            int srcPort = tcpPacket.getHeader().getSrcPort().valueAsInt();
            int dstPort = tcpPacket.getHeader().getDstPort().valueAsInt();
            String hexString = tcpPacket.toHexString();

            tcp = new FrameTcp(srcPort, dstPort, hexString);
        }

        ZonedDateTime nowUtc = Instant.now().atZone(ZoneOffset.UTC);

        String timeStamp = formatter.format(nowUtc);

        final IPacket iPacket = new IPacket(timeStamp, ethernet, ipv4, tcp);

        packetService.addFrame(iPacket);
    }
}
