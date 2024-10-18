package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BOJ_G11054 {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");

        int[] sequence = new int[N], reverse = new int[N];
        for (int i = 0; i < N; i++) {
            sequence[i] = parseInt(inputs[i]);
            reverse[N - i - 1] = sequence[i];
        }

        int[] up = fillTable(sequence);
        int[] down = fillTable(reverse);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            int cnt = up[i] + down[N - i - 1];
            if (up[i] != 0 && down[N - i - 1] != 0) cnt--;
            ans = Integer.max(ans, cnt);
        }

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }

    private static int[] fillTable(int[] sequence) {
        int[] table = new int[N];
        Arrays.fill(table, 1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i]) table[i] = Integer.max(table[j] + 1, table[i]);
            }
        }
        return table;
    }
}
