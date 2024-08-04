package implementation;

class MissingDog {
    private final int BOARD_SIZE = 10;
    private final int[] dirX = {0, 1, 0, -1};
    private final int[] dirY = {-1, 0, 1, 0};

    public int solution(int[][] board) {
        int dogTurn = 0, personTurn = 0;
        int dogX = 0, dogY = 0, personX = 0, personY = 0;
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (board[y][x] == 2) {
                    personX = x;
                    personY = y;
                } else if (board[y][x] == 3) {
                    dogX = x;
                    dogY = y;
                }
            }
        }

        int time = 0;
        while (time < 10_000) {
            time++;
            int nextDogX = dogX + dirX[dogTurn], nextDogY = dogY + dirY[dogTurn];
            int nextPersonX = personX + dirX[personTurn], nextPersonY = personY + dirY[personTurn];
            if (canGo(nextDogX, nextDogY, board)) {
                dogX = nextDogX;
                dogY = nextDogY;
            } else dogTurn = (dogTurn + 1) % 4;
            if (canGo(nextPersonX, nextPersonY, board)) {
                personX = nextPersonX;
                personY = nextPersonY;
            } else personTurn = (personTurn + 1) % 4;
            if (dogX == personX && dogY == personY) break;
        }
        if (time == 10_000) return 0;
        return time;
    }

    private boolean canGo(int x, int y, int[][] board) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && board[y][x] != 1;
    }

    public static void main(String[] args) {
        MissingDog T = new MissingDog();
        int[][] arr1 = {
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 2, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0}};
        System.out.println(T.solution(arr1));
        int[][] arr2 = {
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 2, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 3}};
        System.out.println(T.solution(arr2));
    }
}
