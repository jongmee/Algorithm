package datastructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class Programmers_Level2_42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        int size = progresses.length;
        Deque<Integer> restDays = new ArrayDeque<>();
        for (int i = 0; i < size; i++) {
            int restPercent = 100 - progresses[i];
            int days = restPercent / speeds[i];
            if (restPercent % speeds[i] != 0) days++;
            restDays.addLast(days);
        }
        List<Integer> ans = new ArrayList<>();
        while (!restDays.isEmpty()) {
            int front = restDays.pollFirst();
            int cnt = 1;
            while (!restDays.isEmpty() && restDays.peekFirst() < front) {
                restDays.pollFirst();
                cnt++;
            }
            ans.add(cnt);
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Programmers_Level2_42586 solution = new Programmers_Level2_42586();
        System.out.println(Arrays.toString(solution.solution(new int[]{93, 30, 55}, new int[]{1, 30, 5})));
        System.out.println(Arrays.toString(solution.solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
    }
}
