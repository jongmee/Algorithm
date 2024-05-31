package bs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_G3079 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]), M = Integer.parseInt(inputs[1]);

        long[] times = new long[N];
        for (int i = 0; i < N; i++) times[i] = Long.parseLong(br.readLine());

        Arrays.sort(times);

        long left = 0, right = times[0] * M;
        while (left <= right) {
            long midTime = right + ((left - right) / 2);
            long peopleCnt = 0;
            for (long time : times) peopleCnt += midTime / time;
            if (peopleCnt >= M) right = midTime - 1;
            else left = midTime + 1;
        }

        bw.write(left + "\n");

        bw.flush();
        bw.close();
    }
}
