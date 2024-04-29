package graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BOJ_G2668 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        Map<Integer, Integer> table = new HashMap<>(N);
        for (int i = 1; i <= N; i++) table.put(i, parseInt(br.readLine()));

        int[] candidates = new int[N + 1];
        for (int i = 1; i <= N; i++)
            bfs(N, table, i, candidates);

        int totalCnt = 0;
        for (int i = 1; i <= N; i++)
            if (candidates[i] != 0) totalCnt++;

        bw.write(totalCnt + "\n");
        for (int i = 1; i <= N; i++)
            if (candidates[i] != 0) bw.write(i + "\n");

        bw.flush();
        bw.close();
    }

    static void bfs(int N, Map<Integer, Integer> table, int startIdx, int[] candidates) {
        int[] visited = new int[N + 1];
        Set<Integer> keys = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();

        visited[table.get(startIdx)] = 1;
        keys.add(startIdx);
        q.add(table.get(startIdx));

        boolean found = false;
        int[] tmpCandidates = new int[N + 1];
        while (!q.isEmpty()) {
            int front = q.poll();
            tmpCandidates[front] = 1;

            if (keys.contains(front)) keys.remove(front);
            if (keys.isEmpty()) {
                found = true;
                break;
            }

            if (visited[table.get(front)] == 0) {
                visited[table.get(front)] = 1;
                q.add(table.get(front));
            }
        }
        if (found) checkCandidate(candidates, tmpCandidates, N);
    }

    static void checkCandidate(int[] globalVisited, int[] tmp, int N) {
        for (int i = 1; i <= N; i++) {
            if (tmp[i] == 1) globalVisited[i] = 1;
        }
    }
}
