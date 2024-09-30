package datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_P9938 {
    private static int[] parent; // 현재 위치 (서랍)
    private static boolean[] position;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), L = parseInt(inputs[1]);
        position = new boolean[L + 1];
        parent = new int[L + 1];
        for (int i = 1; i <= L; i++) parent[i] = i;
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            int A = parseInt(inputs[0]), B = parseInt(inputs[1]);
            boolean can = true;
            if (!position[A]) {
                position[A] = true;
                union(A, B, true);
            } else if (!position[B]) {
                position[B] = true;
                union(A, B, false);
            } else if (!position[find(A)]) {
                position[find(A)] = true;
                union(A, B, true);
            } else if (!position[find(B)]) {
                position[find(B)] = true;
                union(A, B, false);
            } else can = false;
            if (can) bw.write("LADICA\n");
            else bw.write("SMECE\n");
        }
        bw.flush();
        bw.close();
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int A, int B, boolean bIsParent) {
        int parentA = find(A);
        int parentB = find(B);
        if (parentA == parentB) return;
        if (bIsParent) parent[parentA] = parentB;
        else parent[parentB] = parentA;
    }
}
