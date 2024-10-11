package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BOJ_G1520 {
    private static int[] dirR = {-1, 1, 0, 0};
    private static int[] dirC = {0, 0, -1, 1};

    private static int R, C;
    private static int[][] map, check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        R = parseInt(inputs[0]);
        C = parseInt(inputs[1]);

        map = new int[R][C];
        check = new int[R][C];

        for (int r = 0; r < R; r++) Arrays.fill(check[r], -1); // 갈 수 없는 곳과 구분하기 위함

        for (int r = 0; r < R; r++) {
            inputs = br.readLine().split(" ");
            for (int c = 0; c < C; c++) map[r][c] = parseInt(inputs[c]);
        }

        check[R - 1][C - 1] = 1;
        dfs(0, 0);

        bw.write(check[0][0] + "\n");

        bw.flush();
        bw.close();
    }

    private static int dfs(int r, int c) {
        if (check[r][c] != -1 && !(r == 0 && c == 0)) return check[r][c];
        int sum = 0;
        for (int dir = 0; dir < 4; dir++) {
            int nextR = r + dirR[dir], nextC = c + dirC[dir];
            if (isValid(nextR, nextC) && map[nextR][nextC] < map[r][c]) {
                sum += dfs(nextR, nextC);
            }
        }
        return check[r][c] = sum;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
