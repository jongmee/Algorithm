package implementation;

import java.util.Arrays;

class Programmers_Level2_42842 {

    public int[] solution(int brown, int yellow) {
        int colLen = 3, rowLen = calculateRowLen(colLen, brown);
        while (colLen < rowLen) {
            int tmpYellow = calculateYellow(colLen, rowLen);
            if (tmpYellow == yellow) break;
            colLen++;
            rowLen = calculateRowLen(colLen, brown);
        }
        return new int[]{rowLen, colLen};
    }

    public int calculateRowLen(int colLen, int brown) {
        return (brown + 4 - colLen * 2) / 2;
    }

    public int calculateYellow(int colLen, int rowLen) {
        int total = colLen * rowLen;
        return total - (colLen * 2) - (rowLen * 2) + 4;
    }

    public static void main(String[] args) {
        Programmers_Level2_42842 solution = new Programmers_Level2_42842();
        System.out.println(Arrays.toString(solution.solution(10, 2)));
        System.out.println(Arrays.toString(solution.solution(8, 1)));
        System.out.println(Arrays.toString(solution.solution(24, 24)));
    }
}
