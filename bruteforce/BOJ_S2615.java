package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S2615 {
    static int BOARD_SIZE = 19;
    static int[] dirX = {-1, 0, 1, 1, 1, 0, -1, -1}; // 0 - 4, 1 - 5, 2 - 6, 3 - 7
    static int[] dirY = {-1, -1, -1, 0, 1, 1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int y = 0; y < BOARD_SIZE; y++) {
            String[] inputs = br.readLine().split(" ");
            for (int x = 0; x < BOARD_SIZE; x++) board[y][x] = parseInt(inputs[x]);
        }

        find(board, bw);

        bw.flush();
        bw.close();
    }

    static void find(int[][] board, BufferedWriter bw) throws IOException {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (board[y][x] != 0) {
                    for (int i = 0; i < 8; i++) {
                        int nextX = x + dirX[i], nextY = y + dirY[i];
                        int cnt = 1;
                        while (checkInRange(nextX, nextY) && board[nextY][nextX] == board[y][x]) {
                            cnt++;
                            nextX += dirX[i];
                            nextY += dirY[i];
                        }
                        if (cnt == 5 && !checkOpposite(i, board, x, y)) {
                            nextX -= dirX[i];
                            nextY -= dirY[i];
                            bw.write(board[y][x] + "\n");
                            if (nextX < x) bw.write((nextY + 1) + " " + (nextX + 1));
                            else bw.write((y + 1) + " " + (x + 1) + "\n");
                            return;
                        }
                    }
                }
            }
        }
        bw.write("0\n");
    }

    static boolean checkOpposite(int dirIdx, int[][] board, int startX, int startY) {
        int oppositeIdx = dirIdx + 4;
        if (oppositeIdx >= 8) oppositeIdx -= 8;
        int nextX = startX + dirX[oppositeIdx], nextY = startY + dirY[oppositeIdx];
        return checkInRange(nextX, nextY) && board[nextY][nextX] == board[startY][startX];
    }

    static boolean checkInRange(int x, int y) {
        return x < BOARD_SIZE && x >= 0 && y < BOARD_SIZE && y >= 0;
    }
}
