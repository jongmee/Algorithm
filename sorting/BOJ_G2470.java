package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_G2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");

        Solution[] solutions = new Solution[N];
        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(line[i]);
            solutions[i] = new Solution(val, Math.abs(val));
        }

        Arrays.sort(solutions);

        int minDiff = Integer.MAX_VALUE;
        int startIdx = -1;
        for (int i = 0; i < N - 1; i++) {
            int sum = solutions[i].val + solutions[i + 1].val;
            if (sum < 0) sum *= -1;
            if (minDiff > sum) {
                minDiff = sum;
                startIdx = i;
            }
        }

        int first = solutions[startIdx].val;
        int second = solutions[startIdx + 1].val;
        if (first > second) {
            int tmp = first;
            first = second;
            second = tmp;
        }
        bw.write(first + " " + second + "\n");

        bw.flush();
        bw.close();
    }

    static class Solution implements Comparable<Solution> {
        int val, abs;

        public Solution(int val, int abs) {
            this.val = val;
            this.abs = abs;
        }

        @Override
        public int compareTo(Solution o) {
            return Integer.compare(abs, o.abs);
        }
    }
}
