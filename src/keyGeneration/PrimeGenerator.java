package keyGeneration;

public class PrimeGenerator {
    public static long genPrime(int maxNumberToGenerateFor) {
        long ret=-1;
        boolean A[] = new boolean[maxNumberToGenerateFor];
        A=mk(A);

        for (int i=2;i<Math.sqrt(maxNumberToGenerateFor);i++) {
            if (A[i] == true) {
                for (int j=i*i;j<maxNumberToGenerateFor;j+=i) {
                    A[j] = false;
                }
            }
        }

        for (int i=A.length-1;i>0;i--) {
            if (A[i] == true) {
                ret = i;
                break;
            }
        }
        return ret;
    }

    private static boolean[] mk (boolean[] tb){
        for (int i=0;i<tb.length;i++) {
            tb[i]=true;
        }
        return tb;
    }
}
