package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class BOJ_S30804 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");

        int[] tanghuru = new int[N];
        for (int i = 0; i < N; i++) {
            tanghuru[i] = parseInt(inputs[i]);
        }

        Set<Integer> fruitTypes = new HashSet<>();
        Deque<Integer> dq = new ArrayDeque<>();

        int ans = -1;
        int[] fruits = new int[10];
        for (int i = 0; i < N; i++) {
            fruitTypes.add(tanghuru[i]);
            fruits[tanghuru[i]] += 1;
            dq.addLast(tanghuru[i]);
            while (fruitTypes.size() > 2) {
                int fruit = dq.pollFirst();
                fruits[fruit] -= 1;
                if (fruits[fruit] == 0) fruitTypes.remove(fruit);
            }
            ans = Integer.max(ans, dq.size());
        }

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }

}
