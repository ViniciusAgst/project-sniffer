package br.ufpe.vinicius.projectsniffer;

import br.ufpe.vinicius.projectsniffer.packetlister.InterfaceSelector;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.LogManager;

@SpringBootApplication
public class App {

    @Getter
    private static InterfaceSelector interfaceSelector;

    protected static Logger logger;

    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();

        SpringApplication.run(App.class, args);

        logger = LoggerFactory.getLogger(App.class);

        interfaceSelector = new InterfaceSelector(logger);
        interfaceSelector.interfaceSelect();
    }
}
