package com.x.utils;

/**
 * @author xgh 2023/6/14
 * 把3个字节的数据通过4个字节来表示 ,将 3个字节分成 4个 6 比特的数据 ,高位填充2个0构成4个字节
 */
public class Base64Utils {
    public static final int STEP = 3;
    public static char[] Base64Code =
            {
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                    'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                    'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
                    '8', '9', '+', '/', '='
            };

    public static void main(String[] args) {
        // TODO xgh
        //        System.out.println(String.valueOf("FJHB".hashCode()));
        //        byte[] bytes = "Man".getBytes(StandardCharsets.UTF_8);
        //        for (int i = 0; i < bytes.length; i += STEP) {
        //            byte[] desc = new byte[3];
        //            System.arraycopy(bytes, i, desc, 0, Math.min(STEP, bytes.length - i));
        //            StringBuilder stringBuilder = new StringBuilder();
        //            for (byte b : intToBytes(bytesToInt(desc))) {
        //                stringBuilder.append(Base64Code[b]);
        //            }
        //            System.out.println(stringBuilder);
        //        }
        char[] array = "TWFu".toCharArray();
        byte[] bytes = new byte[4];
        for (int i = 0; i < array.length; i++) {
            bytes[i] = indexOf(Base64Code, array[i]);
        }
        System.out.println();
    }
    //    public static String encode(String origin) {
    //        byte[] bytes = origin.getBytes();
    //
    //    }

    private static byte indexOf(char[] arr, char c) {
        for (int i = 0; i < arr.length; i++) {
            if (c == arr[i]) {
                return (byte) i;
            }
        }
        return -1;
    }

    private static byte[] intToBytes(int res) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            bytes[i] = (byte) (res & 63);
            res >>>= 6;
        }
        return bytes;
    }

    private static int bytesToInt(byte[] bytes) {
        int res = 0;
        for (int i = 0; i < bytes.length && i < STEP; i++) {
            res |= (255 & bytes[i]) << (i * 8);
        }
        return res;
    }
}
