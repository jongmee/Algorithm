package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S1080 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]);

        int[][] A = new int[N][M], DIFF = new int[N][M], FINAL = new int[N][M];
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split("");
            for (int j = 0; j < M; j++) A[i][j] = parseInt(inputs[j]);
        }
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                int B = parseInt(inputs[j]);
                if (A[i][j] != B) DIFF[i][j] = 1;
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (FINAL[i][j] % 2 != DIFF[i][j]) {
                    if (N < i + 3 || M < j + 3) continue;
                    cnt++;
                    for (int tmpI = i; tmpI < i + 3; tmpI++) {
                        for (int tmpJ = j; tmpJ < j + 3; tmpJ++) {
                            FINAL[tmpI][tmpJ] = toggle(FINAL[tmpI][tmpJ]);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (FINAL[i][j] % 2 != DIFF[i][j]) {
                    bw.write("-1\n");
                    bw.flush();
                    bw.close();
                    return;
                }
            }
        }

        bw.write(cnt + "\n");
        bw.flush();
        bw.close();
    }

    private static int toggle(int n) {
        if (n == 1) return 0;
        return 1;
    }
}
