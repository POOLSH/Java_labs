package com.example.first_lab.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AngleCache {
    private final Logger logger = LogManager.getLogger(AngleCache.class);
    private final Map<Double, Double> cache = new ConcurrentHashMap<>();

    public Double getRadians(Double degree) {
        logger.info("Get from Map");
        return cache.get(degree);
    }

    public void saveRadians(Double degree, Double radians) {
        logger.info("Radians saved");
        cache.put(degree, radians);
    }

    public boolean containsKey(double degree) {
        return cache.containsKey(degree);
    }
}

