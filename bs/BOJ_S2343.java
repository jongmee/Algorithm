package bs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S2343 {
    private static int[] lectures;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        N = parseInt(inputs[0]);
        M = parseInt(inputs[1]);

        inputs = br.readLine().split(" ");
        lectures = new int[N];
        long totalLength = 0;
        for (int i = 0; i < N; i++) {
            lectures[i] = parseInt(inputs[i]);
            totalLength += lectures[i];
        }

        bw.write(bs(totalLength) + "\n");

        bw.flush();
        bw.close();
    }

    private static long bs(long totalLength) {
        long left = 0, right = totalLength + 1;
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (check(mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private static boolean check(long nowLen) {
        long tmpSum = 0;
        int cnt = 1;
        for (int i = 0; i < N; i++) {
            if (tmpSum + lectures[i] > nowLen) {
                tmpSum = lectures[i];
                cnt++;
            } else tmpSum += lectures[i];
            if (cnt > M || tmpSum > nowLen) return false;
        }
        return true;
    }
}
