package implementation;

import java.util.Arrays;

class Programmers_Level2_169199 {
    private int yLimit, xLimit, goalX, goalY;
    private char[][] board;
    private int[][] record;
    private int[] dirX = {0, 0, -1, 1}; // 상하좌우
    private int[] dirY = {-1, 1, 0, 0};

    public int solution(String[] inputBoard) {
        yLimit = inputBoard.length;
        xLimit = inputBoard[0].length();
        board = new char[yLimit][xLimit];
        record = new int[yLimit][xLimit];
        for (int y = 0; y < yLimit; y++) Arrays.fill(record[y], Integer.MAX_VALUE);
        int startX = -1, startY = -1;
        for (int y = 0; y < yLimit; y++) {
            for (int x = 0; x < xLimit; x++) {
                board[y][x] = inputBoard[y].charAt(x);
                if (board[y][x] == 'R') {
                    startX = x;
                    startY = y;
                } else if (board[y][x] == 'G') {
                    goalX = x;
                    goalY = y;
                }
            }
        }
        record[startY][startX] = 0;
        move(startX, startY, 0);
        if (record[goalY][goalX] == Integer.MAX_VALUE) return -1;
        return record[goalY][goalX];
    }

    private void move(final int startX, final int startY, int cnt) {
        for (int dir = 0; dir < 4; dir++) {
            int nextX = startX, nextY = startY;
            while (isValidIdx(nextX + dirX[dir], nextY + dirY[dir])
                    && board[nextY + dirY[dir]][nextX + dirX[dir]] != 'D') {
                nextX += dirX[dir];
                nextY += dirY[dir];
            }
            if (record[nextY][nextX] > cnt + 1) {
                record[nextY][nextX] = cnt + 1;
                move(nextX, nextY, cnt + 1);
            }
        }
    }

    private boolean isValidIdx(int x, int y) {
        return x >= 0 && y >= 0 && x < xLimit && y < yLimit;
    }

    public static void main(String[] args) {
        Programmers_Level2_169199 solution = new Programmers_Level2_169199();
        System.out.println(solution.solution(new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."}));
        System.out.println(solution.solution(new String[]{"G..", "..R", "...", "..."}));
    }
}
