package datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

import static java.lang.Integer.parseInt;

public class BOJ_G17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");

        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) numbers[i] = parseInt(inputs[i]);

        int[] ans = new int[N];
        ans[N - 1] = -1;
        int prev = numbers[N - 1], startIdx = -1;
        for (int i = N - 2; i >= 0; i--) {
            if (prev > numbers[i]) {
                startIdx = i + 1;
                break;
            }
            ans[i] = -1;
            prev = numbers[i];
        }
        if (startIdx == -1) {
            for (int a : ans) bw.write(a + " ");
            bw.flush();
            bw.close();
            return;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(numbers[startIdx]);
        for (int i = startIdx - 1; i >= 0; i--) {
            if (deque.peekFirst() > numbers[i]) {
                ans[i] = deque.peekFirst();
                deque.addFirst(numbers[i]);
            } else {
                while (!deque.isEmpty() && deque.peekFirst() <= numbers[i]) deque.pollFirst();
                if (deque.isEmpty()) ans[i] = -1;
                else ans[i] = deque.peekFirst();
                deque.addFirst(numbers[i]);
            }
        }

        for (int a : ans) bw.write(a + " ");
        bw.flush();
        bw.close();
    }
}
