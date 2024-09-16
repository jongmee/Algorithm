package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Programmers_Level3_49191 {
    private List<Integer>[] winTree, loseTree;

    public int solution(int n, int[][] results) {
        int answer = 0;
        init(n, results);

        for (int player = 1; player <= n; player++) {
            opposites.clear();
            int[] visited = new int[n + 1];
            visited[player] = 1;
            dfsWinTree(player, visited);
            int winCnt = opposites.size();

            opposites.clear();
            Arrays.fill(visited, 0);
            visited[player] = 1;
            dfsLoseTree(player, visited);
            int loseCnt = opposites.size();
            if (winCnt + loseCnt == n - 1) answer++;
        }
        return answer;
    }

    private void init(int n, int[][] results) {
        winTree = new List[n + 1];
        loseTree = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            winTree[i] = new ArrayList<>();
            loseTree[i] = new ArrayList<>();
        }
        for (int[] result : results) {
            winTree[result[0]].add(result[1]);
            loseTree[result[1]].add(result[0]);
        }
    }

    private Set<Integer> opposites = new HashSet<>();

    private void dfsWinTree(int player, int[] visited) {
        for (int nextPlayer : winTree[player]) {
            if (visited[nextPlayer] == 0) {
                visited[nextPlayer] = 1;
                opposites.add(nextPlayer);
                dfsWinTree(nextPlayer, visited);
            }
        }
    }

    private void dfsLoseTree(int player, int[] visited) {
        for (int nextPlayer : loseTree[player]) {
            if (visited[nextPlayer] == 0) {
                visited[nextPlayer] = 1;
                opposites.add(nextPlayer);
                dfsLoseTree(nextPlayer, visited);
            }
        }
    }

    public static void main(String[] args) {
        Programmers_Level3_49191 solution = new Programmers_Level3_49191();
        System.out.println(solution.solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
    }
}
