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

import static java.lang.Integer.parseInt;

public class BOJ_G2252 {
    static int[] counts, visited;
    static List<List<Integer>> orders;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]);
        counts = new int[N + 1];
        visited = new int[N + 1];
        orders = new ArrayList<>();
        for (int i = 0; i <= N; i++) orders.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int first = parseInt(inputs[0]), second = parseInt(inputs[1]);
            counts[second]++;
            orders.get(first).add(second);
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (counts[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int front = q.poll();
            if (counts[front] == 0) {
                for (int next : orders.get(front)) {
                    if (visited[next] == 0) {
                        visited[next] = 1;
                        q.add(next);
                    }
                    counts[next]--;
                }
                bw.write(front + " ");
            } else q.add(front);
        }

        bw.flush();
        bw.close();
    }
}
