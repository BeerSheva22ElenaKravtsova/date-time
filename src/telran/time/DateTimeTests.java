package telran.time;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateTimeTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void localDateTest() {
		LocalDate birthDateAS = LocalDate.parse("1799-06-06");
		LocalDate barMizvaAS = birthDateAS.plusYears(13);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM,YYYY,d");
		System.out.println(barMizvaAS.format(dtf));
		ChronoUnit unit = ChronoUnit.MONTHS;
		System.out.printf("Number of %s between %s and %s is %d", unit,
				birthDateAS, barMizvaAS, unit.between(birthDateAS, barMizvaAS));
		System.out.println();
		System.out.println("*************************************");
		
	}
	@Test
	void barMizvaTest() {
		LocalDate current = LocalDate.now();
		assertEquals(current.plusYears(13), current.with(new BarMizvaAdjuster()));
	}
	
	@Test
	void displayCurrentDateTimeCanadaTimeZones () {
		ZoneId.getAvailableZoneIds().stream().filter(zoneId -> zoneId.contains("Canada")).map(ZoneId::of).map(ZonedDateTime::now).forEach(System.out::println);;
	}
	
	@Test
	void nextFriday13Test() {
		LocalDate nextF13 = LocalDate.parse("2023-10-13");
		LocalDate current = LocalDate.now();
		assertEquals(nextF13, current.with(new NextFriday13()));
		
		LocalDate nextNextF13 = LocalDate.parse("2024-09-13");
		assertEquals(nextNextF13, nextF13.plusDays(1).with(new NextFriday13()));
	}

	@Test
	void workingDaysTest() {
		LocalDate current = LocalDate.parse("2023-02-14");
		assertEquals(current.plusDays(12), current.with(new WorkingDays(10, new DayOfWeek[]{DayOfWeek.SATURDAY})));
		assertEquals(current.plusDays(10), current.with(new WorkingDays(10, new DayOfWeek[] {})));
	}
}
