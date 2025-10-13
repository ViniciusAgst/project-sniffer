package br.ufpe.vinicius.projectsniffer.packetlister;

import br.ufpe.vinicius.projectsniffer.cache.CacheRotate;
import br.ufpe.vinicius.projectsniffer.controllers.FrameController;
import br.ufpe.vinicius.projectsniffer.frame.Frame;
import br.ufpe.vinicius.projectsniffer.frame.FrameEthernet;
import br.ufpe.vinicius.projectsniffer.frame.FrameIpv4;
import br.ufpe.vinicius.projectsniffer.frame.FrameTcp;

import org.pcap4j.core.PacketListener;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.namednumber.EtherType;

import java.time.Instant;
public class InterfaceListener implements PacketListener {

    private final CacheRotate<Frame> frames;

    public InterfaceListener(CacheRotate<Frame> frames) {
        this.frames = frames;
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

            ipv4 = new FrameIpv4(srcIP, dstIP, protocol);
        }

        FrameTcp tcp = null;

        final TcpPacket tcpPacket;

        if ((tcpPacket = packet.get(TcpPacket.class)) != null) {
            int srcPort = tcpPacket.getHeader().getSrcPort().valueAsInt();
            int dstPort = tcpPacket.getHeader().getDstPort().valueAsInt();
            String hexString = tcpPacket.toHexString();

            tcp = new FrameTcp(srcPort, dstPort, hexString);
        }

        final Frame frame = new Frame(Instant.now(), ethernet, ipv4, tcp, packet.length());

        Frame.lengthFull = Frame.lengthFull + frame.getLength();

        frames.add(frame);
    }
}
