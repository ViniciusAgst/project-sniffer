package br.ufpe.vinicius.projectsniffer.packetlister;


import lombok.Getter;
import org.pcap4j.core.*;
import org.pcap4j.util.NifSelector;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@Getter
public class InterfaceSelector extends Thread{

    private PcapNetworkInterface nif;
    private final Logger logger;

    public InterfaceSelector(Logger logger){
        this.nif = null;
        this.logger = logger;
    }

    @Override
    public void run() {
        {
            try {
                nif = new NifSelector().selectNetworkInterface();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Falha ao Encontrar Interfaces de Rede", e);
            }

            System.out.println("Interface selecionada: " + nif.getDescription());


        }
    }
}
