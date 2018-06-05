package com.ubs.opsit.interviews;

import java.util.ArrayList;
import java.util.List;

public class BerlinClock implements TimeConverter {

	@Override
	public String convertTime(String aTime) {
		if (!aTime.matches(BerlinConstants.ACCEPTABLE_TIME_FORMAT)) {
			throw new IllegalArgumentException("Time must be in the format HH:MM:SS, but we have - " + aTime);
		}

		List<Integer> splitTime = new ArrayList<>();
		for (String part : aTime.split(BerlinConstants.SEPARATOR_SIGN)) {
			splitTime.add(Integer.parseInt(part));
		}

		if (splitTime.get(0) < 0 || splitTime.get(0) > 24)
			throw new IllegalArgumentException("Hours out of bounds.");
		if (splitTime.get(1) < 0 || splitTime.get(1) > 59)
			throw new IllegalArgumentException("Minutes out of bounds.");
		if (splitTime.get(2) < 0 || splitTime.get(2) > 59)
			throw new IllegalArgumentException("Seconds out of bounds.");

		return new StringBuilder().append(getSecondsRow(splitTime.get(2))).append(BerlinConstants.NEW_LINE)
				.append(getTopRowOfHours(splitTime.get(0))).append(BerlinConstants.NEW_LINE)
				.append(getBottomRowOfHours(splitTime.get(0))).append(BerlinConstants.NEW_LINE)
				.append(getTopRowOfMinutes(splitTime.get(1))).append(BerlinConstants.NEW_LINE)
				.append(getBottomRowOfMinutes(splitTime.get(1))).toString();
	}

	protected String getSecondsRow(int value) {
		if (value % 2 == 0)
			return BerlinConstants.ON;
		else
			return BerlinConstants.OFF;
	}

	protected String getTopRowOfHours(int value) {
		return getOnOff(BerlinConstants.NO_OF_FIRST_ROW_HOURS_BULB, getTopNumberOfOnSigns(value));
	}

	protected String getBottomRowOfHours(int value) {
		return getOnOff(BerlinConstants.NO_OF_SECOND_ROW_HOURS_BULB, value % 5);
	}

	protected String getTopRowOfMinutes(int value) {
		return getOnOff(BerlinConstants.NO_OF_FIRST_ROW_MINUTES_BULB, getTopNumberOfOnSigns(value), BerlinConstants.ON)
				.replaceAll(BerlinConstants.TOP_ROW_OF_MINUTES_WITHOUT_QUARTERS,
						BerlinConstants.TOP_ROW_OF_MINUTES_WITH_QUARTERS);
	}

	protected String getBottomRowOfMinutes(int value) {
		return getOnOff(BerlinConstants.NO_OF_SECOND_ROW_MINUTES_BULB, value % 5, BerlinConstants.ON);
	}

	private String getOnOff(int lampsInSummary, int lampsIsOnCount) {
		return getOnOff(lampsInSummary, lampsIsOnCount, BerlinConstants.ON_COLOUR);
	}

	private String getOnOff(int lampsInSummary, int lampsIsOnCount, String onSign) {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < lampsIsOnCount; i++) {
			out.append(onSign);
		}

		for (int i = 0; i < (lampsInSummary - lampsIsOnCount); i++) {
			out.append(BerlinConstants.OFF);
		}
		return out.toString();
	}

	private int getTopNumberOfOnSigns(int value) {
		return (value - (value % 5)) / 5;
	}
}