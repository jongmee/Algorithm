package string;

import java.util.ArrayList;
import java.util.List;

class Programmers_Level2_60057 {
    /*
    s의 길이는 1 이상 1,000 이하
    O(n^2)도 가능
     */
    public int solution(String s) {
        int answer = s.length(), sLen = s.length();
        for (int compression = 1; compression <= sLen / 2; compression++) {
            String tmp = "";
            List<String> units = new ArrayList<>();
            for (int i = 0; i < sLen - compression + 1; i += compression) units.add(s.substring(i, i + compression));
            String rest = s.substring(sLen - sLen % compression);
            String prev = units.get(0);
            int cnt = 0;
            for (int i = 1; i < units.size(); i++) {
                if (prev.equals(units.get(i))) {
                    cnt++;
                } else {
                    if (cnt == 0) tmp += prev;
                    else tmp += (cnt + 1) + prev;
                    prev = units.get(i);
                    cnt = 0;
                }
            }
            if (cnt != 0) tmp += (cnt + 1) + prev;
            else tmp += prev;
            tmp += rest;
            answer = Integer.min(answer, tmp.length());
        }
        return answer;
    }

    public static void main(String[] args) {
        Programmers_Level2_60057 solution = new Programmers_Level2_60057();
        System.out.println(solution.solution("aabbaccc"));
        System.out.println(solution.solution("ababcdcdababcdcd"));
        System.out.println(solution.solution("abcabcdede"));
        System.out.println(solution.solution("abcabcabcabcdededededede"));
        System.out.println(solution.solution("xababcdcdababcdcd"));
    }
}
