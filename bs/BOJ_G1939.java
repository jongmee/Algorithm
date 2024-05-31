package bs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_G1939 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]), M = Integer.parseInt(inputs[1]);

        int[] costs = new int[M];
        List<List<Bridge>> bridges = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) bridges.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int island1 = Integer.parseInt(inputs[0]);
            int island2 = Integer.parseInt(inputs[1]);
            int cost = Integer.parseInt(inputs[2]);
            costs[i] = cost;
            bridges.get(island1).add(new Bridge(island2, cost));
            bridges.get(island2).add(new Bridge(island1, cost));
        }
        inputs = br.readLine().split(" ");
        int start = Integer.parseInt(inputs[0]), dest = Integer.parseInt(inputs[1]);

        Arrays.sort(costs);
        int ans = bs(M, N, costs, start, dest, bridges);
        bw.write(ans + "\n");


        bw.flush();
        bw.close();
    }

    static int bs(int M, int N, int[] costs, int start, int dest, List<List<Bridge>> bridges) {
        int left = 0, right = costs[M - 1];
        while (left <= right) {
            int mid = (left + right) / 2;
            found = false;
            dfs(start, dest, bridges, new int[N + 1], mid);
            if (found) left = mid + 1;
            else right = mid - 1;
        }
        return right;
    }

    static boolean found = false;

    static void dfs(int start, int dest, List<List<Bridge>> bridges, int[] visited, int limit) {
        if (start == dest) {
            found = true;
            return;
        }

        for (Bridge bridge : bridges.get(start)) {
            if (visited[bridge.dest] == 0 && limit <= bridge.cost) {
                visited[bridge.dest] = 1;
                dfs(bridge.dest, dest, bridges, visited, limit);
                if (found) return;
            }
        }
    }

    static class Bridge {
        int dest, cost;

        public Bridge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}
