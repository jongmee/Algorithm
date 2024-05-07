package graph;

import java.io.*;

public class BOJ_G1987 {
    static int[] dirX = {0, 0, -1, 1}; //  상하좌우
    static int[] dirY = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int R = Integer.parseInt(inputs[0]), C = Integer.parseInt(inputs[1]);
        char[][] board = new char[R][C];
        for (int r = 0; r < R; r++) {
            inputs = br.readLine().split("");
            for (int c = 0; c < C; c++) board[r][c] = inputs[c].charAt(0);
        }

        int[] visited = new int[30];
        visited[board[0][0] - 'A'] = 1;
        dfs(board, 0, 0, 1, R, C, visited);

        bw.write(maxCnt + "\n");

        bw.flush();
        bw.close();
    }

    static int maxCnt = 0;

    static void dfs(char[][] board, int startX, int startY, int cnt, int R, int C, int[] visited) {
        maxCnt = Math.max(cnt, maxCnt);
        for (int dir = 0; dir < 4; dir++) {
            int nextX = startX + dirX[dir];
            int nextY = startY + dirY[dir];
            if (nextX >= C || nextY >= R || nextX < 0 || nextY < 0) continue;
            char next = board[nextY][nextX];
            if (visited[next - 'A'] == 0) {
                visited[next - 'A'] = 1;
                dfs(board, nextX, nextY, cnt + 1, R, C, visited);
                visited[next - 'A'] = 0;
            }
        }
    }
}
