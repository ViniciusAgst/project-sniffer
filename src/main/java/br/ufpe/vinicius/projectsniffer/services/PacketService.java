package br.ufpe.vinicius.projectsniffer.services;

import br.ufpe.vinicius.projectsniffer.cache.CacheRotate;
import br.ufpe.vinicius.projectsniffer.frame.Frame;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @Getter
public class PacketService {

    private final CacheRotate<Frame> frames = new CacheRotate<>(1000);

    private int counter = 0;

    private long sizer = 0;

    public List<Frame> getFrames(int limit) {
        return frames.getAll().stream().limit(limit).collect(Collectors.toList());
    }

    public void addFrame(Frame frame) {
        frames.add(frame);

        counter++;

        if(frame.getIpv4()!=null) sizer+=frame.getIpv4().length();
    }
}
