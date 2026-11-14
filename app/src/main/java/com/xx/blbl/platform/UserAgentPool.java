package com.xx.blbl.platform;

import java.util.Random;

/** Application UA selection separated from the R8-merged Glide helper. */
final class UserAgentPool {
    static final String BASE =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)"
                    + " Chrome/116.0.0.0 Safari/537.36";
    private static final Random RANDOM = new Random();

    private UserAgentPool() {}

    private static int between(int lower, int upper) {
        return lower + RANDOM.nextInt(upper - lower + 1);
    }

    static String next() {
        int chrome = between(120, 133);
        String[] values = {
            BASE
                    + between(12, 15)
                    + '_'
                    + between(1, 7)
                    + ") AppleWebKit/537.36 (KHTML, like Gecko) Chrome/"
                    + chrome
                    + ".0.0.0 Safari/537.36",
            BASE
                    + between(12, 15)
                    + '_'
                    + between(1, 7)
                    + ") AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.3 Safari/605.1.15",
            BASE + chrome + ".0.0.0 Safari/537.36 Edg/" + chrome + ".0.0.0",
            BASE + chrome + ".0.0.0 Safari/537.36"
        };
        return values[RANDOM.nextInt(values.length)];
    }
}
