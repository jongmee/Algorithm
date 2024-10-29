package graph;

class Programmers_Level2_1829 {
    int[] dirX = {0, 0, -1, 1}, dirY = {-1, 1, 0, 0};

    int numberOfArea, maxSizeOfOneArea, M, N;

    public int[] solution(int M, int N, int[][] picture) {
        this.M = M;
        this.N = N;
        numberOfArea = 0;
        maxSizeOfOneArea = 0;

        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                if (picture[m][n] != -1 && picture[m][n] != 0) {
                    area = 1;
                    int color = picture[m][n];
                    picture[m][n] = -1;
                    dfs(n, m, color, picture);
                    numberOfArea++;
                    maxSizeOfOneArea = Integer.max(maxSizeOfOneArea, area);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    int area;

    void dfs(int startX, int startY, int color, int[][] picture) {

        for (int dir = 0; dir < 4; dir++) {
            int nextX = startX + dirX[dir], nextY = startY + dirY[dir];
            if (!isValid(nextX, nextY)) continue;
            if (picture[nextY][nextX] == color) {
                picture[nextY][nextX] = -1;
                area++;
                dfs(nextX, nextY, color, picture);
            }
        }
    }

    boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
