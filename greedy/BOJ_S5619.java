package greedy;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_S5619 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int[] inputs = new int[N];
        for (int i = 0; i < N; i++) inputs[i] = parseInt(br.readLine());

        Arrays.sort(inputs);

        Queue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < 4 && i < inputs.length; i++) {
            for (int j = 0; j < 4 && j < inputs.length; j++) {
                if (i == j) continue;
                int digit = digit(inputs[j]);
                int result = square(inputs[i], digit + 1) + inputs[j];
                pq.add(result);
            }
        }

        for (int i = 0; i < 2; i++) pq.poll();
        bw.write(pq.poll() + "\n");

        bw.flush();
        bw.close();
    }

    static int digit(int val) {
        int cnt = 0;
        while (val / 10 > 0) {
            val /= 10;
            cnt++;
        }
        return cnt;
    }

    static int square(int val, int cnt) {
        while (cnt-- > 0) val *= 10;
        return val;
    }
}
