package implementation;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class BOJ_G2467 {
    static int MAX_ABS_SUM = 2_000_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = parseInt(inputs[i]);

        int headIdx = 0;
        int tailIdx = N - 1;
        int minAbsSum = MAX_ABS_SUM;
        List<Integer> results = new ArrayList<>();
        while (headIdx < tailIdx) {
            int sum = abs(nums[headIdx] + nums[tailIdx]);
            if (minAbsSum > sum) {
                minAbsSum = sum;
                results.clear();
                results.add(nums[headIdx]);
                results.add(nums[tailIdx]);
            }
            if (minAbsSum == 0) break;
            if (abs(nums[headIdx]) < abs(nums[tailIdx])) tailIdx--;
            else if (abs(nums[headIdx]) > abs(nums[tailIdx])) headIdx++;
        }

        Collections.sort(results);
        for (int result : results) bw.write(result + " ");

        bw.flush();
        bw.close();
    }
}
