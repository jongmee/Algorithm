package graph;

import java.util.HashSet;
import java.util.Set;

class Programmers_Level2_42839 {
    private final int MAX_NUM = 10000000;
    private int[] primeNumbers;
    private Set<Integer> ans;

    public int solution(String numbers) {
        primeNumbers = new int[MAX_NUM];
        ans = new HashSet<>();
        primeNumbers[0] = 1;
        primeNumbers[1] = 1;
        for (int i = 2; i * i < MAX_NUM; i++) {
            for (int j = i * 2; j < MAX_NUM; j = j + i) {
                primeNumbers[j] = 1;
            }
        }
        dfs(new int[numbers.length()], 0, "", numbers);
        return ans.size();
    }

    private int[] made;

    private void dfs(int[] visited, int depth, String number, String numbers) {
        if (!number.isEmpty()) {
            int num = Integer.parseInt(number);
            if (primeNumbers[num] == 0) ans.add(num);
        }
        if (depth == numbers.length()) return;
        for (int i = 0; i < numbers.length(); i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                dfs(visited, depth + 1, number + numbers.charAt(i), numbers);
                visited[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Programmers_Level2_42839 solution = new Programmers_Level2_42839();
        System.out.println(solution.solution("17"));
        System.out.println(solution.solution("011"));
    }
}
