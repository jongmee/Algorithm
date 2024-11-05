package implementation;

import java.util.ArrayList;
import java.util.List;

class Programmers_Level2_17679 {
    int[] dirR = {0, 0, 1, 1}, dirC = {0, 1, 0, 1};

    int R, C;
    char[][] BOARD;

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        R = m;
        C = n;
        BOARD = new char[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                BOARD[r][c] = board[r].charAt(c);
            }
        }

        while (true) {
            List<Integer> pointsR = new ArrayList<>(), pointsC = new ArrayList<>();
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (BOARD[r][c] != '-' && check(r, c)) {
                        pointsR.add(r);
                        pointsC.add(c);
                    }
                }
            }

            int pointsSize = pointsC.size();
            if (pointsSize == 0) break;
            for (int i = 0; i < pointsSize; i++) {
                int rootR = pointsR.get(i), rootC = pointsC.get(i);
                for (int j = 0; j < 4; j++) {
                    if (BOARD[rootR + dirR[j]][rootC + dirC[j]] != '-') {
                        BOARD[rootR + dirR[j]][rootC + dirC[j]] = '-';
                        answer++;
                    }
                }
            }

            for (int c = 0; c < C; c++) {
                for (int r = R - 2; r >= 0; r--) {
                    if (BOARD[r][c] != '-') {
                        int tmpR = r + 1;
                        while (tmpR > 0 && tmpR < R && BOARD[tmpR][c] == '-') tmpR++;
                        if (tmpR - 1 != r) {
                            BOARD[tmpR - 1][c] = BOARD[r][c];
                            BOARD[r][c] = '-';
                        }
                    }
                }
            }
        }
        return answer;
    }

    boolean check(int rootR, int rootC) {
        for (int i = 0; i < 4; i++) {
            int nextR = rootR + dirR[i], nextC = rootC + dirC[i];
            if (!isValid(nextR, nextC)) return false;
            if (BOARD[rootR][rootC] != BOARD[nextR][nextC]) return false;
        }
        return true;
    }

    boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    void printBoard() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                System.out.print(BOARD[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
