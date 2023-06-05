package com.example.first_lab.Test;

import com.example.first_lab.services.AngleCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AngleCacheTest {

    private AngleCache angleCache;

    @BeforeEach
    void setUp() {
        angleCache = new AngleCache();
    }

    @Test
    void testGetRadians() {
        double degree = 45.0;
        double radians = Math.toRadians(degree);
        angleCache.saveRadians(degree, radians);
        double result = angleCache.getRadians(degree);
        assertEquals(radians, result);
    }

    @Test
    void testSaveRadians() {
        double degree = 60.0;
        double radians = Math.toRadians(degree);
        angleCache.saveRadians(degree, radians);
        boolean result = angleCache.containsKey(degree);
        assertTrue(result);
    }

    @Test
    void testContainsKey() {
        double degree = 90.0;
        boolean result = angleCache.containsKey(degree);
        assertFalse(result);
        angleCache.saveRadians(degree, Math.toRadians(degree));
        result = angleCache.containsKey(degree);
        assertTrue(result);
    }
}
