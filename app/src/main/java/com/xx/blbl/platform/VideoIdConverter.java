package com.xx.blbl.platform;

import java.math.BigInteger;

/** Recovered application-only conversion logic; no copied SDK implementation. */
public abstract class VideoIdConverter {
    public static final BigInteger f733a = BigInteger.valueOf(23442827791579L);
    public static final BigInteger f734b = BigInteger.valueOf(2251799813685247L);
    public static final BigInteger f735c = BigInteger.ONE.shiftLeft(51);

    public static String toBvid(Long l7) {
        int i4 = 11;
        char[] cArr = {'B', 'V', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0'};
        BigInteger bigIntegerXor = f735c.or(BigInteger.valueOf(l7.longValue())).xor(f733a);
        while (bigIntegerXor.compareTo(BigInteger.ZERO) > 0) {
            cArr[i4] =
                    "FcwAPNKTMug3GV5Lj7EJnHpWsx4tb8haYeviqBz6rkCy12mUSDQX9RdoZf"
                            .charAt(bigIntegerXor.mod(BigInteger.valueOf(58L)).intValue());
            bigIntegerXor = bigIntegerXor.divide(BigInteger.valueOf(58L));
            i4--;
        }
        char c8 = cArr[3];
        cArr[3] = cArr[9];
        cArr[9] = c8;
        char c9 = cArr[4];
        cArr[4] = cArr[7];
        cArr[7] = c9;
        StringBuilder sb = new StringBuilder(12);
        for (int i7 = 0; i7 < 12; i7++) {
            sb.append(Character.valueOf(cArr[i7]));
        }
        return sb.toString();
    }

    public static Long toAid(String str) {
        char[] charArray = str.toCharArray();
        char c8 = charArray[3];
        charArray[3] = charArray[9];
        charArray[9] = c8;
        char c9 = charArray[4];
        charArray[4] = charArray[7];
        charArray[7] = c9;
        String str2 = new String(charArray, 3, charArray.length - 3);
        BigInteger bigIntegerAdd = BigInteger.ZERO;
        for (char c10 : str2.toCharArray()) {
            bigIntegerAdd =
                    bigIntegerAdd
                            .multiply(BigInteger.valueOf(58L))
                            .add(
                                    BigInteger.valueOf(
                                            "FcwAPNKTMug3GV5Lj7EJnHpWsx4tb8haYeviqBz6rkCy12mUSDQX9RdoZf"
                                                    .indexOf(c10)));
        }
        return Long.valueOf(bigIntegerAdd.and(f734b).xor(f733a).longValue());
    }
}
