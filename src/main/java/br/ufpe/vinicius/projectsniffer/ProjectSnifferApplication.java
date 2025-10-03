package br.ufpe.vinicius.projectsniffer;

import br.ufpe.vinicius.projectsniffer.packetlister.InterfaceSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class ProjectSnifferApplication {

    protected static Logger logger;

    public static void main(String[] args) {
        logger = Logger.getLogger(ProjectSnifferApplication.class.getName());

        SpringApplication.run(ProjectSnifferApplication.class, args);

        InterfaceSelector packetListen = new InterfaceSelector(logger);
        packetListen.start();
    }
}
