package br.ufpe.vinicius.projectsniffer.cache;

import lombok.Getter;

import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

@Getter
public class CacheRotate<T> {

    private final int maxSize;
    private final Deque<T> deque;

    public CacheRotate(int maxSize) {
        this.maxSize = maxSize;
        this.deque = new ConcurrentLinkedDeque<>();
    }

    public void add(T element) {
        deque.addFirst(element);
        while (deque.size() > maxSize) {
            deque.pollLast();
        }
    }

    public Deque<T> getAll() {
        return deque;
    }
}
