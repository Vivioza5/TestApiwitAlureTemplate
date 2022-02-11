package jsonplaceholder.utils;

import com.github.javafaker.Faker;

public class RandomTextGen {
    public static Faker faker = new Faker();

    public static String getRandomtext() {
        return faker.leagueOfLegends().quote();
    }
}
