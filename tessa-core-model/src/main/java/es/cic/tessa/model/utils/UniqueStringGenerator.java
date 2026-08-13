package es.cic.tessa.model.utils;


import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;


public class UniqueStringGenerator
{

    private static SecureRandom random = new SecureRandom();

    public static String generateUniqueString()
    {

	long timestamp = Instant.now().getEpochSecond();

	byte[] randomBytes = new byte[8];
	random.nextBytes(randomBytes);

	String randomString = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

	String uniqueString = (timestamp + randomString).substring(0, 20);
	return uniqueString;
    }

}