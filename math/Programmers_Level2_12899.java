package math;

class Programmers_Level2_12899 {
    private final int[] VALS = {1, 2, 4};

    public String solution(int n) {
        if (n <= 3) {
            return "" + VALS[n - 1];
        }
        long ans = 0, digit = 1;
        while (n > 0) {
            n--;
            int rest = n % 3;
            ans = VALS[rest] * digit + ans;
            n /= 3;
            digit *= 10;
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        Programmers_Level2_12899 solution = new Programmers_Level2_12899();
        System.out.println(solution.solution(1));
        System.out.println(solution.solution(2));
        System.out.println(solution.solution(3));
        System.out.println(solution.solution(4));
        System.out.println(solution.solution(11));
    }
}
