package implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Programmers_Level2_12946 {
    private static final List<int[]> arr = new ArrayList<>();

    public int[][] solution(int n) {
        hanoiTower(n, 1, 2, 3);
        return arr.toArray(int[][]::new);
    }

    private void hanoiTower(int n, int from, int by, int to) {
        if (n == 0) return;
        hanoiTower(n - 1, from, to, by);
        arr.add(new int[]{from, to});
        hanoiTower(n - 1, by, from, to);
    }

    public static void main(String[] args) {
        Programmers_Level2_12946 solution = new Programmers_Level2_12946();
        System.out.println(Arrays.deepToString(solution.solution(1)));
    }
}
