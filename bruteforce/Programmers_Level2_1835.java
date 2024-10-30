package bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class Programmers_Level2_1835 {
    int cnt = 8;
    List<Node>[] nodes;
    Map<Character, Integer> index = Map.of(
            'A', 0,
            'C', 1,
            'F', 2,
            'J', 3,
            'M', 4,
            'N', 5,
            'R', 6,
            'T', 7
    );

    public int solution(int n, String[] data) {
        nodes = new List[cnt];
        for (int i = 0; i < cnt; i++) nodes[i] = new ArrayList<>();
        for (String d : data) {
            char first = d.charAt(0), second = d.charAt(2), op = d.charAt(3);
            int len = d.charAt(4) - 48;

            if (op == '<' && len == 0) return 0;
            Node node = new Node(first, op, len);
            nodes[index.get(second)].add(node);
        }
        ANS = 0;
        int[] visited = new int[cnt];
        Arrays.fill(visited, -1);
        dfs(visited, 0);
        return ANS;
    }

    int ANS;

    void dfs(int[] visited, int seqIdx) {
        if (seqIdx >= cnt) {
            for (int i = 0; i < cnt; i++) {
                if (!check(visited, i)) return;
            }
            ANS++;
            return;
        }
        for (int i = 0; i < cnt; i++) {
            if (visited[i] == -1) {
                visited[i] = seqIdx;
                dfs(visited, seqIdx + 1);
                visited[i] = -1;
            }
        }
    }

    boolean check(int[] visited, int friendIdx) {
        List<Node> conditions = nodes[friendIdx];
        int seqIdx = visited[friendIdx];
        for (Node node : conditions) {
            int idx = index.get(node.other);
            int gapCnt = Integer.max(seqIdx - visited[idx], visited[idx] - seqIdx);
            gapCnt--;
            if (gapCnt < 0) return false;
            if (node.op == '=') {
                if (gapCnt != node.len) return false;
            } else if (node.op == '<') { // 미만
                if (gapCnt >= node.len) return false;
            } else { // 초과
                if (gapCnt <= node.len) return false;
            }
        }
        return true;
    }

    class Node {
        char op, other;
        int len;

        public Node(char other, char op, int len) {
            this.other = other;
            this.op = op;
            this.len = len;
        }
    }
}
