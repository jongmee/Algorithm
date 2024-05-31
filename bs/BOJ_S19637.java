package bs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ_S19637 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]), M = Integer.parseInt(inputs[1]);

        Map<Integer, String> names = new HashMap<>();
        int[] powers = new int[N];
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            powers[i] = Integer.parseInt(inputs[1]);
            if (!names.containsKey(powers[i]))
                names.put(powers[i], inputs[0]);
        }
        Arrays.sort(powers);
        for (int i = 0; i < M; i++) {
            int score = Integer.parseInt(br.readLine());
            int idx = Arrays.binarySearch(powers, score);
            if ((idx < 0)) {
                idx *= -1;
                idx -= 1;
            }
            bw.write(names.get(powers[idx]) + "\n");
        }

        bw.flush();
        bw.close();
    }
}
