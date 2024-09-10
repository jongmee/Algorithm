package dp;

import java.util.HashSet;
import java.util.Set;

class Programmers_Level2_131701 {
    private int[][] dp = new int[1050][2500];

    public int solution(int[] elements) {
        Set<Integer> sums = new HashSet<>();
        int size = elements.length;
        for (int col = 0; col < size; col++) {
            dp[0][col] = elements[col];
            sums.add(dp[0][col]);
        }
        
        dp[1][0] = dp[0][0] + dp[0][size - 1];
        sums.add(dp[1][0]);
        for (int col = 1; col < size; col++) {
            dp[1][col] = dp[0][col] + dp[0][col - 1];
            sums.add(dp[1][col]);
        }
        
        int gap = 1;
        for (int row = 2; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int nextCol = col + gap;
                if (nextCol >= size) nextCol -= size;
                dp[row][col] = dp[row - 1][col] + dp[0][nextCol];
                sums.add(dp[row][col]);
            }
            gap++;
        }

        return sums.size();
    }

    public static void main(String[] args) {
        Programmers_Level2_131701 solution = new Programmers_Level2_131701();
        System.out.println(solution.solution(new int[]{7, 9, 1, 1, 4}));
    }
}
