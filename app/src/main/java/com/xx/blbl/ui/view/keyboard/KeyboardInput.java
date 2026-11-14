package com.xx.blbl.ui.view.keyboard;

/** Pure key data and the recovered ASCII-space trimming rule. */
public final class KeyboardInput {
    private KeyboardInput() {}

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final String[][] T9 = {
        {"0", "1", null, null, null}, {"2", "B", "A", "C", null},
        {"3", "E", "D", "F", null}, {"4", "H", "G", "I", null},
        {"5", "K", "J", "L", null}, {"6", "N", "M", "O", null},
        {"7", "Q", "P", "R", "S"}, {"8", "U", "T", "V", null},
        {"9", "X", "W", "Y", "Z"}
    };

    public static String alphabetKey(int index) {
        return String.valueOf(ALPHABET.charAt(index));
    }

    public static String[] t9Keys(int index) {
        return T9[index].clone();
    }

    public static String trimKey(String text) {
        int start = 0, end = text.length() - 1;
        while (start <= end && text.charAt(start) <= ' ') start++;
        while (end >= start && text.charAt(end) <= ' ') end--;
        return text.substring(start, end + 1);
    }
}
