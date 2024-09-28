package greedy;

import java.util.ArrayDeque;
import java.util.Deque;

class Programmers_Level2_150369 {
    /*
     1 ≤ n ≤ 100,000
     */
    public long solution(final int cap, final int n, int[] deliveries, int[] pickups) {
        Deque<Integer> dLastIdxs = getLastIdx(n, deliveries);
        Deque<Integer> pLastIdxs = getLastIdx(n, pickups);
        long answer = 0;

        while (!(dLastIdxs.isEmpty() && pLastIdxs.isEmpty())) {
            int dCost = take(dLastIdxs, deliveries, cap);
            int pCost = take(pLastIdxs, pickups, cap);
            if (dCost == -1) answer += (pCost + 1) * 2;
            else if (pCost == -1) answer += (dCost + 1) * 2;
            else answer += Integer.max(pCost + 1, dCost + 1) * 2;
        }
        return answer;
    }

    private int take(Deque<Integer> idxs, int[] arr, int cap) {
        if (idxs.isEmpty()) return -1;
        int lastIdx = idxs.peekFirst(), bag = 0;
        while (!idxs.isEmpty()) {
            int idx = idxs.pollFirst();
            if (arr[idx] + bag <= cap) {
                bag += arr[idx];
                arr[idx] = 0;
            } else {
                arr[idx] -= (cap - bag);
                bag = cap;
                idxs.addFirst(idx);
            }
            if (bag == cap) break;
        }
        return lastIdx;
    }

    private Deque<Integer> getLastIdx(int n, int[] arr) {
        Deque<Integer> re = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                re.addFirst(i);
            }
        }
        return re;
    }

    public static void main(String[] args) {
        Programmers_Level2_150369 solution = new Programmers_Level2_150369();
        System.out.println(solution.solution(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0}));
        System.out.println(solution.solution(2, 7, new int[]{1, 0, 2, 0, 1, 0, 2}, new int[]{0, 2, 0, 1, 0, 2, 0}));
    }
}
