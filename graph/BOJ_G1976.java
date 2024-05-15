package graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BOJ_G1976 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine()), M = parseInt(br.readLine());
        Map<Integer, List<Integer>> cities = new HashMap<>();
        for (int i = 0; i < N; i++)
            cities.put(i, new ArrayList<>());

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = i; j < N; j++) {
                int exist = parseInt(line[j]);
                if (exist == 1) {
                    cities.get(i).add(j);
                    cities.get(j).add(i);
                }
            }
        }

        String[] line = br.readLine().split(" ");
        int[] plan = new int[M];
        for (int i = 0; i < M; i++)
            plan[i] = parseInt(line[i]) - 1;

        boolean found = true;
        for (int i = 1; i < M; i++) {
            found = bfs(cities, plan[i - 1], plan[i], N);
            if (!found) break;
        }

        if (found) bw.write("YES\n");
        else bw.write("NO\n");

        bw.flush();
        bw.close();
    }

    static boolean bfs(Map<Integer, List<Integer>> cities, int start, int goal, int N) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] visited = new int[N];
        visited[start] = 1;
        q.add(start);
        while (!q.isEmpty()) {
            int front = q.poll();
            if (front == goal) return true;
            for (int next : cities.get(front)) {
                if (visited[next] == 0) {
                    visited[next] = 1;
                    q.add(next);
                }
            }
        }
        return false;
    }
}
