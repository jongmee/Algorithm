package adhoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BOJ_G1644 {
    static int ans;
    static List<Integer> primes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int[] numbers = new int[N + 1]; // 0 이면 소수
        numbers[1] = 1;
        for (int i = 2; i * i <= N; i++) {
            for (int j = i * 2; j <= N; j += i) numbers[j] = 1;
        }

        primes = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (numbers[i] == 0) primes.add(i);
        }

        int size = primes.size();
        for (int idx = 0; idx < size; idx++) {
            int sum = 0;
            for (int nextIdx = idx; nextIdx < size; nextIdx++) {
                sum += primes.get(nextIdx);
                if (sum > N) break;
                if (sum == N) ans++;
            }
        }

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }
}
