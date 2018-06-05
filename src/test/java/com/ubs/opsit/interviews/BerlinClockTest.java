package com.ubs.opsit.interviews;

import org.junit.Test;

public class BerlinClockTest {

	@Test
	public void testMinValidBerlinClock() {
		new BerlinClock().convertTime("00:00:00");
	}

	@Test
	public void testMaxValidBerlinClock() {
		new BerlinClock().convertTime("23:59:59");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLowerInvalidHours() {
		new BerlinClock().convertTime("-01:00:00");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidString() {
		new BerlinClock().convertTime("00:00");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyString() {
		new BerlinClock().convertTime("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpperInvalidHours() {
		new BerlinClock().convertTime("25:00:00");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpperInvalidMinutes() {
		new BerlinClock().convertTime("00:60:00");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpperInvalidSeconds() {
		new BerlinClock().convertTime("00:00:60");
	}

}
