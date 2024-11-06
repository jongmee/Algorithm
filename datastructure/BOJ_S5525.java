package datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

import static java.lang.Integer.parseInt;

public class BOJ_S5525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int M = parseInt(br.readLine());
        String input = br.readLine();

        char[] S = new char[M];
        for (int i = 0; i < M; i++) S[i] = input.charAt(i);

        int ans = 0;
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            if (isFull(N, dq)) ans++;
            if (dq.isEmpty() && S[i] == 'I') dq.addLast(S[i]);
            else if (dq.peekLast() == 'O' && S[i] == 'I') dq.addLast(S[i]);
            else if (dq.peekLast() == 'O' && S[i] == 'O') dq.clear();
            else if (dq.peekLast() == 'I' && S[i] == 'I') {
                dq.clear();
                dq.addLast(S[i]);
            } else if (isFull(N, dq)) {
                if (S[i] == 'O') {
                    dq.pollFirst();
                    dq.pollFirst();
                    dq.addLast(S[i]);
                } else {
                    dq.clear();
                    dq.addLast(S[i]);
                }
            } else if (dq.peekLast() == 'I' && S[i] == 'O') dq.add(S[i]);
        }
        if (isFull(N, dq)) ans++;

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }

    private static boolean isFull(int N, Deque<Character> dq) {
        int rest = (dq.size() - 1) % 2;
        if (rest != 0) return false;
        return (dq.size() - 1) / 2 == N;
    }
}
