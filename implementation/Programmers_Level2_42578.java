package implementation;

import java.util.HashMap;
import java.util.Map;

class Programmers_Level2_42578 {
    /*
    각 종류별로 한가지의 의상을 입어야 함.
    조합으로 풀면 시간 초과. 단순하게 경우의 수를 구하는 걸 생각해서 각 종류 당 (개수 + 1)의 경우가 있을 때 다 곱해주면 됨.
     */
    private Map<String, Integer> closet; // key - 종류, value - 개수
//    private List<String> types;

    public int solution(String[][] clothes) {
        int answer = 1;
        closet = new HashMap<>();
        for (String[] pair : clothes) {
            if (closet.containsKey(pair[1])) {
                int cnt = closet.get(pair[1]);
                closet.put(pair[1], cnt + 1);
            } else closet.put(pair[1], 1);
        }
        for (int cnt : closet.values()) {
            answer *= (cnt + 1);
        }
        return answer - 1;
    }

// 조합으로 풀면 시간초과
//    private void dfs(int startIdx, int sum) {
//        answer += sum;
//        for(int i = startIdx + 1; i < types.size(); i++) {
//            String type = types.get(i);
//            dfs(i, sum * closet.get(type));
//        }
//    }

    public static void main(String[] args) {
        Programmers_Level2_42578 solution = new Programmers_Level2_42578();
        System.out.println(solution.solution(new String[][]{{"a", "1"}, {"b", "1"}, {"c", "2"}, {"d", "3"}, {"e", "4"}}));
    }
}
