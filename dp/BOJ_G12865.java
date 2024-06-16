package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class BOJ_G12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), K = parseInt(inputs[1]);

        Item[] items = new Item[N];
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            items[i] = new Item(parseInt(inputs[0]), parseLong(inputs[1]));
        }

        long[][] dp = new long[N + 1][K + 1];
        for (int i = 1; i <= N; i++) { // 물건 번호
            for (int j = 1; j <= K; j++) { // 무게
                Item item = items[i - 1];
                if (j - item.weight >= 0)
                    dp[i][j] = Long.max(dp[i - 1][j], dp[i - 1][j - item.weight] + item.value);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        bw.write(dp[N][K] + "\n");

        bw.flush();
        bw.close();
    }

    static class Item implements Comparable<Item> {
        int weight;
        long value;

        public Item(int weight, long value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Item o) {
            return Long.compare(weight, o.weight);
        }
    }
}
