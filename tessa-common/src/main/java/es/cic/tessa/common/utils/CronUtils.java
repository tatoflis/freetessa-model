package es.cic.tessa.common.utils;


import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import es.cic.tessa.common.exceptions.TessaException;


public class CronUtils
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CronUtils.class);

    private static CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));

    public static Instant getNextDateOfCronExpression(String cronExpression)
    {

	return getNextDateOfCronExpression(cronExpression, Instant.now(), "");

    }


    public static Instant getNextDateOfCronExpression(String cronExpression, Instant instant)
    {

	return getNextDateOfCronExpression(cronExpression, instant, "");

    }


    public static Instant getNextDateOfCronExpression(String cronExpression, String timeZone)
    {

	return getNextDateOfCronExpression(cronExpression, Instant.now(), timeZone);

    }


    public static Instant getNextDateOfCronExpression(String cronExpression, Instant instant, Long delay)
    {

	return getNextDateOfCronExpression(cronExpression, instant, delay, "");

    }


    public static Instant getNextDateOfCronExpression(String cronExpression, Instant instant, String timeZone)
    {

	return getNextDateOfCronExpression(cronExpression, instant, null, timeZone);

    }


    public static Instant getNextDateOfCronExpression(String cronExpression, Long delay)
    {

	return getNextDateOfCronExpression(cronExpression, Instant.now(), delay);

    }


    public static Instant getNextDateOfCronExpression(String cronExpression, Instant instant, Long delay, String timeZone)
    {

	if(delay == null)
	{
	    delay = Long.valueOf(0);
	}

	Cron cron = parser.parse(cronExpression);

	ZoneId zone = ZoneId.of("UTC");

	if(timeZone != null && !timeZone.isEmpty())
	{

	    zone = ZoneId.of(timeZone);

	}

	if(instant == null)
	{
	    instant = Instant.now();
	}

	ZonedDateTime ofInstant = ZonedDateTime.ofInstant(instant, zone);

	Optional<ZonedDateTime> nextExecution = ExecutionTime.forCron(cron).nextExecution(ofInstant);

	ZonedDateTime nextExecutionWithDelay = nextExecution.orElseThrow(() -> new TessaException("No next execution found for cron expression: " + cronExpression)).plus(delay, ChronoUnit.MILLIS);

	Instant nextInstant = nextExecutionWithDelay.toInstant();

	LOGGER.trace("Instant of next cron {} with timezone [{}] is {}", cronExpression, zone, nextInstant);

	return nextInstant;

    }


    public static Instant getLastDateOfCronExpression(String cronExpression)
    {

	return getLastDateOfCronExpression(cronExpression, Instant.now(), null, "");

    }


    public static Instant getLastDateOfCronExpression(String cronExpression, Long delay)
    {

	return getLastDateOfCronExpression(cronExpression, Instant.now(), delay, "");

    }


    public static Instant getLastDateOfCronExpression(String cronExpression, Instant instant)
    {

	return getLastDateOfCronExpression(cronExpression, instant, null, "");

    }


    public static Instant getLastDateOfCronExpression(String cronExpression, Instant instant, Long delay)
    {

	return getLastDateOfCronExpression(cronExpression, instant, delay, "");

    }


    public static Instant getLastDateOfCronExpression(String cronExpression, Instant instant, Long delay, String timeZone)
    {

	if(delay == null)
	{
	    delay = Long.valueOf(0);
	}

	Cron cron = parser.parse(cronExpression);

	ZoneId zone = ZoneId.of("UTC");

	if(timeZone != null && !timeZone.isEmpty())
	{

	    zone = ZoneId.of(timeZone);

	}

	if(instant == null)
	{
	    instant = Instant.now();
	}

	Optional<ZonedDateTime> lastExecution = ExecutionTime.forCron(cron).lastExecution(ZonedDateTime.ofInstant(instant, zone));

	ZonedDateTime lastExecutionWithDelay = lastExecution.orElseThrow(() -> new TessaException("No last execution found for cron expression: " + cronExpression)).plus(delay, ChronoUnit.MILLIS);

	Instant lastInstant = lastExecutionWithDelay.toInstant();

	LOGGER.trace("Instant of last cron {} with timezone [{}] is {}", cronExpression, zone, lastInstant);

	return lastInstant;

    }


    public static Instant getLastDateOfCronExpressionFromDate(String cronExpression, ZonedDateTime date)
    {

	Cron cron = parser.parse(cronExpression);

	Optional<ZonedDateTime> lastExecution = ExecutionTime.forCron(cron).lastExecution(date);

	ZonedDateTime result = lastExecution.orElseThrow(() -> new TessaException("No last execution found for cron expression: " + cronExpression));
	LOGGER.trace("Last date of cron expression {} from date {} is {}", cronExpression, date, result);

	return result.toInstant();

    }


    public static Instant getNextDateOfCronExpressionFromDate(String cronExpression, ZonedDateTime date)
    {

	Cron cron = parser.parse(cronExpression);

	Optional<ZonedDateTime> nextExecution = ExecutionTime.forCron(cron).nextExecution(date);

	ZonedDateTime result = nextExecution.orElseThrow(() -> new TessaException("No next execution found for cron expression: " + cronExpression));
	LOGGER.trace("Next date of cron expression {} from date {} is {}", cronExpression, date, result);

	return result.toInstant();

    }


    public static Duration getPeriod(String cronExpression)
    {

	return Duration.between(getLastDateOfCronExpression(cronExpression, Instant.now()), getNextDateOfCronExpression(cronExpression).with(ChronoField.NANO_OF_SECOND, 0));

    }


    public static Duration getPeriod(String cronExpression, Instant instant)
    {

	Instant lastDateOfCronExpression = getLastDateOfCronExpression(cronExpression, instant);
	LOGGER.trace("Period from {}", lastDateOfCronExpression);
	Instant nextDateOfCronExpression = getNextDateOfCronExpression(cronExpression, lastDateOfCronExpression);
	LOGGER.trace("Period to {}", nextDateOfCronExpression);

	return Duration.between(lastDateOfCronExpression, nextDateOfCronExpression.with(ChronoField.NANO_OF_SECOND, 0));

    }


    public static Duration getNextPeriodFromInstant(String cronExpression, Instant lastDateOfExecution)
    {

	Instant nextDateOfCronExpression = getNextDateOfCronExpression(cronExpression, lastDateOfExecution);
	LOGGER.trace("Period to {}", nextDateOfCronExpression);

	return Duration.between(lastDateOfExecution, nextDateOfCronExpression.with(ChronoField.NANO_OF_SECOND, 0));

    }


    public static Duration getPeriod(String cronExpression, ChronoField chronoField)
    {

	return Duration.between(getLastDateOfCronExpression(cronExpression, Instant.now()), getNextDateOfCronExpression(cronExpression, Instant.now()).with(chronoField, 0));

    }


    public static boolean isCronFormatValid(String posibleCronExpression)
    {

	try
	{

	    Optional<Cron> cronOptional = Optional.of(parser.parse(posibleCronExpression));

	    return cronOptional.isPresent();
	}
	catch (Exception e)
	{
	    return false;
	}

    }

}
