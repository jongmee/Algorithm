package implementation;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class BOJ_G13144 {
    static int N;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        numbers = new int[N];
        for (int i = 0; i < N; i++) numbers[i] = parseInt(input[i]);

        Map<Integer, Integer> visited = new HashMap<>(); // val, pos
        long cnt = 0;
        int firstPos = 0;
        for (int i = 0; i < N; i++) {
            if (visited.containsKey(numbers[i])) {
                int posSameVal = visited.get(numbers[i]);
                for (int j = firstPos; j <= posSameVal; j++) {
                    cnt += (i - 1 - j + 1);
                    visited.remove(numbers[j]);
                }
                firstPos = posSameVal + 1;
            }
            visited.put(numbers[i], i);
        }
        for (int i = 1; i <= visited.size(); i++) cnt += i;

        bw.write(cnt + "\n");

        bw.flush();
        bw.close();
    }
}
