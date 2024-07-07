package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class BOJ_G10159 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int M = parseInt(br.readLine());

        List<Set<Integer>> outTree = new ArrayList<>();
        List<Set<Integer>> inTree = new ArrayList<>();
        for (int n = 0; n <= N; n++) outTree.add(new HashSet<>());
        for (int n = 0; n <= N; n++) inTree.add(new HashSet<>());

        for (int m = 0; m < M; m++) {
            String[] line = br.readLine().split(" ");
            int first = parseInt(line[0]), second = parseInt(line[1]);
            outTree.get(first).add(second);
            inTree.get(second).add(first);
        }

        for (int n = 1; n <= N; n++) {
            int intCnt = bfs(inTree, n, N);
            int outCnt = bfs(outTree, n, N);
            int ans = N - intCnt - outCnt - 1;
            bw.write(ans + "\n");
        }
        bw.flush();
        bw.close();
    }

    static int bfs(List<Set<Integer>> tree, int target, int N) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] visited = new int[N + 1];
        for (int first : tree.get(target)) {
            q.add(first);
            visited[first] = 1;
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            int front = q.poll();
            cnt++;
            for (int next : tree.get(front)) {
                if (visited[next] == 0) {
                    visited[next] = 1;
                    q.add(next);
                }
            }
        }
        return cnt;
    }
}
