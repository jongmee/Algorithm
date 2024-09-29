package datastructure;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ_G9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split("");
        char[] full = new char[input.length];
        for (int i = 0; i < input.length; i++) full[i] = input[i].charAt(0);
        input = br.readLine().split("");
        char[] bomb = new char[input.length];
        for (int i = 0; i < input.length; i++) bomb[i] = input[i].charAt(0);

        Deque<Char> stk = new ArrayDeque<>();
        for (int i = 0; i < full.length; i++) {
            if (stk.isEmpty()) {
                if (full[i] == bomb[0]) stk.addLast(new Char(full[i], 0));
                else stk.addLast(new Char(full[i], -1));
            } else {
                Char prev = stk.getLast();
                if (bomb[prev.seq + 1] == full[i]) stk.addLast(new Char(full[i], prev.seq + 1));
                else if (full[i] == bomb[0]) stk.addLast(new Char(full[i], 0));
                else stk.addLast(new Char(full[i], -1));
            }
            if (stk.getLast().seq == bomb.length - 1) {
                while (stk.getLast().seq != 0) stk.pollLast();
                stk.pollLast();
            }
        }

        if (stk.isEmpty()) bw.write("FRULA");
        else while (!stk.isEmpty()) bw.write(stk.pollFirst().val);

        bw.flush();
        bw.close();
    }

    static class Char {
        char val;
        int seq;

        public Char(char val, int seq) {
            this.val = val;
            this.seq = seq;
        }
    }
}
