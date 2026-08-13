package es.cic.tessa.common.utils;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


class CronUtilsTest
{

    private static final String VALID_CRON = "59 59 23 1/1 * ?";
    private static final String LAST_DAY_CRON = "0 0 12 L * ?";
    private static final String FIRST_DAY_CRON = " 0 0 0 1 * ?";
    private static final String PREVIOUS_FIRST_DAY_CRON = "0 0 0 L-1 * ?";

    private static final String INVALID_CRON = "INVALID CRON";

    @Test
    void testGetNextDateOfCronExpression()
    {

	Instant now = Instant.now();
	Instant nextExecution = CronUtils.getNextDateOfCronExpression(VALID_CRON, now);
	assertNotNull(nextExecution);
	assertTrue(nextExecution.isAfter(now));
    }


    @Test
    void testGetNextDateOfCronExpressionWithTimeZone()
    {

	Instant now = Instant.now();
	String timeZone = "Europe/Madrid";
	Instant nextExecution = CronUtils.getNextDateOfCronExpression(VALID_CRON, now, timeZone);
	assertNotNull(nextExecution);
	assertTrue(nextExecution.isAfter(now));
    }


    @Test
    void testGetLastDateOfCronExpression()
    {

	Instant now = Instant.now();
	Instant lastExecution = CronUtils.getLastDateOfCronExpression(VALID_CRON, now);
	assertNotNull(lastExecution);
	assertTrue(lastExecution.isBefore(now));
    }


    @Test
    void testGetLastDateOfCronExpressionWithTimeZone()
    {

	Instant now = Instant.now();
	String timeZone = "Europe/Madrid";
	Instant lastExecution = CronUtils.getLastDateOfCronExpression(VALID_CRON, now, 0L, timeZone);
	assertNotNull(lastExecution);
	assertTrue(lastExecution.isBefore(now));
    }


    @Test
    void testGetNextDateOfCronExpressionFromDate()
    {

	ZonedDateTime dateTime = ZonedDateTime.of(2025, 1, 1, 10, 0, 0, 0, ZoneId.systemDefault());
	Instant nextExecution = CronUtils.getNextDateOfCronExpressionFromDate(VALID_CRON, dateTime);
	assertNotNull(nextExecution);
    }


    @Test
    void testIsCronFormatValid()
    {

	assertTrue(CronUtils.isCronFormatValid(VALID_CRON));
	assertFalse(CronUtils.isCronFormatValid(INVALID_CRON));
    }


    @Test
    void testGetLastDayOfMonthExecution()
    {

	Instant now = Instant.now();
	Instant lastDayExecution = CronUtils.getNextDateOfCronExpression(LAST_DAY_CRON, now);
	assertNotNull(lastDayExecution);
	ZonedDateTime executionDateTime = ZonedDateTime.ofInstant(lastDayExecution, ZoneId.systemDefault());
	assertEquals(executionDateTime.getDayOfMonth(), executionDateTime.getMonth().length(executionDateTime.toLocalDate().isLeapYear()));
    }


    @Test
    void testGetBeforeDayOfMonthExecution()
    {

	Instant now = Instant.now();
	Instant lastDayExecution = CronUtils.getLastDateOfCronExpression(LAST_DAY_CRON, now);
	assertNotNull(lastDayExecution);
	ZonedDateTime executionDateTime = ZonedDateTime.ofInstant(lastDayExecution, ZoneId.systemDefault());
	assertEquals(executionDateTime.getDayOfMonth(), executionDateTime.getMonth().length(executionDateTime.toLocalDate().isLeapYear()));
    }


    @Test
    void testGetFirstDayOfMonthExecution()
    {

	Instant now = Instant.now();
	Instant firstDayExecution = CronUtils.getNextDateOfCronExpression(FIRST_DAY_CRON, now);
	assertNotNull(firstDayExecution);
	ZonedDateTime executionDateTime = ZonedDateTime.ofInstant(firstDayExecution, ZoneId.systemDefault());
	assertEquals(1, executionDateTime.getDayOfMonth());
    }


    @Test
    void testGetFirstDatBeforeOfMonthExecution()
    {

	Instant now = Instant.now();
	Instant lastDayExecution = CronUtils.getLastDateOfCronExpression(FIRST_DAY_CRON, now);
	assertNotNull(lastDayExecution);
	ZonedDateTime executionDateTime = ZonedDateTime.ofInstant(lastDayExecution, ZoneId.systemDefault());
	assertEquals(1, executionDateTime.getDayOfMonth());
    }


    @Test
    void testGetPreviousDayOfMonthExecution()
    {

	Instant now = Instant.now();
	Instant previousDayExecution = CronUtils.getNextDateOfCronExpression(PREVIOUS_FIRST_DAY_CRON, now);
	assertNotNull(previousDayExecution);
	ZonedDateTime executionDateTime = ZonedDateTime.ofInstant(previousDayExecution, ZoneId.systemDefault());
	assertEquals(executionDateTime.getDayOfMonth(), executionDateTime.getMonth().length(executionDateTime.toLocalDate().isLeapYear()) - 1);
    }


    @Test
    void testGetLastExecutionDateForSunday()
    {

	String cronExpression = "59 59 23 ? * SUN";
	Instant executionDate = ZonedDateTime.of(2025, 4, 28, 1, 0, 0, 0, ZoneId.of("UTC")).toInstant();

	Instant result = ZonedDateTime.of(2025, 4, 27, 23, 59, 59, 0, ZoneId.of("UTC")).toInstant();

	Instant previousExecution = CronUtils.getLastDateOfCronExpression(cronExpression, executionDate, 0L, null);

	assertNotNull(previousExecution);
	assertEquals(result, previousExecution);
    }


    @Test
    @Disabled("Desactivado por posible bug en la libreria CRON-UTILS, el dia 7 de la semana devuelve sabado en lugar de domingo")
    void testGetLastExecutionDateForDayOfTheWeek()
    {

	String cronExpression = "59 59 23 ? * 7";
	Instant executionDate = ZonedDateTime.of(2025, 4, 28, 1, 0, 0, 0, ZoneId.of("UTC")).toInstant();

	Instant result = ZonedDateTime.of(2025, 4, 27, 23, 59, 59, 0, ZoneId.of("UTC")).toInstant();

	Instant previousExecution = CronUtils.getLastDateOfCronExpression(cronExpression, executionDate, 0L, null);

	assertNotNull(previousExecution);
	assertEquals(result, previousExecution);
    }


    @Test
    void testGetLastExecutionDateForLastDayOfMonth()
    {

	String cronExpression = "59 59 23 L * ?";
	Instant executionDate = ZonedDateTime.of(2025, 4, 28, 1, 0, 0, 0, ZoneId.of("UTC")).toInstant();

	Instant result = ZonedDateTime.of(2025, 3, 31, 23, 59, 59, 0, ZoneId.of("UTC")).toInstant();

	Instant previousExecution = CronUtils.getLastDateOfCronExpression(cronExpression, executionDate, 0L, null);

	assertNotNull(previousExecution);
	assertEquals(result, previousExecution);
    }


    @Test
    void testGetNextExecutionDateForLastSecondOfDay()
    {

	String cronExpression = "59 59 23 * * ?";
	Instant executionDate = ZonedDateTime.of(2025, 4, 28, 23, 0, 0, 0, ZoneId.of("UTC")).toInstant();

	Instant result = ZonedDateTime.of(2025, 4, 28, 23, 59, 59, 0, ZoneId.of("UTC")).toInstant();

	Instant previousExecution = CronUtils.getNextDateOfCronExpression(cronExpression, executionDate, 0L, null);

	assertNotNull(previousExecution);
	assertEquals(result, previousExecution);
    }


    @Test
    void testPeriodEvery10Seconds()
    {

	String cron = "0/10 * * * * ?";
	Instant base = Instant.parse("2025-05-13T10:00:05Z");

	Duration duration = CronUtils.getPeriod(cron, base);

	assertEquals(Duration.ofSeconds(10), duration);
    }


    @Test
    void testPeriodEvery15Minutes()
    {

	String cron = "0/15 * * * * ?";
	Instant base = Instant.parse("2025-05-13T10:00:02Z");

	Duration duration = CronUtils.getPeriod(cron, base);

	assertEquals(Duration.ofSeconds(15), duration);
    }


    @Test
    void testPeriodNextDayOneSecondBeforeMidnight()
    {

	String cron = "59 59 23 * * ?";
	Instant base = Instant.parse("2025-05-13T23:59:59Z");

	Duration duration = CronUtils.getPeriod(cron, base);

	assertEquals(Duration.ofHours(24), duration);
    }


    @Test
    void testPeriodWeeklyOnMondayAt9am()
    {

	String cron = "0 0 9 ? * MON";
	Instant base = Instant.parse("2025-05-13T10:00:00Z");

	Duration duration = CronUtils.getPeriod(cron, base);

	assertEquals(Duration.ofDays(7), duration);
    }


    @Test
    void testGetPeriodMonthlyLastDay()
    {

	String cronExpression = "59 59 23 L * ?";

	Instant instant = ZonedDateTime.of(2025, 4, 30, 23, 00, 00, 0, ZoneId.of("UTC")).toInstant();

	Duration period = CronUtils.getPeriod(cronExpression, instant);

	assertEquals(Duration.ofDays(30), period);

    }


    @Test
    void testNextPeriodFromInstantEvery10Seconds()
    {

	String cron = "0/10 * * * * ?";
	Instant base = Instant.parse("2025-05-13T10:00:05Z");

	Duration duration = CronUtils.getNextPeriodFromInstant(cron, base);

	assertEquals(Duration.ofSeconds(5), duration);
    }


    @Test
    void testNextPeriodFromInstantEvery15Minutes()
    {

	String cron = "* 0/15 * * * ?";
	Instant base = Instant.parse("2025-05-13T10:02:00Z");

	Duration duration = CronUtils.getNextPeriodFromInstant(cron, base);

	assertEquals(Duration.ofMinutes(13), duration);
    }


    @Test
    void testNextPeriodFromInstantNextDayOneSecondBeforeMidnight()
    {

	String cron = "59 59 23 * * ?";
	Instant base = Instant.parse("2025-05-13T23:59:59Z");

	Duration duration = CronUtils.getNextPeriodFromInstant(cron, base);

	assertEquals(Duration.ofHours(24), duration);
    }


    @Test
    void testNextPeriodFromInstantWeeklyOnMondayAt9am()
    {

	String cron = "0 0 9 ? * MON";
	Instant base = Instant.parse("2025-05-13T10:00:00Z");

	Duration duration = CronUtils.getNextPeriodFromInstant(cron, base);

	assertEquals(Duration.ofHours(143), duration);
    }


    @Test
    void testNextPeriodFromInstantMonthlyLastDay()
    {

	String cron = "59 59 23 L * ?";
	Instant base = Instant.parse("2025-04-30T23:59:59Z");

	Duration duration = CronUtils.getNextPeriodFromInstant(cron, base);

	assertEquals(Duration.ofHours(744), duration);
    }


    // ── getNextDateOfCronExpression (1 arg) ──────────────────────────────────

    @Test
    void getNextDate_soloExpresion_devuelveFuturo()
    {

	Instant antes = Instant.now();
	Instant result = CronUtils.getNextDateOfCronExpression(VALID_CRON);

	assertNotNull(result);
	assertTrue(result.isAfter(antes));
    }


    // ── getNextDateOfCronExpression (expression, timezone) ───────────────────

    @Test
    void getNextDate_expresionYTimezone_devuelveFuturo()
    {

	Instant antes = Instant.now();
	Instant result = CronUtils.getNextDateOfCronExpression(VALID_CRON, "America/New_York");

	assertNotNull(result);
	assertTrue(result.isAfter(antes));
    }


    // ── getNextDateOfCronExpression (expression, delay) ──────────────────────

    @Test
    void getNextDate_soloDelay_devuelveFuturo()
    {

	Instant antes = Instant.now();
	Instant result = CronUtils.getNextDateOfCronExpression(VALID_CRON, 1000L);

	assertNotNull(result);
	assertTrue(result.isAfter(antes));
    }

    @Test
    void getNextDate_instantYDelay_desplazaExactamente()
    {

	Instant base = Instant.parse("2025-05-13T10:00:00Z");
	long delayMs = 5000L;

	Instant sinDelay = CronUtils.getNextDateOfCronExpression(VALID_CRON, base, 0L);
	Instant conDelay = CronUtils.getNextDateOfCronExpression(VALID_CRON, base, delayMs);

	assertEquals(delayMs, Duration.between(sinDelay, conDelay).toMillis());
    }

    @Test
    void getNextDate_instantDelayTimezone_delayDesplazaExactamente()
    {

	Instant base = Instant.parse("2025-05-13T10:00:00Z");
	long delayMs = 3000L;

	Instant sinDelay = CronUtils.getNextDateOfCronExpression(VALID_CRON, base, 0L, "UTC");
	Instant conDelay = CronUtils.getNextDateOfCronExpression(VALID_CRON, base, delayMs, "UTC");

	assertEquals(delayMs, Duration.between(sinDelay, conDelay).toMillis());
    }

    @Test
    void getNextDate_delayNulo_equivaleADelayZero()
    {

	Instant base = Instant.parse("2025-05-13T10:00:00Z");

	Instant conNulo = CronUtils.getNextDateOfCronExpression(VALID_CRON, base, (Long) null, null);
	Instant conCero = CronUtils.getNextDateOfCronExpression(VALID_CRON, base, 0L, null);

	assertEquals(conCero, conNulo);
    }


    // ── getLastDateOfCronExpression (1 arg) ──────────────────────────────────

    @Test
    void getLastDate_soloExpresion_devuelvePasado()
    {

	Instant antes = Instant.now();
	Instant result = CronUtils.getLastDateOfCronExpression(VALID_CRON);

	assertNotNull(result);
	assertTrue(result.isBefore(antes));
    }


    // ── getLastDateOfCronExpression con delay ────────────────────────────────

    @Test
    void getLastDate_soloDelay_desplazaResultado()
    {

	Instant base = Instant.parse("2025-05-13T10:00:00Z");
	long delayMs = 5000L;

	Instant sinDelay = CronUtils.getLastDateOfCronExpression(VALID_CRON, base, 0L);
	Instant conDelay = CronUtils.getLastDateOfCronExpression(VALID_CRON, base, delayMs);

	assertEquals(delayMs, Duration.between(sinDelay, conDelay).toMillis());
    }

    @Test
    void getLastDate_delayNulo_equivaleADelayZero()
    {

	Instant base = Instant.parse("2025-05-13T10:00:00Z");

	Instant conNulo = CronUtils.getLastDateOfCronExpression(VALID_CRON, base, null, null);
	Instant conCero = CronUtils.getLastDateOfCronExpression(VALID_CRON, base, 0L, null);

	assertEquals(conCero, conNulo);
    }


    // ── getLastDateOfCronExpressionFromDate ──────────────────────────────────

    @Test
    void getLastDateFromDate_domingoAntes_devuelveInstantCorrecto()
    {

	String cron = "59 59 23 ? * SUN";
	ZonedDateTime fecha = ZonedDateTime.of(2025, 4, 28, 1, 0, 0, 0, ZoneId.of("UTC"));

	Instant result = CronUtils.getLastDateOfCronExpressionFromDate(cron, fecha);

	Instant expected = ZonedDateTime.of(2025, 4, 27, 23, 59, 59, 0, ZoneId.of("UTC")).toInstant();
	assertEquals(expected, result);
    }

    @Test
    void getLastDateFromDate_devuelveAntesDelaFecha()
    {

	ZonedDateTime fecha = ZonedDateTime.of(2025, 1, 15, 12, 0, 0, 0, ZoneId.of("UTC"));

	Instant result = CronUtils.getLastDateOfCronExpressionFromDate(VALID_CRON, fecha);

	assertNotNull(result);
	assertTrue(result.isBefore(fecha.toInstant()));
    }


    // ── getNextDateOfCronExpressionFromDate (verificación exacta) ────────────

    @Test
    void getNextDateFromDate_mediodia_devuelveInstantCorrecto()
    {

	String cron = "0 0 12 * * ?";
	ZonedDateTime fecha = ZonedDateTime.of(2025, 1, 15, 10, 0, 0, 0, ZoneId.of("UTC"));

	Instant result = CronUtils.getNextDateOfCronExpressionFromDate(cron, fecha);

	Instant expected = ZonedDateTime.of(2025, 1, 15, 12, 0, 0, 0, ZoneId.of("UTC")).toInstant();
	assertEquals(expected, result);
    }


    // ── isCronFormatValid — casos límite ─────────────────────────────────────

    @Test
    void isCronFormatValid_null_devuelveFalse()
    {

	assertFalse(CronUtils.isCronFormatValid(null));
    }

    @Test
    void isCronFormatValid_vacio_devuelveFalse()
    {

	assertFalse(CronUtils.isCronFormatValid(""));
    }

    @Test
    void isCronFormatValid_variosValidos_devuelveTrue()
    {

	assertTrue(CronUtils.isCronFormatValid("0 0 12 * * ?"));
	assertTrue(CronUtils.isCronFormatValid("0 0 9 ? * MON"));
	assertTrue(CronUtils.isCronFormatValid("0/10 * * * * ?"));
	assertTrue(CronUtils.isCronFormatValid("59 59 23 L * ?"));
    }


    // ── getPeriod (1 arg) ────────────────────────────────────────────────────

    @Test
    void getPeriod_soloExpresion_devuelveDuracionNoNegativa()
    {

	Duration result = CronUtils.getPeriod("0/10 * * * * ?");

	assertNotNull(result);
	assertFalse(result.isNegative());
    }


    // ── efecto de timezone ───────────────────────────────────────────────────

    @Test
    void getNextDate_distingosTimezones_devuelvenInstantesDiferentes()
    {

	// Medianoche local: UTC midnight != Madrid midnight (UTC+1)
	String cron = "0 0 0 * * ?";
	Instant base = Instant.parse("2025-01-15T10:00:00Z");

	Instant utcNext = CronUtils.getNextDateOfCronExpression(cron, base, "UTC");
	Instant madridNext = CronUtils.getNextDateOfCronExpression(cron, base, "Europe/Madrid");

	assertNotEquals(utcNext, madridNext);
    }
}