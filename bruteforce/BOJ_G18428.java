package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class BOJ_G18428 {
    static int[] dirX = {0, 0, -1, 1}; // 상하좌우
    static int[] dirY = {-1, 1, 0, 0};
    static Set<Point> uniqueCases = new HashSet<>();
    static List<Point> cases = new ArrayList<>(), students = new ArrayList<>();

    static char[][] hallway;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = parseInt(br.readLine());
        hallway = new char[N][N];
        for (int y = 0; y < N; y++) {
            String[] line = br.readLine().split(" ");
            for (int x = 0; x < N; x++) {
                hallway[y][x] = line[x].charAt(0);
                if (hallway[y][x] == 'S') students.add(new Point(x, y));
            }
        }

        for (Point studentPoint : students) {
            for (int d = 0; d < 4; d++) {
                List<Point> points = new ArrayList<>();
                for (int nextX = studentPoint.x + dirX[d], nextY = studentPoint.y + dirY[d];
                     nextX < N && nextY < N && nextY >= 0 && nextX >= 0; nextX += dirX[d], nextY += dirY[d]) {
                    if (hallway[nextY][nextX] == 'T') {
                        uniqueCases.addAll(points);
                        points.clear();
                    } else points.add(new Point(nextX, nextY));
                }
            }
        }

        if (!uniqueCases.isEmpty()) {
            int[][] obstacles = new int[N][N];
            cases = new ArrayList<>(uniqueCases);
            find(-1, obstacles, cases.size(), 0);
        }

        if (ans) bw.write("YES\n");
        else bw.write("NO\n");

        bw.flush();
        bw.close();
    }

    static boolean ans = false;

    static void find(int start, int[][] obstacles, int caseSize, int depth) {
        if (depth <= 3) {
            if (checkTeacher(obstacles)) ans = true;
        }
        if (depth > 3) return;
        for (int i = start + 1; i < caseSize; i++) {
            obstacles[cases.get(i).y][cases.get(i).x] = 1;
            find(i, obstacles, caseSize, depth + 1);
            if (ans) return;
            obstacles[cases.get(i).y][cases.get(i).x] = 0;
        }
    }

    static boolean checkTeacher(int[][] obstacles) {
        for (Point studentPoint : students) {
            for (int d = 0; d < 4; d++) {
                boolean canHide = false;
                for (int nextX = studentPoint.x + dirX[d], nextY = studentPoint.y + dirY[d];
                     nextX < N && nextY < N && nextY >= 0 && nextX >= 0; nextX += dirX[d], nextY += dirY[d]) {
                    if (obstacles[nextY][nextX] == 1) canHide = true;
                    if (hallway[nextY][nextX] == 'T' && !canHide) return false;
                }
            }
        }
        return true;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
