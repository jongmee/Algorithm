package greedy;

import java.util.ArrayDeque;
import java.util.Deque;

class Programmers_Level2_42883 {
    public String solution(String number, int k) {
        String answer = "";
        int totalLen = number.length();
        int[] numbers = new int[totalLen + 1];
        for (int i = 0; i < totalLen; i++) {
            numbers[i] = number.charAt(i) - '0';
        }
        numbers[totalLen] = 10;
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i <= totalLen; i++) {
            if (stk.isEmpty()) stk.add(numbers[i]);
            else if (stk.peekLast() >= numbers[i]) stk.add(numbers[i]);
            else {
                while (k > 0 && !stk.isEmpty() && stk.peekLast() < numbers[i]) {
                    stk.pollLast();
                    k--;
                }
                stk.add(numbers[i]);
            }
        }
        while (!stk.isEmpty()) {
            int val = stk.pollFirst();
            if (val != 10) answer += val;
        }
        return answer;
    }
}
