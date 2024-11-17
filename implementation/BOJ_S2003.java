package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

import static java.lang.Integer.parseInt;

public class BOJ_S2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]);

        inputs = br.readLine().split(" ");
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = parseInt(inputs[i]);

        Deque<Integer> dq = new ArrayDeque<>();
        int total = nums[0], idx = 1, cnt = 0;
        dq.addLast(nums[0]);
        while (idx < N) {
            if (total > M) total -= dq.pollFirst();
            else if (total == M) {
                cnt++;
                total -= dq.pollFirst();
            } else if (total < M) {
                total += nums[idx];
                dq.addLast(nums[idx++]);
            }
        }

        if (total == M) cnt++;
        while (!dq.isEmpty()) {
            total -= dq.pollFirst();
            if (total == M) cnt++;
        }

        bw.write(cnt + "\n");

        bw.flush();
        bw.close();
    }
}
