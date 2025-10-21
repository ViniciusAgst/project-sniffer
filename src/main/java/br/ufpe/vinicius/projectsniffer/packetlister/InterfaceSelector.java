package br.ufpe.vinicius.projectsniffer.packetlister;


import br.ufpe.vinicius.projectsniffer.App;
import lombok.Getter;
import org.pcap4j.core.*;
import org.pcap4j.util.NifSelector;

import java.io.IOException;

@Getter
public class InterfaceSelector{

    private PcapNetworkInterface networkInterface;

    public InterfaceSelector(){
        this.networkInterface = null;
    }

    public void interfaceSelect() {
        try {
            networkInterface = new NifSelector().selectNetworkInterface();

            if (networkInterface == null) {
                throw new IOException();
            }

            App.logger.info("Interface selecionada: {}", networkInterface);

            InterfaceHandler handler = new InterfaceHandler(networkInterface.getName());

            handler.start();

        } catch (IOException | NotOpenException | PcapNativeException e) {

            App.logger.error("#1 Falha ao encontrar interfaces de rede", e);

        }
    }
}
