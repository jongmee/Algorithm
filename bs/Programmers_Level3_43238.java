package bs;

import java.util.Arrays;

class Programmers_Level3_43238 {
    private int[] TIMES;
    private long N;

    public long solution(int n, int[] inputTimes) {
        TIMES = inputTimes;
        N = n;
        Arrays.sort(TIMES);
        return binarySearchLowerBound(N * TIMES[0]);
    }

    private long binarySearchLowerBound(long maxVal) {
        long left = 0, right = maxVal;
        while (left < right) {
            long mid = (right - left) / 2 + left;
            long checked = check(mid);
            if (checked > 0) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    private long check(long val) { // 각 심사위원에 val에 최대한 가깝게
        long rest = N;
        for (int time : TIMES) {
            long cnt = val / time;
            rest -= cnt;
        }
        return rest;
    }

    public static void main(String[] args) {
        Programmers_Level3_43238 solution = new Programmers_Level3_43238();
        System.out.println(solution.solution(6, new int[]{7, 10}));
        System.out.println(solution.solution(1_000_000_000, new int[]{1_000_000_000, 1_000_000_000}));
    }
}
