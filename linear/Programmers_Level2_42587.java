package linear;

import java.util.ArrayDeque;
import java.util.Deque;

class Programmers_Level2_42587 {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Deque<Process> ready = new ArrayDeque<>();
        for (int idx = 0; idx < priorities.length; idx++) ready.add(new Process(priorities[idx], idx));
        Deque<Process> tmp = new ArrayDeque<>();
        while (!ready.isEmpty()) {
            Process front = ready.poll();
            boolean flag = true;
            while (!ready.isEmpty()) {
                Process next = ready.poll();
                tmp.addLast(next);

                if (next.priority > front.priority) {
                    flag = false;
                    break;
                }
            }
            while (!tmp.isEmpty()) {
                ready.addFirst(tmp.pollLast());
            }
            if (!flag) ready.addLast(front);
            else answer++;
            if (flag && front.idx == location) break;
        }
        return answer;
    }

    private static class Process {
        int priority, idx;

        public Process(int priority, int idx) {
            this.priority = priority;
            this.idx = idx;
        }
    }

    public static void main(String[] args) {
        Programmers_Level2_42587 solution = new Programmers_Level2_42587();
        System.out.println(solution.solution(new int[]{1, 1, 9, 1, 1, 1}, 0));
    }
}
