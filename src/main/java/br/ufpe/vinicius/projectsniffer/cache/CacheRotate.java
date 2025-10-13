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

    private int fullSize;

    public CacheRotate(int maxSize) {
        this.maxSize = maxSize;
        this.deque = new ConcurrentLinkedDeque<>();
    }

    public void add(T element) {
        fullSize++;

        deque.addFirst(element);
        while (deque.size() > maxSize) {
            deque.pollLast();
        }
    }

    public Deque<T> getAll() {
        return deque;
    }

    public synchronized List<T> getTops(int limit) {
        return deque.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }
}
