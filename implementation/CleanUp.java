package implementation;

import java.util.Arrays;

class CleanUp {
    int[] dirX = {1, 0, -1, 0}; // 처음에는 오른쪽 전진부터 시작 & 90도 시계방향으로 방향 바꿈
    int[] dirY = {0, 1, 0, -1};
    int turn, boardSize;

    public int[] solution(int[][] board, int k) {
        int[] answer = new int[2];
        boardSize = board.length;
        turn = 0;
        int time = 0, x = 0, y = 0;
        while (time < k) {
            time++;
            x += dirX[turn];
            y += dirY[turn];
            if (!canGo(x, y, board)) {
                x -= dirX[turn];
                y -= dirY[turn];
                turn = (turn + 1) % 4;
            }

        }
        answer[0] = y;
        answer[1] = x;
        return answer;
    }

    private boolean canGo(int x, int y, int[][] board) {
        return x >= 0 && x < boardSize && y >= 0 && y < boardSize && board[y][x] == 0;
    }

    // 벽 혹은 장애물(1로 표시)을 만나면 전진할 수 없음
    public static void main(String[] args) {
        CleanUp T = new CleanUp();
        int[][] arr1 = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(arr1, 10)));
        int[][] arr2 = {
                {0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(arr2, 20)));
        int[][] arr3 = {
                {0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(arr3, 25)));
    }
}
