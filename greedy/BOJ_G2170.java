package greedy;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BOJ_G2170 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());

        String[] input;
        int x, y;
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            x = parseInt(input[0]);
            y = parseInt(input[1]);
            points.add(new Point(x, y));
        }
        Collections.sort(points);

        int endXPoint = points.get(0).x;
        int endYPoint = points.get(0).y;
        int totalLength = endYPoint - endXPoint;
        for (int i = 1; i < N; i++) {
            if (points.get(i).x > endYPoint) {
                endXPoint = points.get(i).x;
                endYPoint = points.get(i).y;
                totalLength += (endYPoint - endXPoint);
            } else {
                if (points.get(i).x < endXPoint) {
                    totalLength += (endXPoint - points.get(i).x);
                    endXPoint = points.get(i).x;
                }
                if (points.get(i).y > endYPoint) {
                    totalLength += (points.get(i).y - endYPoint);
                    endYPoint = points.get(i).y;
                }
            }
        }

        bw.write(String.valueOf(totalLength));

        bw.flush();
        bw.close();
    }

    static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point point) {
            if (x < point.x) return -1;
            if (x == point.x && y < point.y) return -1;
            if (x == point.x && y == point.y) return 0;
            return 1;
        }
    }
}
