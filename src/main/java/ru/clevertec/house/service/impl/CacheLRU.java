package ru.clevertec.house.service.impl;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.house.exception.CacheException;
import ru.clevertec.house.service.Cache;

import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
@NoArgsConstructor
public class CacheLRU<K, V> implements Cache<K, V> {

    private Queue<K> queue = new ConcurrentLinkedQueue<K>();
    private Map<K, V> map = new ConcurrentHashMap<K, V>();
    private final int capacity = 20;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock writeLock = lock.writeLock();
    private Lock readLock = lock.readLock();

    @Override
    public void put(K key, V value) {

        writeLock.lock();

        try {
            if (map.containsKey(key)) {
                queue.remove(key);
            }
            if (queue.size() == capacity) {
                K queueKey = queue.poll();
                map.remove(queueKey);
            }
            queue.add(key);
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Optional<V> get(K key) {

        readLock.lock();

        try {
            V value;
            if (map.containsKey(key)) {
                queue.remove(key);
                queue.add(key);
                value = map.get(key);
                Optional<V> optionalValue = Optional.of(value);
                return optionalValue;
            } else {
                return Optional.empty();
            }
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void remove(K key) {

        writeLock.lock();

        try {
            if (map.containsKey(key)) {
                map.remove(key);
                queue.remove(key);
            } else {
                throw new CacheException("There is no such element in cache");
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void clear() {

        writeLock.lock();

        try {
            queue = null;
            map = null;
        } finally {
            writeLock.unlock();
        }
    }
}
