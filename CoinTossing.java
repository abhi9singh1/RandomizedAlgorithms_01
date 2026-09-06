import java.util.Random;

public class CoinTossing {
    public static void main(String[] args) {

        Random r = new Random();

        int n = 100000;
        int head = 0;
        
        for (int i = 0; i < n; i++) {
            int toss = r.nextInt(2);

            if (toss == 0) {
                head++;
            }
        }

        int tail = n - head;

        System.out.println("Number of tosses = " + n);
        System.out.println("Heads = " + head);
        System.out.println("Tails = " + tail);

        double p = (double) head / n;
        System.out.println("Probability of Head = " + p);
    }
}