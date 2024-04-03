package greedy;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BOJ_S2232 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int[] inputs = new int[N];
        Queue<Point> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            inputs[i] = parseInt(br.readLine());
            pq.add(new Point(inputs[i], i));
        }

        int[] visited = new int[N];
        Queue<Integer> result = new PriorityQueue<>();
        while (!pq.isEmpty()) {
            Point front = pq.poll();
            if (visited[front.idx] == 0) {
                result.add(front.idx + 1);
                visited[front.idx] = 1;
                int prevVal = front.val;
                for (int i = front.idx + 1; i < N; i++) {
                    if (inputs[i] < prevVal) {
                        visited[i] = 1;
                        prevVal = inputs[i];
                    } else break;
                }
                prevVal = front.val;
                for (int i = front.idx - 1; i >= 0; i--) {
                    if (inputs[i] < prevVal) {
                        visited[i] = 1;
                        prevVal = inputs[i];
                    } else break;
                }
            }
        }

        while (!result.isEmpty()) bw.write(result.poll() + "\n");

        bw.flush();
        bw.close();
    }

    static class Point implements Comparable<Point> {
        int val, idx;

        public Point(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(o.val, val);
        }
    }
}
