package datastructure;

import java.util.ArrayDeque;
import java.util.Deque;

class Programmers_Level2_42584 {
    public int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) answer[i] = len - i - 1;

        Deque<Node> stk = new ArrayDeque<>(); // 자기보다 작은 것들로 내림차순 보관
        for (int i = len - 1; i >= 0; i--) {
            while (!stk.isEmpty() && stk.peekLast().val >= prices[i]) stk.pollLast();
            if (!stk.isEmpty()) answer[i] = stk.peekLast().idx - i;
            stk.addLast(new Node(i, prices[i]));
        }
        return answer;
    }

    class Node {
        int idx, val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}
