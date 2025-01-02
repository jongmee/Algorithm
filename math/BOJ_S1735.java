package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S1735 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int A = parseInt(inputs[0]), B = parseInt(inputs[1]);
        inputs = br.readLine().split(" ");
        int C = parseInt(inputs[0]), D = parseInt(inputs[1]);

        int 분자 = A * D + C * B;
        int 분모 = B * D;
        int gcd = getGCD(분모, 분자);
        분자 /= gcd;
        분모 /= gcd;

        bw.write(분자 + " " + 분모 + "\n");

        bw.flush();
        bw.close();
    }

    private static int getGCD(int a, int b) { // 유클리드 호제법
        if (a % b == 0) return b;
        return getGCD(b, a % b);
    }
}
