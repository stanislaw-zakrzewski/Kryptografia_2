package keyGeneration;

public class BlumMicali {
    public static String getNElements(long prime, long g, long seed, int lenght) {
        long x = seed;
        long a;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lenght; i++) {
            a = modExp(g, x, prime);
            if(a <= (prime-1)/2) {
                stringBuilder.append("1");
            } else {
                stringBuilder.append("0");
            }
            x = a;
        }
        return stringBuilder.toString();
    }

    private static long modExp(long g, long x, long p) {
        long r = 1;
        while (x > 0) {
            if (x % 2 == 1) {
                r = r * g % p;
            }
            x = (long) Math.floor((double) x / 2);
            g = g * g % p;
        }
        return r;
    }
}
