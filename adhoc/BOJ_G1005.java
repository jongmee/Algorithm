package adhoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BOJ_G1005 {
    static List<List<Integer>> tree;
    static int[] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            String[] line = br.readLine().split(" ");
            int N = parseInt(line[0]), K = parseInt(line[1]);

            times = new int[N + 1];
            record = new int[N + 1];
            line = br.readLine().split(" ");
            for (int i = 0; i < N; i++) times[i + 1] = Integer.parseInt(line[i]);

            tree = new ArrayList<>(N + 1);
            for (int i = 0; i < N + 1; i++) tree.add(new ArrayList<>());
            for (int i = 0; i < K; i++) {
                line = br.readLine().split(" ");
                int first = parseInt(line[0]), second = parseInt(line[1]);
                tree.get(second).add(first);
            }
            int W = parseInt(br.readLine());

            bw.write((dfs(W) + 1) + "\n");
        }
        bw.flush();
        bw.close();
    }

    static int[] record;

    static int dfs(int start) {
        if (record[start] != 0) return record[start];
        int sum = -1;
        for (int prev : tree.get(start)) {
            sum = Integer.max(dfs(prev), sum);
        }
        record[start] = sum + times[start];
        return record[start];
    }
}
