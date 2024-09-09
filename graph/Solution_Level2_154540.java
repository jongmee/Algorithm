package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution_Level2_154540 {
    private static int[] dirX = {0, 0, -1, 1}; // 상하좌우
    private static int[] dirY = {-1, 1, 0, 0};
    private int ySize, xSize;
    private int[][] map;


    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        ySize = maps.length;
        xSize = maps[0].length();
        map = new int[ySize][xSize];
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if (maps[y].charAt(x) == 'X') map[y][x] = -1;
                else map[y][x] = maps[y].charAt(x) - '0';
            }
        }
        int[][] visited = new int[ySize][xSize];
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if (visited[y][x] == 0 && map[y][x] != -1) {
                    visited[y][x] = 1;
                    result = map[y][x];
                    dfs(visited, x, y);
                    answer.add(result);
                }
            }
        }
        if (answer.isEmpty()) return new int[]{-1};
        Collections.sort(answer);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private int result;

    private void dfs(int[][] visited, int startX, int startY) {
        for (int dir = 0; dir < 4; dir++) {
            int nextX = startX + dirX[dir], nextY = startY + dirY[dir];
            if (isValidIdx(nextX, nextY) && visited[nextY][nextX] == 0 && map[nextY][nextX] != -1) {
                visited[nextY][nextX] = 1;
                result += map[nextY][nextX];
                dfs(visited, nextX, nextY);
            }
        }
    }

    private boolean isValidIdx(int x, int y) {
        return x >= 0 && y >= 0 && x < xSize && y < ySize;
    }

    public static void main(String[] args) {
        Solution_Level2_154540 solution = new Solution_Level2_154540();
        System.out.println(Arrays.toString(solution.solution((new String[]{"X591X", "X1X5X", "X231X", "1XXX1"}))));
        System.out.println(Arrays.toString(solution.solution((new String[]{"X1X", "XXX", "456"}))));
    }
}
