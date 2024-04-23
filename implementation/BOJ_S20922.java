package implementation;

import java.io.*;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class BOJ_S20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), K = parseInt(inputs[1]);

        inputs = br.readLine().split(" ");
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) numbers[i] = parseInt(inputs[i]);
        int[] counts = new int[100_001];

        int startIdx = 0;
        int endIdx = 0;
        int maxLen = -1;
        while (endIdx < N) {
            while (endIdx < N && counts[numbers[endIdx]] < K) {
                counts[numbers[endIdx]]++;
                endIdx++;
            }
            maxLen = max(maxLen, endIdx - startIdx);
            counts[numbers[startIdx]]--;
            startIdx++;
        }

        bw.write(maxLen + "\n");

        bw.flush();
        bw.close();
    }
}
