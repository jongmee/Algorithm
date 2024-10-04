package greedy;

import java.util.Arrays;

class Programmers_Level2_42885 {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int totalLen = people.length;
        int left = 0, right = totalLen - 1;
        while (left <= right) {
            while (right >= 0 && left < right && people[right] + people[left] > limit) {
                right--;
                answer++;
            }
            answer++;
            left++;
            right--;
        }
        return answer;
    }
}
