package dp;

class Programmers_Level3_43105 {
    public int solution(int[][] triangle) {
        int answer = 0;
        int totalHeight = triangle.length;
        int[][] dp = new int[totalHeight][triangle[totalHeight - 1].length];
        dp[0][0] = triangle[0][0];
        for (int height = 1; height < totalHeight; height++) {
            int stepWidth = triangle[height].length;
            dp[height][0] = dp[height - 1][0] + triangle[height][0];
            int prevStepWidth = triangle[height - 1].length;
            dp[height][stepWidth - 1] = dp[height - 1][prevStepWidth - 1] + triangle[height][stepWidth - 1];
            for (int width = 1; width < stepWidth - 1; width++) {
                dp[height][width] = Integer.max(dp[height - 1][width - 1], dp[height - 1][width]);
                dp[height][width] += triangle[height][width];
            }
        }
        for (int totalSum : dp[totalHeight - 1]) answer = Integer.max(answer, totalSum);
        return answer;
    }

    public static void main(String[] args) {
        Programmers_Level3_43105 solution = new Programmers_Level3_43105();
        System.out.println(solution.solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}));
    }
}
