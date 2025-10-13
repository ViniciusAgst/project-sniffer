package br.ufpe.vinicius.projectsniffer.packetlister;

import org.pcap4j.core.*;

public class InterfaceHandler extends Thread {

    private final InterfaceSelector selector;
    private final PcapHandle handle;

    protected InterfaceHandler(InterfaceSelector selector) throws PcapNativeException, NotOpenException {
        this.selector = selector;

        this.handle = new PcapHandle.Builder(selector.getNetworkInterface().getName())
                .snaplen(65536)
                .promiscuousMode(PcapNetworkInterface.PromiscuousMode.PROMISCUOUS)
                .timeoutMillis(10)
                .bufferSize(10 * 1024 * 1024)
                .build();

        handle.setFilter("tcp", BpfProgram.BpfCompileMode.OPTIMIZE);
    }

    @Override
    public void run() {
        final InterfaceListener interfaceListener = new InterfaceListener(selector.getFrames());

        try {

            this.selector.getLogger().info("Iniciando captura de pacotes...");

            handle.loop(-1, interfaceListener);

            handle.close();

        } catch (PcapNativeException | InterruptedException | NotOpenException e) {

            this.selector.getLogger().error("#2 Falha ao iniciar captura de pacotes do handler!", e);

        }
    }
}
