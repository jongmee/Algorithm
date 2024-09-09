package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Programmers_Level3_43163 {
    /*
    각 단어는 알파벳 소문자로만 이루어져 있다.
    각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같다.
    words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없다.
     */
    private static List<List<Integer>> tree;

    public int solution(String begin, String target, String[] words) {
        tree = new ArrayList<>();
        for (int i = 0; i < words.length; i++) tree.add(new ArrayList<>());
        for (int i = 0; i < words.length - 1; i++) {
            String criteria = words[i];
            for (int j = i + 1; j < words.length; j++) {
                int diffCnt = getDiffCnt(criteria, words[j]);
                if (diffCnt == 1) {
                    tree.get(i).add(j);
                    tree.get(j).add(i);
                }
            }
        }
        List<Integer> starters = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            int diffCnt = getDiffCnt(begin, words[i]);
            if (diffCnt == 1) starters.add(i);
        }
        int[] visited = new int[words.length];
        minCnt = Integer.MAX_VALUE;
        for (int startIdx : starters) {
            visited[startIdx] = 1;
            dfs(visited, startIdx, words, target, 1);
            visited[startIdx] = 0;
        }

        if (minCnt == Integer.MAX_VALUE) return 0;
        return minCnt;
    }

    private static int minCnt;

    private static void dfs(int[] visited, int startIdx, String[] words, String target, int depth) {
        if (depth > minCnt) return;
        if (words[startIdx].equals(target)) {
            minCnt = depth;
        }
        for (int nextIdx : tree.get(startIdx)) {
            if (visited[nextIdx] == 0) {
                visited[nextIdx] = 1;
                dfs(visited, nextIdx, words, target, depth + 1);
                visited[nextIdx] = 0;
            }
        }
    }

    private static int getDiffCnt(String str1, String str2) {
        Map<Character, Integer> str1Map = getCharMap(str1);
        Map<Character, Integer> str2Map = getCharMap(str2);
        int ret = 0;
        for (char str1Char : str1Map.keySet()) {
            if (str2Map.containsKey(str1Char)) {
                ret += Integer.min(str2Map.get(str1Char), str1Map.get(str1Char));
            }
        }
        return str1.length() - ret;
    }

    private static Map<Character, Integer> getCharMap(String str) {
        Map<Character, Integer> str1Map = new HashMap<>(); // 문자, 개수
        for (char c : str.toCharArray()) {
            if (str1Map.containsKey(c)) {
                int cnt = str1Map.get(c) + 1;
                str1Map.remove(c);
                str1Map.put(c, cnt);
            } else str1Map.put(c, 1);
        }
        return str1Map;
    }

    public static void main(String[] args) {
        Programmers_Level3_43163 solution = new Programmers_Level3_43163();
        System.out.println(solution.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(solution.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}));
    }
}
