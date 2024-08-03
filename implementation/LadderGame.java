package implementation;

import java.util.Arrays;

class LadderGame {
    // 가로 한 줄씩 보는 방식
    public char[] improvedSolution(int n, int[][] ladder) {
        char[] answer = new char[n];
        for (int i = 0; i < n; i++) answer[i] = (char) (i + 'A');
        for (int[] step : ladder) {
            for (int line : step) {
                line--;
                char tmp = answer[line];
                answer[line] = answer[line + 1];
                answer[line + 1] = tmp;
            }
        }
        return answer;
    }

    // 한 출발점마다 사다리를 타고 내려가는 방식
    public char[] solution(int n, int[][] ladderInputs) {
        char[] answer = new char[n];
        int ladderLen = ladderInputs.length;
        int[][] ladder = new int[ladderLen][n];
        for (int height = 0; height < ladderLen; height++) {
            for (int idx = 0; idx < ladderInputs[height].length; idx++) {
                int line = ladderInputs[height][idx] - 1;
                ladder[height][line] = 1;
                ladder[height][line + 1] = -1;
            }
        }

        for (int student = 0; student < n; student++) {
            int height = 0, point = student;
            while (height < ladderLen) {
                point += ladder[height][point];
                height++;
            }
            answer[point] = (char) (student + 'A');
        }

        return answer;
    }

    // 입력 값: n - 사람 수, ladder - 가로 한줄씩 발판 기준 왼쪽 학생 번호
    public static void main(String[] args) {
        LadderGame T = new LadderGame();
        System.out.println(Arrays.toString(T.improvedSolution(5, new int[][]{{1, 3}, {2, 4}, {1, 4}})));
        System.out.println(Arrays.toString(T.improvedSolution(7, new int[][]{{1, 3, 5}, {1, 3, 6}, {2, 4}})));
        System.out.println(Arrays.toString(T.improvedSolution(8, new int[][]{{1, 5}, {2, 4, 7}, {1, 5, 7}, {2, 5, 7}})));
        System.out.println(Arrays.toString(T.improvedSolution(12, new int[][]{{1, 5, 8, 10}, {2, 4, 7}, {1, 5, 7, 9, 11}, {2, 5, 7, 10}, {3, 6, 8, 11}})));
    }
}
