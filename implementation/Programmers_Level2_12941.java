package implementation;

import java.util.Arrays;

class Programmers_Level2_12941 {

    public int solution(int[] A, int[] B) {
        int answer = 0;
        int n = A.length;
        Arrays.sort(A);
        Arrays.sort(B);
        for (int i = 0; i < n; i++) {
            answer += A[i] * B[n - i - 1];
        }
        return answer;
    }

    public static void main(String[] args) {
        Programmers_Level2_12941 solution = new Programmers_Level2_12941();
        System.out.println(solution.solution(new int[]{1, 4, 2}, new int[]{5, 4, 4}));
        System.out.println(solution.solution(new int[]{1, 2}, new int[]{3, 4}));
    }
}
