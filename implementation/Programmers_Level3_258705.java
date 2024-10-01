package implementation;

class Programmers_Level3_258705 {
    /*
    a, b, c, d 모양
     */
    public int solution(int n, int[] tops) {
        int answer = 0;
        int aCnt = 1, bCnt = 0, cCnt = 1, dCnt = 1;
        if (tops[0] == 1) bCnt++;
        for (int idx = 1; idx < n; idx++) {
            int tmpA = aCnt + bCnt + cCnt + dCnt;
            int tmpB = 0;
            int tmpC = aCnt + bCnt + cCnt + dCnt;
            int tmpD = aCnt + bCnt + dCnt;
            if (tops[idx] == 1) tmpB = aCnt + bCnt + cCnt + dCnt;
            aCnt = tmpA % 10007;
            bCnt = tmpB % 10007;
            cCnt = tmpC % 10007;
            dCnt = tmpD % 10007;
        }
        answer = aCnt + bCnt + cCnt + dCnt;
        return answer % 10007;
    }

    public static void main(String[] args) {
        Programmers_Level3_258705 solution = new Programmers_Level3_258705();
        System.out.println(solution.solution(4, new int[]{1, 1, 0, 1}));
        System.out.println(solution.solution(2, new int[]{0, 1}));
        System.out.println(solution.solution(10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
    }
}
