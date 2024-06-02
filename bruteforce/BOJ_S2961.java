package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;

public class BOJ_S2961 {
    /*
    모든 재료의 조합을 확인.
     */
    static int minDiff = Integer.MAX_VALUE, N;
    static int[][] ingredients;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = parseInt(br.readLine());
        ingredients = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            ingredients[i][0] = parseInt(inputs[0]);
            ingredients[i][1] = parseInt(inputs[1]);
        }
        for (int cnt = 1; cnt <= N; cnt++) dfs(0, cnt, -1, 0, 1);
        bw.write(minDiff + "\n");
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int cnt, int start, int BSum, int SSum) {
        if (depth == cnt) {
            minDiff = min(minDiff, Math.abs(BSum - SSum));
            return;
        }
        for (int i = start + 1; i < N; i++) {
            dfs(depth + 1, cnt, i, BSum + ingredients[i][1], SSum * ingredients[i][0]);
        }
    }
}
