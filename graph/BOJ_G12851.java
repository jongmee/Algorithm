package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_G12851 {
    private static int N, K, MAX = 100_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        N = parseInt(inputs[0]);
        K = parseInt(inputs[1]);

        Queue<Integer> q = new ArrayDeque<>();
        int[] time = new int[MAX];
        q.add(N);

        int minTime = Integer.MAX_VALUE, cnt = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            if (time[now] > minTime) continue;
            if (now == K && time[now] <= minTime) {
                cnt++;
                minTime = time[now];
                continue;
            }

            if (now + 1 < MAX && (time[now] + 1 == time[now + 1] || time[now + 1] == 0)) {
                time[now + 1] = time[now] + 1;
                q.add(now + 1);
            }
            if (now * 2 < MAX && (time[now] + 1 == time[now * 2] || time[now * 2] == 0)) {
                time[now * 2] = time[now] + 1;
                q.add(now * 2);
            }
            if (now - 1 >= 0 && (time[now] + 1 == time[now-1] || time[now - 1] == 0)) {
                time[now - 1] = time[now] + 1;
                q.add(now - 1);
            }
        }

        bw.write(minTime + "\n" + cnt + "\n");
        bw.flush();
        bw.close();
    }
}
