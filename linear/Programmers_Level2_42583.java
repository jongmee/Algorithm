package linear;

import java.util.ArrayDeque;
import java.util.Queue;

class Programmers_Level2_42583 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0, weightSum = 0;
        Queue<Integer> trucks = new ArrayDeque<>();
        Queue<Integer> bridge = new ArrayDeque<>();
        for (int truck : truck_weights) trucks.add(truck);
        for (int i = 0; i < bridge_length; i++) bridge.add(0);

        while (!trucks.isEmpty()) {
            int truck = trucks.peek();
            int front = bridge.poll();
            weightSum -= front;
            if (weightSum + truck <= weight) {
                weightSum += truck;
                trucks.poll();
                bridge.add(truck);
            } else bridge.add(0);
            time++;
        }

        return time + bridge_length;
    }

    public static void main(String[] args) {
        Programmers_Level2_42583 solution = new Programmers_Level2_42583();
        System.out.println(solution.solution(1, 1, new int[]{1}));
        System.out.println(solution.solution(2, 10, new int[]{7, 4, 5, 6}));
        System.out.println(solution.solution(100, 100, new int[]{10}));
        System.out.println(solution.solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}));
    }
}
