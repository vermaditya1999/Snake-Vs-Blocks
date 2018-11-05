public class Random {
    public static java.util.Random random;

    public static int nextInt(int n) {
        if (random == null) {
            random = new java.util.Random();
        }
        return random.nextInt(n);
    }

    public static double nextDouble() {
        if (random == null) {
            random = new java.util.Random();
        }
        return random.nextDouble();
    }
}
