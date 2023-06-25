package com.x;

/**
 * @author xgh 2023/6/25
 */
public class IdCardValidator {
    public static final int[] WEIGHT = {
            7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2
    };

    public static final char[] CHARS = {
            '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'
    };

    public static final int OFFSET = 48;


    public static boolean legal(String idCard) {
        if (idCard.length() != 18) {
            throw new IllegalArgumentException();
        }
        char[] idChars = idCard.toCharArray();
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            char idChar = idChars[i];
            if (idChar < '0' || idChar > '9') {
                throw new IllegalArgumentException();
            }
            sum += (idChar - OFFSET) * WEIGHT[i];
        }
        return idChars[17] == CHARS[sum % 11];
    }

    public static void main(String[] args) {
        System.out.println(legal("370683198901117657"));
    }
}
