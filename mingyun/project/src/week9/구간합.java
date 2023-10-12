package week9;

import java.util.Arrays;

public class 구간합 {
    static long[] divSum = new long[10];
    static long getSum(long a, int length) {
        long divder = 1;
        for (int i=0;i<length-1;i++) divder *= 10;
        if (a/10 == 0) return divSum[(int)a];

        long v1 = 45 * ((a / divder) * (long)(Math.pow(10, length-2)));
        long v2 = ((a%divder)/(divder/10)) * (long)(Math.pow(10, length-2)) + 1;
        long v3 = getSum(a % divder, length-1);
        return v1 + v2 + v3;
    }

    static long getSum2(long a) {
        long sum = 0;
        for (int i=0;i<=a;i++) {
            int temp= i;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
        }
        return sum;
    }

    static int getLength(long a) {
        int cnt = 0;
        while (a > 0) {
            cnt++;
            a/=10;
        }
        return cnt;
    }

    public static void main(String[] args) {
        long a = 33-1; long b = 133;

        divSum[0] = 0;
        for (int i=1;i<10;i++) {
            divSum[i] = i + divSum[i-1];
        }
        System.out.println(Arrays.toString(divSum));
        System.out.println(45 * 6);
        System.out.println(getSum(b,getLength(b)) );
        System.out.println("끄아악" + (getSum2(133) - getSum2(32)));
        System.out.println("끄아악2 " + (getSum2(100)));
        System.out.println(getSum(b,getLength(b)) - getSum(a,getLength(a)));
    }
}
