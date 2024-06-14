package adhoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ_G2623 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]), M = Integer.parseInt(line[1]);

        int[][] orders = new int[M][N + 1];
        for (int m = 0; m < M; m++) {
            line = br.readLine().split(" ");
            int totalCnt = Integer.parseInt(line[0]);
            orders[m][0] = totalCnt;
            for (int i = 1; i <= totalCnt; i++) {
                orders[m][i] = Integer.parseInt(line[i]);
            }
        }

        int[] topology = new int[N + 1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for (int m = 0; m < M; m++) {
            for (int i = 1; i < orders[m][0]; i++) {
                topology[orders[m][i + 1]]++;
                graph.get(orders[m][i]).add(orders[m][i + 1]);
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        int[] visited = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (topology[i] == 0 && visited[i] == 0) {
                for (int next : graph.get(i)) topology[next]--;
                visited[i] = 1;
                q.add(i);
            }
        }

        int[] ans = new int[N];
        int ansIdx = 0;
        while (!q.isEmpty()) {
            int front = q.poll();
            ans[ansIdx++] = front;

            for (int next : graph.get(front)) {
                if (topology[next] == 0 && visited[next] == 0) {
                    for (int nextOfNext : graph.get(next)) topology[nextOfNext]--;
                    visited[next] = 1;
                    q.add(next);
                }
            }
        }

        if (ansIdx != N) bw.write("0\n");
        else for (int i = 0; i < N; i++) bw.write(ans[i] + "\n");

        bw.flush();
        bw.close();
    }
}
