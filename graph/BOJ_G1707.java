package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BOJ_G1707 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = parseInt(br.readLine());
        while (K-- > 0) {
            String[] inputs = br.readLine().split(" ");
            int V = parseInt(inputs[0]), E = parseInt(inputs[1]);
            List<Integer>[] graph = new List[V + 1];
            for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>();
            for (int i = 0; i < E; i++) {
                inputs = br.readLine().split(" ");
                int u = parseInt(inputs[0]), v = parseInt(inputs[1]);
                graph[u].add(v);
                graph[v].add(u);
            }
            int[] group = new int[V + 1]; // -1 혹은 1
            ANS = true;
            for (int i = 1; i <= V; i++) {
                if (group[i] == 0) {
                    group[i] = -1;
                    dfs(group, i, graph);
                }
                if (!ANS) break;
            }

            if (ANS) bw.write("YES\n");
            else bw.write("NO\n");
        }
        bw.flush();
        bw.close();
    }

    private static Boolean ANS;

    private static void dfs(int[] group, int startIdx, List<Integer>[] graph) {
        if (!ANS) return;

        for (int next : graph[startIdx]) {
            if (group[next] == 0) {
                group[next] = group[startIdx] * -1;
                dfs(group, next, graph);
            } else if (group[next] == group[startIdx]) {
                ANS = false;
                return;
            }
        }
    }

}
