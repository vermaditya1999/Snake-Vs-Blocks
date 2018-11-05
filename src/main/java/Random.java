public class Random {
    public static java.util.Random random;

    public int nextInt(int n) {
        if (random == null) {
            random = new java.util.Random();
        }
        return random.nextInt(n);
    }
}
