package implementation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BOJ_G7682 {
    static final int BOARD_SIZE = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split("");
        while (!inputs[0].equals("e")) {
            char[][] board = initializeBoard(inputs);
            int emptyCnt = count('.', board);
            int OCnt = count('O', board);
            int XCnt = count('X', board);

            List<Character> winners = findWinners(board);
            int OWinnerCnt = countWinners(winners, 'O');
            int XWinnerCnt = countWinners(winners, 'X');
            if (emptyCnt != 0) { // 꽉 차지 않았을 경우
                if (OWinnerCnt == XWinnerCnt) bw.write("invalid\n"); // 둘 다 질 경우
                else if (OWinnerCnt * XWinnerCnt != 0) bw.write("invalid\n"); // 둘 다 이길 경우
                else if (XWinnerCnt == 0 && OCnt != XCnt) bw.write("invalid\n"); // O가 이김 -> 반드시 개수가 같음
                else if (OWinnerCnt == 0 && XCnt - OCnt != 1) bw.write("invalid\n"); // X가 이김 -> X가 하나더 많음
                else bw.write("valid\n");
            } else { // 꽉 찼을 경우
                if (!(XCnt == 5 && OCnt == 4)) bw.write("invalid\n"); // 개수 고정
                else if (OWinnerCnt * XWinnerCnt != 0) bw.write("invalid\n"); // 둘 다 이길 경우
                else if (OWinnerCnt != 0 && XWinnerCnt == 0) bw.write("invalid\n"); // O가 이기는 경우
                else bw.write("valid\n");
            }
            inputs = br.readLine().split("");
        }

        bw.flush();
        bw.close();
    }

    static char[][] initializeBoard(String[] inputs) {
        char[][] board = new char[BOARD_SIZE + 2][BOARD_SIZE + 2];
        for (int i = 1; i <= BOARD_SIZE; i++) {
            for (int j = 1; j <= BOARD_SIZE; j++) {
                board[i][j] = inputs[(i - 1) * BOARD_SIZE + j - 1].charAt(0);
            }
        }
        return board;
    }

    static int count(char target, char[][] board) {
        int cnt = 0;
        for (int i = 1; i <= BOARD_SIZE; i++) {
            for (int j = 1; j <= BOARD_SIZE; j++) {
                if (board[i][j] == target) cnt++;
            }
        }
        return cnt;
    }

    static List<Character> findWinners(char[][] board) {
        List<Character> winners = new ArrayList<>();

        if (board[1][1] == board[2][2] && board[2][2] == board[3][3]) winners.add(board[1][1]);
        if (board[1][3] == board[2][2] && board[2][2] == board[3][1]) winners.add(board[1][3]);

        for (int i = 1; i <= BOARD_SIZE; i++) {
            char prev = board[i][1];
            boolean flag = false;
            for (int j = 2; j <= BOARD_SIZE; j++) {
                if (board[i][j] == prev) flag = true;
                else {
                    flag = false;
                    break;
                }
            }
            if (flag) winners.add(prev);
        }

        for (int i = 1; i <= BOARD_SIZE; i++) {
            char prev = board[1][i];
            boolean flag = false;
            for (int j = 2; j <= BOARD_SIZE; j++) {
                if (board[j][i] == prev) flag = true;
                else {
                    flag = false;
                    break;
                }
            }
            if (flag) winners.add(prev);
        }

        return winners;
    }

    static int countWinners(List<Character> winners, char target) {
        int cnt = 0;
        for (char winner : winners) {
            if (winner == target) cnt++;
        }
        return cnt;
    }
}
