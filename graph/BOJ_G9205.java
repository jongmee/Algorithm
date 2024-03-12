package graph;

import java.io.*;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class BOJ_G9205 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static boolean found = false;

    public static void main(String[] args) throws IOException {
        int testCases = parseInt(br.readLine());

        while (testCases-- > 0) {
            found = false;
            int storeCnt = parseInt(br.readLine());
            Point home = readPoint();

            Point[] stores = new Point[storeCnt];
            for (int i = 0; i < storeCnt; i++) {
                Point store = readPoint();
                stores[i] = store;
            }
            
            Point dest = readPoint();
            int totalDist = getDistance(home, dest);
            if (totalDist <= 1000) found = true;

            for (int i = 0; i < storeCnt; i++) {
                int dist = getDistance(home, stores[i]);
                if (dist <= 1000) {
                    int[] visited = new int[storeCnt];
                    visited[i] = 1;
                    dfs(i, stores, dest, visited);
                }
                if (found) break;
            }
            if (found) bw.write("happy\n");
            else bw.write("sad\n");
        }
        bw.flush();
        bw.close();
    }

    static Point readPoint() throws IOException {
        String[] input = br.readLine().split(" ");
        int x = parseInt(input[0]);
        int y = parseInt(input[1]);
        return new Point(x, y);
    }

    static int getDistance(Point p1, Point p2) {
        int xDiff = max(p1.x - p2.x, p2.x - p1.x);
        int yDiff = max(p1.y - p2.y, p2.y - p1.y);
        return xDiff + yDiff;
    }

    static void dfs(int prevIdx, Point[] stores, Point dest, int[] visited) {
        Point prev = stores[prevIdx];
        int finalDist = getDistance(dest, prev);
        if (finalDist <= 1000) {
            found = true;
        }
        for (int i = 0; i < stores.length; i++) {
            if (visited[i] == 0) {
                int dist = getDistance(prev, stores[i]);
                if (dist <= 1000) {
                    visited[i] = 1;
                    dfs(i, stores, dest, visited);
                }
            }
            if (found) break;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
