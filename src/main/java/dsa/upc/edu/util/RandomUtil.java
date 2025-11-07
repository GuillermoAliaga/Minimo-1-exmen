package dsa.upc.edu.util;

import net.moznion.random.string.RandomStringGenerator;

public class RandomUtil {


    public static String getId() {
        RandomStringGenerator generator = new RandomStringGenerator();
        String randomString = generator.generateByRegex("\\w+\\d*[0-9]{0,8}");

        return randomString;
    }
}
