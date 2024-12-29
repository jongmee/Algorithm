package tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_S11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());

        List<Integer>[] tree = new List[N + 1];
        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            String[] inputs = br.readLine().split(" ");
            int a = parseInt(inputs[0]), b = parseInt(inputs[1]);
            tree[a].add(b);
            tree[b].add(a);
        }

        int[] parents = new int[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        parents[1] = -1;
        while (!q.isEmpty()) {
            int parent = q.poll();

            for (int child : tree[parent]) {
                if (parents[child] == 0) {
                    parents[child] = parent;
                    q.add(child);
                }
            }
        }

        for (int child = 2; child <= N; child++) bw.write(parents[child] + "\n");

        bw.flush();
        bw.close();
    }
}
