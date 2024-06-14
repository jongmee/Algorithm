package linear;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BOJ_S1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] sequence = new int[N + 1];
        sequence[0] = -1;
        for (int i = 0; i < N; i++) sequence[i + 1] = Integer.parseInt(br.readLine());

        Deque<Integer> stk = new ArrayDeque<>();
        List<Character> ans = new ArrayList<>();
        int num = 1;
        boolean can = true;
        for (int i = 1; i <= N; i++) {
            if (sequence[i - 1] < sequence[i]) {
                while (num < sequence[i]) {
                    stk.addLast(num++);
                    ans.add('+');
                }
                ans.add('+');
                ans.add('-');
                num++;
            } else {
                if (stk.isEmpty() || stk.peekLast() != sequence[i]) {
                    can = false;
                    break;
                }
                stk.pollLast();
                ans.add('-');
            }
        }

        if (can) for (char op : ans) bw.write(op + "\n");
        else bw.write("NO\n");


        bw.flush();
        bw.close();
    }
}
