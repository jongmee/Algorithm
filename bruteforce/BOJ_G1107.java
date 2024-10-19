package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_G1107 {
    private static int len, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        N = parseInt(input);
        len = input.length();
        int[] NArr = new int[len];
        String[] inputs = input.split("");
        for (int i = 0; i < len; i++) NArr[i] = parseInt(inputs[i]);

        int M = parseInt(br.readLine());
        int[] brokenButton = new int[10];
        if (M != 0) {
            inputs = br.readLine().split(" ");
            for (String str : inputs) {
                int button = parseInt(str);
                brokenButton[button] = 1;
            }
        }

        // + - 로만 가는 경우
        int ans = Math.abs(100 - N);

        // 숫자를 눌러서 가는 경우
        if (M > 0 && M < 10) {
            CNT = Integer.MAX_VALUE;
            findNearestChannel(brokenButton, 0, 0);
            ans = Integer.min(ans, CNT);
        } else if (M == 0) ans = Integer.min(ans, len);

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }

    private static int CNT;

    private static void findNearestChannel(int[] brokenButton, int nowLen, int nowChannel) {
        if (nowLen != 0 && Math.abs(N - nowChannel) + nowLen < CNT) {
            CNT = Math.abs(N - nowChannel) + nowLen;
        }
        if (nowLen == len + 2) return;
        for (int button = 0; button < 10; button++) {
            if (brokenButton[button] == 1) continue;
            findNearestChannel(brokenButton, nowLen + 1, nowChannel * 10 + button);
        }
    }
}
