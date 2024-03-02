package dp;

import java.io.*;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BOJ_G2293 {
    /*
    1. 입력된 동전의 가치를 정렬한다.
    2. dp 테이블을 채운다. (경우의 수에 대한)
        x: 0 부터 최종 k 원까지
        y: 입력된 동전 가치들 (n개)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int n = parseInt(inputs[0]), k = parseInt(inputs[1]);

        int[][] combinations = new int[n][k + 1];

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) coins[i] = parseInt(br.readLine());
        Arrays.sort(coins);

        for (int valueIdx = 1; valueIdx <= k; valueIdx++) {
            if (valueIdx % coins[0] == 0) combinations[0][valueIdx] = 1;
        }

        for (int coinsIdx = 1; coinsIdx < n; coinsIdx++) {
            for (int valueIdx = 1; valueIdx <= k; valueIdx++) {
                if (valueIdx == coins[coinsIdx]) combinations[coinsIdx][valueIdx] += 1;
                combinations[coinsIdx][valueIdx] += combinations[coinsIdx - 1][valueIdx];
                int prevValue = valueIdx - coins[coinsIdx];
                if (prevValue > 0) combinations[coinsIdx][valueIdx] += combinations[coinsIdx][prevValue];
            }
        }

        bw.write(String.valueOf(combinations[n - 1][k]));

        bw.flush();
        bw.close();
    }
}
