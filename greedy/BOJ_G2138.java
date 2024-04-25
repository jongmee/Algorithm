package greedy;

import java.io.*;

import static java.lang.Integer.parseInt;
import static java.lang.Math.min;

public class BOJ_G2138 {
    static int minCnt = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        String[] inputs = br.readLine().split("");

        int[] nowBulbs = new int[N];
        int[] nowBulbs2 = new int[N];
        for (int i = 0; i < N; i++) {
            nowBulbs[i] = parseInt(inputs[i]);
            nowBulbs2[i] = nowBulbs[i];
        }

        inputs = br.readLine().split("");
        int[] goalBulbs = new int[N];
        for (int i = 0; i < N; i++) goalBulbs[i] = parseInt(inputs[i]);

        // 0번째를 누르지 않는 경우
        int cnt = 0;
        find(N, nowBulbs, goalBulbs, cnt);

        // 0번째를 누르는 경우
        push(nowBulbs2, 0, N);
        cnt = 1;
        find(N, nowBulbs2, goalBulbs, cnt);

        if (minCnt == Integer.MAX_VALUE) bw.write("-1\n");
        else bw.write(minCnt + "\n");

        bw.flush();
        bw.close();
    }

    private static void find(int n, int[] nowBulbs, int[] goalBulbs, int cnt) {
        for (int i = 0; i < n - 1; i++) {
            if (notSameIn(goalBulbs, nowBulbs, i)) {
                push(nowBulbs, i + 1, n);
                cnt++;
            }
        }
        if (equals(goalBulbs, nowBulbs, n)) {
            minCnt = min(minCnt, cnt);
        }
    }

    static boolean notSameIn(int[] goalBulbs, int[] nowBulbs, int idx) {
        return goalBulbs[idx] != nowBulbs[idx];
    }

    static void push(int[] nowBulbs, int idx, int N) {
        if (idx == 0) {
            nowBulbs[idx + 1] = convert(nowBulbs[idx + 1]);
        } else if (idx == N - 1) {
            nowBulbs[idx - 1] = convert(nowBulbs[idx - 1]);
        } else {
            nowBulbs[idx + 1] = convert(nowBulbs[idx + 1]);
            nowBulbs[idx - 1] = convert(nowBulbs[idx - 1]);
        }
        nowBulbs[idx] = convert(nowBulbs[idx]);
    }

    static int convert(int num) {
        return 1 - num;
    }

    static boolean equals(int[] goalBulbs, int[] nowBulbs, int N) {
        for (int i = 0; i < N; i++) {
            if (goalBulbs[i] != nowBulbs[i]) return false;
        }
        return true;
    }
}
