package com.example.first_lab.Test;

import com.example.first_lab.services.RequestCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestCounterTest {

    private RequestCounter requestCounter;

    @BeforeEach
    void setUp() {
        requestCounter = new RequestCounter();
    }

    @Test
    void testSetZero() {
        requestCounter.increment();
        requestCounter.increment();
        int result = requestCounter.setZero();
        assertEquals(0, result);
        assertEquals(0, requestCounter.getCount());
    }

    @Test
    void testIncrement() {
        int expected = 1;
        int result = requestCounter.increment();
        assertEquals(expected, result);
        assertEquals(expected, requestCounter.getCount());
    }

    @Test
    void testGetCount() {
        int expected = 0;
        int result = requestCounter.getCount();
        assertEquals(expected, result);
        requestCounter.increment();
        expected = 1;
        result = requestCounter.getCount();
        assertEquals(expected, result);
    }
}