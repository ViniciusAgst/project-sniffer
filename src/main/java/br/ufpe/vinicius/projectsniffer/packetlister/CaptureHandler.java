package br.ufpe.vinicius.projectsniffer.packetlister;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;

import java.io.IOException;

public class CaptureHandler {
//
//    public void start() throws PcapNativeException, NotOpenException, IOException {
//        // Parâmetros de configuração
//        int snapLen = 1600;  // Tamanho máximo do pacote em bytes
//        PcapNetworkInterface.PromiscuousMode mode =
//                PcapNetworkInterface.PromiscuousMode.PROMISCUOUS;
//        int timeout = 10;    // Timeout em milissegundos
//
//        // Abre a interface para captura
//        handle = nif.openLive(snapLen, mode, timeout);
//
//        // Aplica filtro BPF para capturar apenas TCP
//        String filter = "tcp";
//        handle.setFilter(filter, BpfProgram.BpfCompileMode.OPTIMIZE);
//
//        System.out.println("Modo promíscuo ativado");
//        System.out.println("Filtro aplicado: " + filter);
//    }
}
