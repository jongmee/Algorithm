package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Programmers_Level3_92343 {
    private List<List<Integer>> ROUTES;

    public int solution(int[] info, int[][] edges) {
        ANS = 0;
        int size = info.length;
        ROUTES = new ArrayList<>();
        for (int i = 0; i < size; i++) ROUTES.add(new ArrayList<>());
        for (int[] edge : edges) ROUTES.get(edge[0]).add(edge[1]);
        dfs(0, 0, 1, info, new HashSet<>());
        return ANS;
    }

    private int ANS;

    private void dfs(int startIdx, int wolfCnt, int sheepCnt, int[] info, Set<Integer> nexts) {
        if (sheepCnt <= wolfCnt) return;
        ANS = Integer.max(sheepCnt, ANS);
        nexts.addAll(ROUTES.get(startIdx));
        nexts.remove(startIdx);

        for (int next : nexts) {
            if (info[next] == 0) dfs(next, wolfCnt, sheepCnt + 1, info, new HashSet<>(nexts));
            else dfs(next, wolfCnt + 1, sheepCnt, info, new HashSet<>(nexts));
        }
    }

    public static void main(String[] args) {
        Programmers_Level3_92343 solution = new Programmers_Level3_92343();
        System.out.println(solution.solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
                new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}}));
        System.out.println(solution.solution(new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0},
                new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}}));
    }
}
