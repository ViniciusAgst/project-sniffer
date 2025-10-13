package br.ufpe.vinicius.projectsniffer.packetlister;


import br.ufpe.vinicius.projectsniffer.cache.CacheRotate;
import br.ufpe.vinicius.projectsniffer.frame.Frame;
import lombok.Getter;
import org.pcap4j.core.*;
import org.pcap4j.util.NifSelector;
import org.slf4j.Logger;

import java.io.IOException;

@Getter
public class InterfaceSelector{

    private PcapNetworkInterface networkInterface;

    private final Logger logger;

    private final CacheRotate<Frame> frames;

    public InterfaceSelector(Logger logger){
        this.networkInterface = null;

        this.logger = logger;

        this.frames = new CacheRotate<>(10000);
    }

    public void interfaceSelect() {
        try {
            networkInterface = new NifSelector().selectNetworkInterface();

            if (networkInterface == null) {
                throw new IOException();
            }

            this.logger.info("Nif selecionado: {}", networkInterface);

            InterfaceHandler handler = new InterfaceHandler(this);

            handler.start();

        } catch (IOException | NotOpenException | PcapNativeException e) {

            logger.error("#1 Falha ao encontrar interfaces de rede", e);

        }
    }
}
