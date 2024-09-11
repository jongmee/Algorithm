package tree;

import java.util.Arrays;
import java.util.TreeSet;

class Programmers_Level3_42628 {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (String operation : operations) {
            String[] parts = operation.split(" ");
            String first = parts[0];
            int second = Integer.parseInt(parts[1]);
            if (first.equals("I")) {
                treeSet.add(second);
            } else if (second == -1 && !treeSet.isEmpty()) {
                Integer min = treeSet.first();
                treeSet.remove(min);
            } else if (second == 1 && !treeSet.isEmpty()) {
                Integer max = treeSet.last();
                treeSet.remove(max);
            }
        }
        if (treeSet.isEmpty()) return answer;
        return new int[]{treeSet.last(), treeSet.first()};
    }

    public static void main(String[] args) {
        Programmers_Level3_42628 solution = new Programmers_Level3_42628();
        System.out.println(Arrays.toString(solution.solution(new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"})));
        System.out.println(Arrays.toString(solution.solution(new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"})));
    }
}
