package graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BOJ_G13549 {
    static final int MAX_POSITION = 100_000;
    static final int[] dir = {2, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), K = parseInt(inputs[1]);
        if (N >= K) {
            bw.write(N - K + "\n");
            bw.flush();
            bw.close();
            return;
        }

        Point result = find(N, K);

        bw.write(result.time + "\n");

        bw.flush();
        bw.close();
    }

    static Point find(int N, int K) {
        int[] visited = new int[MAX_POSITION + 1];
        Queue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(N, 0));
        while (!pq.isEmpty()) {
            Point front = pq.poll();
            visited[front.position] = 1;
            if (front.position == K) {
                return front;
            }
            for (int d : dir) {
                int nextPosition = -1, nextTime = -1;
                if (d == 2) {
                    nextPosition = front.position * d;
                    nextTime = front.time;
                } else {
                    nextPosition = front.position + d;
                    nextTime = front.time + 1;
                }
                if (nextPosition > 0 && nextPosition <= Math.min(MAX_POSITION, K * 2) && visited[nextPosition] == 0)
                    pq.add(new Point(nextPosition, nextTime));
            }
        }
        return null;
    }

    static class Point implements Comparable<Point> {
        int position, time;

        public Point(int position, int time) {
            this.position = position;
            this.time = time;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(time, o.time);
        }
    }
}
