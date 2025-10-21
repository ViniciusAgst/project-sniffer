package br.ufpe.vinicius.projectsniffer.packetlister;

import br.ufpe.vinicius.projectsniffer.App;
import org.pcap4j.core.*;

public class InterfaceHandler extends Thread {

    private final PcapHandle handle;

    protected InterfaceHandler(String name) throws PcapNativeException, NotOpenException {

        this.handle = new PcapHandle.Builder(name)
                .snaplen(65536)
                .promiscuousMode(PcapNetworkInterface.PromiscuousMode.PROMISCUOUS)
                .timeoutMillis(10)
                .bufferSize(10 * 1024 * 1024)
                .build();

        handle.setFilter("tcp", BpfProgram.BpfCompileMode.OPTIMIZE);
    }

    @Override
    public void run() {
        final InterfaceListener interfaceListener = new InterfaceListener();

        try {

            handle.loop(-1, interfaceListener);

            handle.close();

        } catch (PcapNativeException | InterruptedException | NotOpenException e) {

            App.logger.error("#2 Falha ao iniciar captura de pacotes do handler!", e);

        }
    }
}
