package br.ufpe.vinicius.projectsniffer.controllers;

import br.ufpe.vinicius.projectsniffer.packet.IPacket;
import br.ufpe.vinicius.projectsniffer.services.PacketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/packets")
public class PacketController {

    private final PacketService packetService;

    public PacketController(PacketService packetService) {
        this.packetService = packetService;
    }

    /**
     * Adquire a lista de pacotes
     * @param limit total de pacotes
     * @return lista de pacotes
     */
    @GetMapping("/list")
    public List<IPacket> list(@RequestParam(defaultValue = "100") int limit) {
        return packetService.getFrames(limit);
    }

    /**
     * Adquire o tamanho total de pacotes
     * @return tamanho total
     */
    @GetMapping("/sizer")
    public long sizer() {
        return packetService.getSizer();
    }

    /**
     * Adquire o total de pacotes
     * @return total de pacotes
     */
    @GetMapping("/counter")
    public int counter() {
        return packetService.getCounter();
    }
}
