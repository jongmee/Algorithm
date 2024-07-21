package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;

public class BOJ_G11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        Lecture[] lectures = new Lecture[N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int s = parseInt(line[0]), t = parseInt(line[1]);
            lectures[i] = new Lecture(s, t);
        }

        Arrays.sort(lectures);

        int cnt = 1;
        PriorityQueue<Integer> assigned = new PriorityQueue<>();
        assigned.add(lectures[0].t);
        Deque<Integer> tmp = new ArrayDeque<>();
        for (int i = 1; i < N; i++) {
            Lecture lecture = lectures[i];
            int front = assigned.poll();
            if (front > lecture.s) {
                cnt++;
                assigned.add(front);
                assigned.add(lecture.t);
            } else {
                assigned.add(lecture.t);
            }
        }

        bw.write(cnt + "\n");


        bw.flush();
        bw.close();
    }

    static class Lecture implements Comparable<Lecture> {
        int s, t;

        public Lecture(int s, int t) {
            this.s = s;
            this.t = t;
        }

        @Override
        public int compareTo(Lecture o) {
            return Integer.compare(s, o.s);
        }
    }
}
