package br.ufpe.vinicius.projectsniffer.services;

import br.ufpe.vinicius.projectsniffer.cache.CacheRotate;
import br.ufpe.vinicius.projectsniffer.packet.IPacket;
import br.ufpe.vinicius.projectsniffer.storage.PacketDTO;
import br.ufpe.vinicius.projectsniffer.storage.PacketRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @Getter
public class PacketService {

    private final CacheRotate<IPacket> packets;
    private final PacketRepository packetRepository;

    @Autowired
    public PacketService(PacketRepository packetRepository) {
        this.packetRepository = packetRepository;
        this.packets = new CacheRotate<>(1000);
    }


    private int counter = 0;

    private long sizer = 0;

    public List<IPacket> getPackets(int limit) {
        return packets.getAll().stream().limit(limit).collect(Collectors.toList());
    }

    public void addPacket(IPacket frame) {
        packets.add(frame);

        PacketDTO dto = new PacketDTO();
        dto.setTimestamp(frame.getTimestamp());
        dto.setEthernet(frame.getEthernet());
        dto.setIpv4(frame.getIpv4());
        dto.setTcp(frame.getTcp());

        packetRepository.save(dto);

        counter++;

        if(frame.getIpv4()!=null) sizer+=frame.getIpv4().getLength();
    }


}
