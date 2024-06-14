package adhoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_S1929 {
    /*
    에라토스테네스의 체
    : 제곱근까지만 약수를 검증
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]), M = Integer.parseInt(line[1]);
        int[] numbers = new int[M + 1];
        numbers[1] = 1;
        for (int n = 2; n * n <= M; n++) {
            if (numbers[n] == 1) continue;
            for (int i = n * 2; i <= M; i += n) numbers[i] = 1;
        }

        for (int n = N; n <= M; n++) if (numbers[n] == 0) bw.write(n + "\n");

        bw.flush();
        bw.close();
    }
}
