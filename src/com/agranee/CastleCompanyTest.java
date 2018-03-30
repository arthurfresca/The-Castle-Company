package com.agranee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CastleCompanyTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void testCastleInPeakAndValeys() {
        CastleCompany.generateResponse("1,2,3,5,7,8,9,4,5");
        assertEquals("You can build 3 castles on this land", outContent.toString());
    }

    @Test
    public void testArrayWithSingleValue() {
        CastleCompany.generateResponse("4");
        assertEquals("You can build 1 castles on this land", outContent.toString());
    }

    @Test
    public void testNullInput() {
        CastleCompany.generateResponse(null);
        assertEquals("Invalid input. You must provide numbers separated by comma (ie: 1,3,4)", outContent.toString());
    }

    @Test
    public void test1Castle() {
        CastleCompany.generateResponse("1,2,3,4,5");
        assertEquals("You can build 1 castles on this land", outContent.toString());
    }

    @Test
    public void testInvalidInput() {
        CastleCompany.generateResponse("1,2,3,be,7,8,9,4,5");
        assertEquals("Invalid input. You must provide numbers separated by comma (ie: 1,3,4)", outContent.toString());
    }
}