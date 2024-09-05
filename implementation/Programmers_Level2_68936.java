package implementation;

import java.util.Arrays;

class Programmers_Level2_68936 {
    private int zeroCnt, oneCnt, N;
    private int[][] inputArr;

    public int[] solution(int[][] arr) {
        inputArr = arr;
        zeroCnt = 0;
        oneCnt = 0;
        N = arr.length;
        count(arr.length, 0, 0);
        return new int[]{zeroCnt, oneCnt};
    }

    private void count(int sideLen, int startX, int startY) { // sideLen은 정사각형 한쪽 면의 길이
        boolean isZero = false, isOne = false;
        for (int y = startY; y < startY + sideLen; y++) {
            for (int x = startX; x < startX + sideLen; x++) {
                if (inputArr[y][x] == 0) isZero = true;
                else isOne = true;
                if (isOne && isZero) {
                    int nextLen = sideLen / 2;
                    count(nextLen, startX, startY);
                    count(nextLen, startX + nextLen, startY);
                    count(nextLen, startX, startY + nextLen);
                    count(nextLen, startX + nextLen, startY + nextLen);
                    break;
                }
            }
            if (isOne && isZero) break;
        }
        if (!isZero && isOne) oneCnt++;
        if (isZero && !isOne) zeroCnt++;
    }

    public static void main(String[] args) {
        Programmers_Level2_68936 solution = new Programmers_Level2_68936();
        System.out.println(Arrays.toString(solution.solution(
                new int[][]{
                        {1, 1, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 1},
                        {1, 1, 1, 1}})));
        System.out.println(Arrays.toString(solution.solution(
                new int[][]{
                        {1, 1, 1, 1, 1, 1, 1, 1},
                        {0, 1, 1, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 1, 1, 1, 1},
                        {0, 1, 0, 0, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1, 0, 0, 1},
                        {0, 0, 0, 0, 1, 1, 1, 1}})));
    }
}
