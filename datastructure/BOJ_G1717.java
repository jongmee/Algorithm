package datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_G1717 {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int n = parseInt(inputs[0]), m = parseInt(inputs[1]);
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;
        for (int i = 0; i < m; i++) {
            inputs = br.readLine().split(" ");
            int command = Integer.parseInt(inputs[0]), a = Integer.parseInt(inputs[1]), b = Integer.parseInt(inputs[2]);
            if (command == 0) {
                // a가 b의 parent
                setParent(a, b);
            } else if (command == 1) {
                int aParent = findParent(a);
                int bParent = findParent(b);
                if (aParent == bParent) bw.write("YES\n");
                else bw.write("NO\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static int findParent(int idx) {
        if (parent[idx] == idx) return idx;
        return parent[idx] = findParent(parent[idx]);
    }

    private static void setParent(int a, int b) {
        int aParent = findParent(a);
        int bParent = findParent(b);
        if (aParent == bParent) return;
        if (aParent < bParent) parent[bParent] = aParent;
        else parent[aParent] = bParent;
    }
}
