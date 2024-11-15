package datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class BOJ_G16724 {
    private static int[] dirC = {0, 0, -1, 1}, dirR = {-1, 1, 0, 0};
    private static int N, M;
    private static Node[][] parent;
    private static char[][] map;
    private static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        N = parseInt(inputs[0]);
        M = parseInt(inputs[1]);

        parent = new Node[N][M];
        map = new char[N][M];
        visited = new int[N][M];
        for (int r = 0; r < N; r++) {
            String line = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = line.charAt(c);
                parent[r][c] = new Node(r, c);
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                getParent(r, c);
            }
        }

        Set<Node> parents = new HashSet<>();
        for (int r = 0; r < N; r++)
            for (int c = 0; c < M; c++) parents.add(parent[r][c]);

        bw.write(parents.size() + "\n");

        bw.flush();
        bw.close();
    }

    private static Node getParent(int r, int c) {
        if (visited[r][c] == 1) return parent[r][c];
        visited[r][c] = 1;
        int dir = getDir(map[r][c]);
        int nextR = r + dirR[dir], nextC = c + dirC[dir];
        return parent[r][c] = getParent(nextR, nextC);
    }

    private static int getDir(char c) {
        if (c == 'U') return 0;
        if (c == 'D') return 1;
        if (c == 'L') return 2;
        return 3;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
