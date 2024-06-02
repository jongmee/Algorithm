package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_G2636 {
    static int MELTED = -1, CHEESE = 1, NOTHING = 0, R, C, cheeseCnt;
    static int[] dirX = {0, 0, -1, 1}, dirY = {-1, 1, 0, 0};
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        R = parseInt(inputs[0]);
        C = parseInt(inputs[1]);
        board = new int[R][C];
        cheeseCnt = 0;
        for (int r = 0; r < R; r++) {
            inputs = br.readLine().split(" ");
            for (int c = 0; c < C; c++) {
                board[r][c] = parseInt(inputs[c]);
                if (board[r][c] == CHEESE) cheeseCnt++;
            }
        }

        int tryCnt = 1, prevCnt = -1;
        while (cheeseCnt > 0) {
            prevCnt = cheeseCnt;
            melt(tryCnt++);
        }

        bw.write((tryCnt - 1) + "\n");
        bw.write(prevCnt + "\n");

        bw.flush();
        bw.close();
    }

    static void melt(int tryCnt) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0));
        int[][] visited = new int[R][C];
        visited[0][0] = 1;
        while (!q.isEmpty()) {
            Node front = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = front.x + dirX[i], nextY = front.y + dirY[i];
                if (checkIdx(nextX, nextY) && visited[nextY][nextX] == 0) {
                    if (board[nextY][nextX] == NOTHING || (board[nextY][nextX] < 0 && board[nextY][nextX] != MELTED * tryCnt)) {
                        visited[nextY][nextX] = 1;
                        q.add(new Node(nextX, nextY));
                    } else if (board[nextY][nextX] != MELTED * tryCnt) {
                        board[nextY][nextX] = MELTED * tryCnt;
                        cheeseCnt--;
                    }
                }
            }
        }
    }

    static boolean checkIdx(int x, int y) {
        return x >= 0 && x < C && y >= 0 && y < R;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
