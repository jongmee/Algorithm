package greedy;

import java.io.*;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BOJ_S17615 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        String[] inputs = br.readLine().split("");
        char[] balls = new char[N];
        for (int i = 0; i < N; i++) balls[i] = inputs[i].charAt(0);

        int reverseRedCount = calculateMovementCount('R', balls.clone(), N, true);
        int reverseBlueCount = calculateMovementCount('B', balls.clone(), N, true);
        int redCount = calculateMovementCount('R', balls.clone(), N, false);
        int blueCount = calculateMovementCount('B', balls.clone(), N, false);

        int[] cnts = {reverseRedCount, reverseBlueCount, redCount, blueCount};
        Arrays.sort(cnts);

        bw.write(cnts[0] + "\n");

        bw.flush();
        bw.close();
    }

    static int targetIdx;
    static int otherIdx;
    static int cnt;

    static int calculateMovementCount(char targetColor, char[] balls, int N, boolean reverse) {
        targetIdx = -1;
        otherIdx = -1;
        cnt = 1;
        findFirstIdx(targetColor, balls, N, reverse);
        if (targetIdx == -1) return 0;
        swap(balls, targetIdx, otherIdx);
        otherIdx = targetIdx;
        findNext(targetColor, balls, N, reverse);
        return cnt;
    }

    static void findFirstIdx(char targetColor, char[] balls, int N, boolean reverse) {
        boolean can = false;
        if (reverse) {
            for (int i = N - 1; i >= 0; i--) {
                if (balls[i] != targetColor && !can) {
                    can = true;
                    otherIdx = i;
                }
                if (balls[i] == targetColor && can) {
                    targetIdx = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                if (balls[i] != targetColor && !can) {
                    can = true;
                    otherIdx = i;
                }
                if (balls[i] == targetColor && can) {
                    targetIdx = i;
                    break;
                }
            }
        }
    }

    static void findNext(char targetColor, char[] balls, int N, boolean reverse) {
        if (reverse) {
            int startIdx = otherIdx - 1;
            for (int i = startIdx; i >= 0; i--) {
                if (balls[i] == targetColor) {
                    swap(balls, i, otherIdx);
                    otherIdx--;
                    cnt++;
                }
            }
        } else {
            int startIdx = otherIdx + 1;
            for (int i = startIdx; i < N; i++) {
                if (balls[i] == targetColor) {
                    swap(balls, i, otherIdx);
                    otherIdx++;
                    cnt++;
                }
            }
        }
    }

    static void swap(char[] balls, int idx1, int idx2) {
        if (balls[idx1] == 'R') {
            balls[idx1] = 'B';
            balls[idx2] = 'R';
            return;
        }
        balls[idx1] = 'R';
        balls[idx2] = 'B';
    }
}
