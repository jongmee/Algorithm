package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_G1043 {
    /*
    1. 최대한 거짓말한다.
    2. 진실을 아는 사람이 있으면 진실을 말한다.
    3. 어떤 사람을 일관된 정보(모두 거짓, 모두 진실)를 듣는다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]);

        inputs = br.readLine().split(" ");
        int intelCnt = Integer.parseInt(inputs[0]);
        int[] intels = new int[intelCnt];
        for (int i = 1; i <= intelCnt; i++) intels[i - 1] = Integer.parseInt(inputs[i]);

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) graph.put(i, new ArrayList<>());
        int[][] parties = new int[M][51];
        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int peopleCnt = Integer.parseInt(inputs[0]);
            int[] people = new int[peopleCnt + 1];
            people[0] = peopleCnt;
            for (int j = 1; j <= peopleCnt; j++) people[j] = Integer.parseInt(inputs[j]);
            for (int j = 1; j <= peopleCnt; j++) {
                for (int k = 1; k <= peopleCnt; k++) {
                    if (j == k) continue;
                    graph.get(people[j]).add(people[k]);
                }
            }
            parties[i] = people;
        }

        int[] visited = new int[N + 1];
        int[] TFs = new int[N + 1]; // 1이면 진실, 0이면 거짓
        for (int i = 0; i < intelCnt; i++) {
            Queue<Integer> q = new ArrayDeque<>();
            q.add(intels[i]);
            TFs[intels[i]] = 1;
            while (!q.isEmpty()) {
                int front = q.poll();
                for (int next : graph.get(front)) {
                    if (visited[next] == 0) {
                        visited[next] = 1;
                        q.add(next);
                        TFs[next] = 1;
                    }
                }
            }
        }

        int trueCnt = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 1; j <= parties[i][0]; j++) {
                int person = parties[i][j];
                if (TFs[person] == 1) {
                    trueCnt++;
                    break;
                }
            }
        }

        bw.write((M - trueCnt) + "\n");

        bw.flush();
        bw.close();
    }
}
