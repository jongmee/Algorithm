package dp;

class Programmers_Level3_12907 {
    private static int DIVIDER = 1_000_000_007;
    private int[] dp;

    public int solution(int n, int[] money) {
        dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < money.length; i++) {
            for (int total = 1; total <= n; total++) {
                int rest = total - money[i];
                if (rest >= 0) dp[total] += dp[rest];
            }
        }
        return dp[n] % DIVIDER;
    }

    public static void main(String[] args) {
        Programmers_Level3_12907 solution = new Programmers_Level3_12907();
        System.out.println(solution.solution(10, new int[]{3, 2, 5}));
    }
}
