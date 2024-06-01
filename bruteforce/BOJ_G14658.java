package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class BOJ_G14658 {
    static int[] dirX = {1, 1, -1, -1};
    static int[] dirY = {1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]), L = parseInt(inputs[2]), K = parseInt(inputs[3]);

        int[] Xs = new int[K];
        int[] Ys = new int[K];
        for (int i = 0; i < K; i++) {
            inputs = br.readLine().split(" ");
            int x = parseInt(inputs[0]), y = parseInt(inputs[1]);
            Xs[i] = x;
            Ys[i] = y;
        }

        int maxCnt = -1;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                int x = Xs[i], y = Ys[j];
                for (int dir = 0; dir < 4; dir++) {
                    int cnt = 0;
                    int lastX = x + dirX[dir] * L;
                    int lastY = y + dirY[dir] * L;
                    for (int k = 0; k < K; k++) {
                        if (checkInRange(x, lastX, Xs[k]) && checkInRange(y, lastY, Ys[k])) cnt++;
                    }
                    maxCnt = max(maxCnt, cnt);
                }
            }
        }

        bw.write((K - maxCnt) + "\n");
        bw.flush();
        bw.close();
    }

    static boolean checkInRange(int start, int end, int target) {
        if (start < end) {
            return start <= target && target <= end;
        }
        return start >= target && target >= end;
    }
}
