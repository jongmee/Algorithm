package greedy;

import java.io.*;

import static java.lang.Integer.parseInt;

public class BOJ_S11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), K = parseInt(inputs[1]);

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) coins[i] = parseInt(br.readLine());

        int cnt = 0;
        for (int i = N - 1; i >= 0; i--) {
            int coin = coins[i];
            int availableCoinsCnt = K / coin;
            if (availableCoinsCnt > 0) {
                cnt += availableCoinsCnt;
                K -= availableCoinsCnt * coin;
            }
        }

        bw.write(String.valueOf(cnt));

        bw.flush();
        bw.close();
    }
}
