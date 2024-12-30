package adhoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BOJ_S4948 {
    private static int MAX = 123_456 * 2;
    private static int[] primes = new int[MAX + 1]; // 소수면 1
    private static long[] accumulatedSum = new long[MAX + 1];

    static {
        Arrays.fill(primes, 1);
        // 에라토스테네스의 체
        for (int i = 2; i * i <= MAX; i++) {
            for (int j = i * 2; j <= MAX; j += i) {
                primes[j] = 0;
            }
        }

        // 누적합
        for (int i = 1; i <= MAX; i++) {
            accumulatedSum[i] = accumulatedSum[i - 1] + primes[i];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = -1;
        while ((n = parseInt(br.readLine())) != 0) {
            long cntN = accumulatedSum[n];
            long cnt2N = accumulatedSum[2 * n];
            bw.write((cnt2N - cntN) + "\n");
        }

        bw.flush();
        bw.close();
    }
}
