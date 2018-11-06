public class Random {
    public final static java.util.Random RANDOM = new java.util.Random();

    public static int nextInt(int n) {
        return RANDOM.nextInt(n);
    }

    public static double nextDouble() {
        return RANDOM.nextDouble();
    }
}
