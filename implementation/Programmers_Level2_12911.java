package implementation;

public class Programmers_Level2_12911 {
    public int solution(int n) {
        int nOneCnt = Integer.bitCount(n); // integer를 이진수로 바꾼 후 1의 개수를 세는 메소드
        for (int next = n + 1; ; next++) {
            int nextOneCnt = Integer.bitCount(next);
            if (nOneCnt == nextOneCnt) return next;
        }
    }

    public static void main(String[] args) {
        Programmers_Level2_12911 solution = new Programmers_Level2_12911();
        System.out.println(solution.solution(78));
        System.out.println(solution.solution(15));
    }
}
