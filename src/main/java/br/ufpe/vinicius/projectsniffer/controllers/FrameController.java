package br.ufpe.vinicius.projectsniffer.controllers;

import br.ufpe.vinicius.projectsniffer.App;
import br.ufpe.vinicius.projectsniffer.frame.Frame;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FrameController {

    @GetMapping("/frames")
    public List<Frame> frames(@RequestParam(defaultValue = "10") int limit) {
        return App.getInterfaceSelector().getFrames().getTops(limit);
    }

    @GetMapping("/frames-size")
    public int framesSize() {
        return App.getInterfaceSelector().getFrames().getFullSize();
    }

    @GetMapping("/frames-length")
    public long framesLength() {
        return Frame.lengthFull;
    }
}
