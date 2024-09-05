package implementation;

import java.util.ArrayList;
import java.util.List;

class Programmers_Level2_86971 {
    /*
    n은 2 이상 100 이하인 자연수
    그래프(주어진 트리를 양방향으로 기록)를 만들어서 한 간선이 끊어졌을 때 어떻게 전력망이 나누어지는지 확인한다.
     */
    private int[][] wires;
    private List<List<Integer>> tree;

    public int solution(int n, int[][] inputWires) {
        int answer = 100000;
        wires = inputWires;
        tree = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) tree.add(new ArrayList<>());
        for (int[] wire : inputWires) {
            tree.get(wire[0]).add(wire[1]);
            tree.get(wire[1]).add(wire[0]);
        }

        for (int[] wire : inputWires) {
            cnt = 1;
            int[] visited = new int[n + 1];
            visited[wire[1]] = 1;
            find(wire[1], wire[0], visited);
            answer = Integer.min(answer, Math.abs(n - cnt - cnt));
        }

        return answer;
    }

    private int cnt;

    private void find(int startNode, int exceptNode, int[] visited) {
        for (int node : tree.get(startNode)) {
            if (visited[node] == 0 && node != exceptNode) {
                visited[node] = 1;
                cnt++;
                find(node, exceptNode, visited);
            }
        }
    }

    public static void main(String[] args) {
        Programmers_Level2_86971 solution = new Programmers_Level2_86971();
        System.out.println(solution.solution(9, new int[][]{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}}));
        System.out.println(solution.solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}}));
        System.out.println(solution.solution(7, new int[][]{{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}}));
    }
}
