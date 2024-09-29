package datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class BOJ_G4195 {
    private static Map<String, Integer> names = new HashMap<>();
    private static int idx;
    private static int[] parents = new int[100_001 * 2];
    private static int[] cnts = new int[100_001 * 2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            int F = parseInt(br.readLine());
            Arrays.fill(parents, 0);
            Arrays.fill(cnts, 0);
            names.clear();
            idx = 0;
            while (F-- > 0) {
                String[] friends = br.readLine().split(" ");
                if (!names.containsKey(friends[0])) {
                    parents[idx] = idx;
                    names.put(friends[0], idx++);
                }
                if (!names.containsKey(friends[1])) {
                    parents[idx] = idx;
                    names.put(friends[1], idx++);
                }
                int friend1 = names.get(friends[0]), friend2 = names.get(friends[1]);
                union(friend1, friend2);
                int parent = find(friend1);
                bw.write(cnts[parent] + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static int find(int friend) {
        if (parents[friend] == friend) return friend;
        return parents[friend] = find(parents[friend]);
    }

    private static void union(int friend1, int friend2) {
        int parent1 = find(friend1);
        int parent2 = find(friend2);
        if (parent1 == parent2) return;
        int parent1Cnt = cnts[parent1] == 0 ? 1 : cnts[parent1];
        int parent2Cnt = cnts[parent2] == 0 ? 1 : cnts[parent2];
        parents[parent2] = parent1;
        cnts[parent1] = parent1Cnt + parent2Cnt;
    }
}
