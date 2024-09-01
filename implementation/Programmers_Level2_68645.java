package implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Programmers_Level2_68645 {
    private final int[] dirX = {0, 1, -1};
    private final int[] dirY = {1, 0, -1};
    private int[][] visited;
    private int N;

    public int[] solution(int n) {
        N = n;
        int maxN = 0;
        for (int i = 1; i <= n; i++) maxN += i;

        visited = new int[n][n];
        List<Point> points = new ArrayList<>();
        int x = 0, y = 0, dirIdx = 0;
        for (int num = 1; num <= maxN; num++) {
            visited[y][x] = 1;
            points.add(new Point(num, x, y));
            int nextX = x + dirX[dirIdx], nextY = y + dirY[dirIdx];
            if (!isValidIdx(nextX, nextY) || !(visited[nextY][nextX] == 0)) dirIdx = (dirIdx + 1) % 3;
            x += dirX[dirIdx];
            y += dirY[dirIdx];
        }
        Collections.sort(points);

        int[] answer = new int[maxN];
        for (int i = 0; i < maxN; i++) answer[i] = points.get(i).val;
        return answer;
    }

    private static class Point implements Comparable<Point> {
        int val, x, y;

        public Point(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (o.y > y) return -1;
            if (o.y == y && o.x > x) return -1;
            if (o.x == x && o.y == y) return 0;
            return 1;
        }
    }

    private boolean isValidIdx(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) {
        Programmers_Level2_68645 solution = new Programmers_Level2_68645();
        System.out.println(Arrays.toString(solution.solution(4)));
        System.out.println(Arrays.toString(solution.solution(5)));
        System.out.println(Arrays.toString(solution.solution(6)));
    }
}
