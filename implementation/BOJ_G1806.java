package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_G1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), S = parseInt(inputs[1]);
        inputs = br.readLine().split(" ");
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) numbers[i] = parseInt(inputs[i]);

        Queue<Integer> q = new ArrayDeque<>();
        long sum = 0;
        int idx = 0, minLen = Integer.MAX_VALUE;
        while (idx < N) {
            if (sum < S) {
                sum += numbers[idx];
                q.add(numbers[idx++]);
            }
            while (!q.isEmpty() && sum >= S) {
                minLen = Integer.min(minLen, q.size());
                int front = q.poll();
                sum -= front;
            }
        }

        if (minLen == Integer.MAX_VALUE) bw.write("0\n");
        else bw.write(minLen + "\n");


        bw.flush();
        bw.close();
    }
}
