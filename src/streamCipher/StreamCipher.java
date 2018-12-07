package streamCipher;

import keyGeneration.BlumMicali;

public class StreamCipher {
    public static String process(String message, long prime, long g, long seed) {
        StringBuilder stringBuilder = new StringBuilder();
        String key = BlumMicali.getNElements(prime,g,seed,message.length());
        for(int i = 0; i < message.length(); i++) {
            stringBuilder.append(xor(message.charAt(i), key.charAt(i)));
        }
        return stringBuilder.toString();
    }

    private static int xor(char val1, char val2) {
        int i1 = Integer.parseInt(Character.toString(val1));
        int i2 = Integer.parseInt(Character.toString(val2));
        return i1 ^ i2;
    }
}
