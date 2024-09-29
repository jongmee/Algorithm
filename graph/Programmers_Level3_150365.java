package graph;

class Programmers_Level3_150365 {
    // 아래, 왼, 오, 위
    private static final int[] dirX = {1, 0, 0, -1};
    private static final int[] dirY = {0, -1, 1, 0};
    private static final char[] dirChar = {'d', 'l', 'r', 'u'};

    private int[][] maze;

    public String solution(int n, int m, int startX, int startY, int endX, int endY, int totalCost) {
        startX--;
        startY--;
        endY--;
        endX--;
        ans = "";
        tmp = new StringBuilder();
        maze = new int[n][m];
        dfs(totalCost, 0, endX, endY, startX, startY, n, m);
        return ans.isEmpty() ? "impossible" : ans;
    }

    private String ans = "";
    private StringBuilder tmp;

    private void dfs(int totalCost, int cost, int endX, int endY, int x, int y, int n, int m) {
        if (!ans.isEmpty()) return;
        int restDist = calculateDist(x, y, endX, endY), restCost = totalCost - cost;
        if (restDist > restCost) return;
        if ((restDist % 2 == 0 && restCost % 2 != 0) || (restDist % 2 != 0 && restCost % 2 == 0)) return; // ㅜㅜ

        if (cost >= totalCost) {
            if (x == endX && y == endY) {
                ans = tmp.toString();
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (!isValidIdx(x + dirX[i], y + dirY[i], n, m)) continue;
            tmp.append(dirChar[i]);
            dfs(totalCost, cost + 1, endX, endY, x + dirX[i], y + dirY[i], n, m);
            tmp.deleteCharAt(tmp.length() - 1);
        }
    }

    private int calculateDist(int x, int y, int endX, int endY) {
        return Math.abs(x - endX) + Math.abs(y - endY);
    }

    private boolean isValidIdx(int x, int y, int n, int m) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}
