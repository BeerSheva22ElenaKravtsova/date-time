package telran.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class WorkingDays implements TemporalAdjuster {
	int amountOfWorkingDays;
	DayOfWeek[] dayOffs;

	public WorkingDays(int amountOfWorkingDays, DayOfWeek[] dayOffs) {
		this.amountOfWorkingDays = amountOfWorkingDays;
		this.dayOffs = dayOffs;
	}

	@Override
	public Temporal adjustInto(Temporal temporal) {
		LocalDate from = LocalDate.from(temporal);
		return temporal.plus(nextWorkDay(from), ChronoUnit.DAYS);
	}

	private long nextWorkDay(LocalDate from) {
		LocalDate resDate = from;
		while (amountOfWorkingDays != 0) {
			resDate = resDate.plusDays(1);
			if (dayOffs.length == 0) {
				amountOfWorkingDays--;
			} else {
				for (DayOfWeek dayOfWeek : dayOffs) {
					if (!resDate.getDayOfWeek().equals(dayOfWeek)) {
						amountOfWorkingDays--;
					}
				}
			}
		}
		return ChronoUnit.DAYS.between(from, resDate);
	}
}