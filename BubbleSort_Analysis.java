import java.util.Random;
public class BubbleSort_Analysis{
    static long normalComparision;
    static long optimizedComparision;
    static void normalBubbleSort(int [] a){
        int n = a.length;
        for(int i=0; i<n-1; i++){
            for(int j=0; j<n-1-i; j++){
                normalComparision++;
                if(a[j]>a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }
    static void optimizedBubbleSort(int [] a){
        int n = a.length;
        for(int i=0; i<n-1; i++){
            boolean flag = false;
            for(int j=0; j<n-1-i; j++){
                optimizedComparision++;
                if(a[j]>a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = true;
                }
            }
            if(!flag)
                break;
        }
    }
    static int[] copyArray(int [] a){
        int [] b = new int[a.length];
        for(int i=0; i<a.length; i++){
            b[i]=a[i];
        }
        return b;
    }
    public static void main (String[] args){
        Random r = new Random();
        int [] size = {100,500,1000,2000};
        int iterations = 50;
        System.out.println("Size"+"\t"+"Normal Avg."+"\t"+"Optimal Avg.");
        for(int n:size){
            long normalTotal = 0;
            long optimaizedTotal = 0;
            for(int i=0; i<iterations; i++){
                int [] original = new int[n];
                for(int j=0; j<n; j++){
                    original[j] = r.nextInt(10000);
                }
                int [] a = copyArray(original);
                int [] b = copyArray(original);

                normalComparision = 0;
                normalBubbleSort(a);
                normalTotal += normalComparision;

                optimizedComparision = 0;
                optimizedBubbleSort(b);
                optimaizedTotal += optimizedComparision;
            }
            double normalAverage = (double)(normalTotal)/(double)(iterations);
            double optimaizedAverage = (double)(optimaizedTotal)/(double)(iterations);
            System.out.printf("%-8d %-18.2f %-18.2f%n",n, normalAverage, optimaizedAverage);
        }

    }
}