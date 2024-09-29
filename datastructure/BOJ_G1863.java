package datastructure;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

import static java.lang.Integer.parseInt;

public class BOJ_G1863 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine()), cnt = 0;
        Deque<Integer> stk = new ArrayDeque<>();
        while (N-- > 0) {
            String[] line = br.readLine().split(" ");
            int nowY = parseInt(line[1]);
            while (!stk.isEmpty() && stk.peekLast() > nowY) {
                int last = stk.pollLast();
                if (last != 0) cnt++;
            }
            if (stk.isEmpty()) stk.addLast(nowY);
            else if (stk.peekLast() != nowY) stk.addLast(nowY);
        }
        while (!stk.isEmpty()) if (stk.pollLast() != 0) cnt++;

        bw.write(cnt + " \n");

        bw.flush();
        bw.close();
    }
}
