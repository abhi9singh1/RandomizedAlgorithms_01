import java.util.Random;
public class QuickSortVariants {
    static int[] Array(int n, Random r) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(10000);
        }
        return a;
    }
    static int[] copyArray(int a[]) {
        int b[] = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }
    static long quickComp;
    static long insertComp;
    static void quickSort(int a[], int low, int high) {
        if (low < high) {
            int p = partition(a, low, high);
            quickSort(a, low, p - 1);
            quickSort(a, p + 1, high);
        }
    }
    static int partition(int a[], int low, int high) {
        int p = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            quickComp++;
            if (a[j] <= p) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;
        return i + 1;
    }
    static void insertionSort(int a[]) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0) {
                insertComp++;
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else {break;}
            }
            a[j + 1] = key;
        }
    }
    static void hybridQuickSort(int a[], int low, int high) {
        if (low < high) {
            if (high - low + 1 <= 12) {
                insertionPart(a, low, high);
                return;
            }
            int p = partition(a, low, high);
            hybridQuickSort(a, low, p - 1);
            hybridQuickSort(a, p + 1, high);
        }
    }
    static void insertionPart(int a[], int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= low) {
                insertComp++;
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else {break;}
            }
            a[j + 1] = key;
        }
    }
    public static void main(String[] args) {
        Random r = new Random();
        System.out.println("n\tQuick\t\tInsertion\t\tHybrid");
        int crossover = -1;
        for (int n = 10; n <= 1000; n += 10) {
            long quickTotal = 0;
            long insertionTotal = 0;
            long hybridTotal = 0;
            for (int run = 0; run < 50; run++) {
                int original[] = Array(n, r);
                int a[] = copyArray(original);
                int b[] = copyArray(original);
                int c[] = copyArray(original);
                quickComp = 0;
                quickSort(a, 0, n - 1);
                quickTotal += quickComp;
                insertComp = 0;
                insertionSort(b);
                insertionTotal += insertComp;
                quickComp = 0;
                insertComp = 0;
                hybridQuickSort(c, 0, n - 1);
                hybridTotal += quickComp + insertComp;
            }
            double quickAvg = (double) quickTotal / 50;
            double insertionAvg = (double) insertionTotal / 50;
            double hybridAvg = (double) hybridTotal / 50;
            System.out.printf("%d\t%.2f\t\t%.2f\t\t%.2f%n",
                    n, quickAvg, insertionAvg, hybridAvg);
            if (crossover == -1 && insertionAvg < quickAvg) {
                crossover = n;
            }
        }
        if (crossover == -1){
            System.out.println("No crossover point was observed");
        }
        else{
           System.out.println("Crossover point = " + crossover); 
        }
    }
}
