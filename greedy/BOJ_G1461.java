package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BOJ_G1461 {
    static int[] numbers;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        N = parseInt(inputs[0]);
        M = parseInt(inputs[1]);
        inputs = br.readLine().split(" ");

        numbers = new int[N];
        for (int i = 0; i < N; i++) numbers[i] = parseInt(inputs[i]);

        Arrays.sort(numbers);

        bw.write(simulate() + "\n");

        bw.flush();
        bw.close();
    }


    static int simulate() {
        if (N == 1) return numbers[0];
        int sum = 0;
        if (Math.abs(numbers[N - 1]) < Math.abs(numbers[0])) {
            sum += Math.abs(numbers[0]);
            int nextIdx = M;
            while (nextIdx < N && numbers[nextIdx] * numbers[0] > 0) {
                sum += Math.abs(numbers[nextIdx]) * 2;
                nextIdx += M;
            }
            int limit = nextIdx - M;
            while (limit < N && numbers[limit] * numbers[0] > 0) limit++;
            nextIdx = N - 1;
            while (nextIdx >= limit && numbers[N - 1] * numbers[nextIdx] > 0) {
                sum += Math.abs(numbers[nextIdx]) * 2;
                nextIdx -= M;
            }
            return sum;
        }
        sum += Math.abs(numbers[N - 1]);
        int nextIdx = N - 1 - M;
        while (nextIdx >= 0 && numbers[nextIdx] * numbers[N - 1] > 0) {
            sum += Math.abs(numbers[nextIdx]) * 2;
            nextIdx -= M;
        }
        int limit = nextIdx + M;
        while (limit >= 0 && numbers[limit] * numbers[N - 1] > 0) limit--;
        nextIdx = 0;
        while (nextIdx <= limit && numbers[0] * numbers[nextIdx] > 0) {
            sum += Math.abs(numbers[nextIdx]) * 2;
            nextIdx += M;
        }
        return sum;
    }
}
