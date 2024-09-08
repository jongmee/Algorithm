package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Programmers_Level2_12953 {
    /*
    최소공배수
    1. 1-100까지의 소수를 구한다.
    2. arr를 소수의 곱으로 나타낸다.
    3. 소수가 최대로 나온 횟수를 곱한다.

     */
    private static final int[] primeNums = new int[101]; // 맞으면 0 아니면 1

    static {
        for (int i = 2; i * i <= 100; i++) {
            for (int j = i * 2; j <= 100; j += i) {
                primeNums[j] = 1;
            }
        }
    }

    public int solution(int[] arr) {
        int answer = 1;
        Arrays.sort(arr);
        List<List<Integer>> divisors = new ArrayList<>();
        for (int num : arr) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 2; i <= 100; i++) {
                if (i > num) break;
                if (primeNums[i] == 0) {
                    while ((num / i) > 0 && (num % i == 0)) {
                        num /= i;
                        tmp.add(i);
                    }
                }
            }
            divisors.add(tmp);
        }
        Map<Integer, Integer> result = new HashMap<>();
        for (var list : divisors) {
            for (int num : list) {
                if (result.containsKey(num)) {
                    int cnt = count(list, num);
                    if (result.get(num) < cnt) {
                        result.remove(num);
                        result.put(num, cnt);
                    }
                } else {
                    result.put(num, count(list, num));
                }
            }
        }
        for (var entry : result.entrySet())
            for (int cnt = 0; cnt < entry.getValue(); cnt++)
                answer *= entry.getKey();

        return answer;
    }

    public static void main(String[] args) {
        Programmers_Level2_12953 solution = new Programmers_Level2_12953();
        System.out.println(solution.solution(new int[]{2, 6, 8, 14}));
        System.out.println(solution.solution(new int[]{9, 99}));
    }

    private int count(List<Integer> divisors, int num) {
        int cnt = 0;
        for (int di : divisors) if (num == di) cnt++;
        return cnt;
    }
}
