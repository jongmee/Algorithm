package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_S2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int computerCount = parseInt(br.readLine());
        int connectCount = parseInt(br.readLine());
        List<List<Integer>> connections = new ArrayList<>(computerCount + 1);
        for (int i = 0; i < computerCount + 1; i++) connections.add(new ArrayList<>());

        for (int i = 0; i < connectCount; i++) {
            String[] connection = br.readLine().split(" ");
            int computer1 = parseInt(connection[0]);
            int computer2 = parseInt(connection[1]);

            connections.get(computer1).add(computer2);
            connections.get(computer2).add(computer1);
        }

        int result = 0;
        int[] visited = new int[computerCount + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = 1;
        while (!q.isEmpty()) {
            int front = q.poll();
            result++;
            for (int i = 0; i < connections.get(front).size(); i++) {
                int newComputer = connections.get(front).get(i);
                if (visited[newComputer] == 0) {
                    q.add(newComputer);
                    visited[newComputer] = 1;
                }
            }
        }

        bw.write(String.valueOf(result - 1));

        bw.flush();
        bw.close();
    }
}
