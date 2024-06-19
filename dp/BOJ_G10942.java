package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_G10942 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        int[] sequence = new int[N];
        for (int i = 0; i < N; i++) sequence[i] = Integer.parseInt(line[i]);

        int[][] isPalindrome = new int[N][N];
        for (int i = N / 2; i >= 0; i--) {
            checkPalindrome(sequence, isPalindrome, i);
        }
        for (int i = N / 2 + 1; i < N; i++) {
            checkPalindrome(sequence, isPalindrome, i);
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (sequence[i] != sequence[j]) break;
                isPalindrome[i][j] = 1;
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            line = br.readLine().split(" ");
            int first = Integer.parseInt(line[0]), second = Integer.parseInt(line[1]);
            bw.write(isPalindrome[first - 1][second - 1] + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void checkPalindrome(int[] sequence, int[][] isPalindrome, int i) {
        isPalindrome[i][i] = 1;
        for (int j = 1; j < N; j++) {
            if (!isValidIdx(i - j, i + j)) break;
            if (sequence[i - j] == sequence[i + j] && isPalindrome[i - j + 1][i + j - 1] == 1) {
                isPalindrome[i - j][i + j] = 1;
            } else {
                break;
            }
        }
    }

    private static boolean isValidIdx(int a, int a1) {
        return a >= 0 && a < N && a1 >= 0 && a1 < N;
    }
}
