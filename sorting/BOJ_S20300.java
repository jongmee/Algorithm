package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BOJ_S20300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        long[] equipments = new long[N];
        for (int i = 0; i < N; i++) equipments[i] = Long.parseLong(inputs[i]);

        Arrays.sort(equipments);

        int lastIdx = N - 1;
        if (N % 2 != 0) lastIdx--;
        long max = Long.MIN_VALUE;
        for (int i = 0; i < N / 2; i++) {
            long sum = equipments[i] + equipments[lastIdx - i];
            max = Long.max(max, sum);
        }
        if (N % 2 != 0) max = Long.max(max, equipments[N - 1]);

        bw.write(max + "\n");

        bw.flush();
        bw.close();
    }
}
