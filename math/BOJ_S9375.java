package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class BOJ_S9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            int N = parseInt(br.readLine());
            Map<String, Integer> total = new HashMap<>();
            while (N-- > 0) {
                String[] inputs = br.readLine().split(" ");
                if (total.containsKey(inputs[1])) {
                    int cnt = total.get(inputs[1]);
                    total.put(inputs[1], cnt + 1);
                } else {
                    total.put(inputs[1], 1);
                }
            }

            int ans = 1;
            for (int cnt : total.values()) ans *= (cnt + 1);

            bw.write((ans - 1) + "\n");
        }
        bw.flush();
        bw.close();
    }
}
