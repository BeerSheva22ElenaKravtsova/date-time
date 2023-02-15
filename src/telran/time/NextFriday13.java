package telran.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;

public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		LocalDate from = LocalDate.from(temporal);
		return temporal.plus(nextFriday13(from), ChronoUnit.DAYS);
	}

	private long nextFriday13(LocalDate localDate) {
		LocalDate resDate = localDate.plusDays(1);
		while (resDate.getDayOfMonth() != 13) {
			resDate = resDate.plusDays(1);
		}
		while (!resDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
			resDate = resDate.plusMonths(1);
		}
		return ChronoUnit.DAYS.between(localDate, resDate);
	}
}