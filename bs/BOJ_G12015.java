package bs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_G12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        final int[] numbers = new int[N];
        for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(line[i]);

        int[] sequence = new int[N];
        sequence[0] = numbers[0];
        int lastIdx = 0;
        for (int i = 1; i < N; i++) {
            if (sequence[lastIdx] < numbers[i]) {
                sequence[++lastIdx] = numbers[i];
            } else {
                int replaceIdx = bs(numbers[i], sequence, lastIdx);
                sequence[replaceIdx] = numbers[i];
            }
        }

        bw.write((lastIdx + 1) + "\n");


        bw.flush();
        bw.close();
    }

    static int bs(int target, int[] numbers, int last) {
        int left = 0, right = last, mid = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (numbers[mid] == target) return mid;
            if (numbers[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return right + 1;
    }
}
