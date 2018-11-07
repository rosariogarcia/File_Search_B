package com.fundation.search.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidatorTest {

    @Test
    public void verifyKilobytesToBytes() {
        long expected = 1024;
        assertEquals(expected, Validator.getAppropiateSize("1", "Kb"));
    }

    @Test
    public void verifyMegabytesToBytes() {
        long expected = 1024 * 1024;
        assertEquals(expected, Validator.getAppropiateSize("1", "Mb"));
    }

    @Test
    public void verifyGigabytesToBytes() {
        long expected = 1024 * 1024 * 1024;
        assertEquals(expected, Validator.getAppropiateSize("1", "Gb"));
    }
}
