package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_G9019 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            String[] inputs = br.readLine().split(" ");
            int A = parseInt(inputs[0]), B = parseInt(inputs[1]);
            int[] visited = new int[10000];

            Queue<Node> q = new ArrayDeque<>();
            q.add(new Node(0, A, List.of()));
            visited[A] = 1;
            while (!q.isEmpty()) {
                Node front = q.poll();
                if (front.nowA == B) {
                    for (char c : front.seq) bw.write(String.valueOf(c));
                    bw.write("\n");
                    break;
                }
                int d = D(front.nowA);
                if (visited[d] == 0) {
                    visited[d] = 1;
                    List<Character> newSeq = new ArrayList<>(front.seq);
                    newSeq.add('D');
                    q.add(new Node(front.cnt + 1, d, newSeq));
                }
                int s = S(front.nowA);
                if (visited[s] == 0) {
                    visited[s] = 1;
                    List<Character> newSeq = new ArrayList<>(front.seq);
                    newSeq.add('S');
                    q.add(new Node(front.cnt + 1, s, newSeq));
                }
                int l = L(front.nowA);
                if (visited[l] == 0) {
                    visited[l] = 1;
                    List<Character> newSeq = new ArrayList<>(front.seq);
                    newSeq.add('L');
                    q.add(new Node(front.cnt + 1, l, newSeq));
                }
                int r = R(front.nowA);
                if (visited[r] == 0) {
                    visited[r] = 1;
                    List<Character> newSeq = new ArrayList<>(front.seq);
                    newSeq.add('R');
                    q.add(new Node(front.cnt + 1, r, newSeq));
                }
            }
        }
        bw.flush();
        bw.close();
    }


    private static int D(int n) {
        return n * 2 % 10000;
    }

    private static int S(int n) {
        if (n == 0) return 9999;
        return n - 1;
    }

    private static int L(int n) {
        String number = String.valueOf(n);
        while (number.length() < 4) number = '0' + number;
        char first = number.charAt(0);
        String result = number.substring(1) + first;
        return parseInt(result);
    }

    private static int R(int n) {
        String number = String.valueOf(n);
        while (number.length() < 4) number = '0' + number; // 0001??
        char last = number.charAt(number.length() - 1);
        String result = last + number.substring(0, number.length() - 1);
        return parseInt(result);
    }

    private static class Node {
        int cnt, nowA;
        List<Character> seq;

        public Node(int cnt, int nowA, List<Character> seq) {
            this.cnt = cnt;
            this.nowA = nowA;
            this.seq = seq;
        }
    }
}
