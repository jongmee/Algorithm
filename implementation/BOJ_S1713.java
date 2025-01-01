package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;

public class BOJ_S1713 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int CNT = parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");

        int[] likeCnt = new int[101];
        PriorityQueue<Candidate> pq = new PriorityQueue<>();
        int age = 0;
        for (int i = 0; i < CNT; i++) {
            int studentNumber = parseInt(inputs[i]);
            likeCnt[studentNumber] += 1;

            if (pq.size() < N && likeCnt[studentNumber] == 1) {
                pq.add(new Candidate(age, 1, studentNumber));
            } else if (pq.size() == N && likeCnt[studentNumber] == 1) {
                while (pq.peek().cnt != likeCnt[pq.peek().number]) {
                    Candidate front = pq.poll();
                    front.cnt = likeCnt[front.number];
                    pq.add(front);
                }

                Candidate remove = pq.poll();
                likeCnt[remove.number] = 0;
                pq.add(new Candidate(age, 1, studentNumber));
            }
            age++;
        }

        List<Integer> finalCandidates = new ArrayList<>();
        while (!pq.isEmpty()) finalCandidates.add(pq.poll().number);
        Collections.sort(finalCandidates);

        for (int c : finalCandidates) bw.write(c + " ");
        bw.write("\n");

        bw.flush();
        bw.close();
    }

    private static class Candidate implements Comparable<Candidate> {
        int age, cnt, number;

        public Candidate(int age, int cnt, int number) {
            this.age = age;
            this.cnt = cnt;
            this.number = number;
        }

        @Override
        public int compareTo(Candidate o) {
            if (o.cnt == this.cnt) return Integer.compare(this.age, o.age);
            return Integer.compare(this.cnt, o.cnt);
        }
    }
}
