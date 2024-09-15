package bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Programmers_Level2_81302 {
    /*
    각 row은 하나의 대기실 구조
    맨하튼 거리를 8방향에 대해서 체크해야 함
     */
    private final int SIZE = 5;
    private final int[] dirCol = {0, 0, 0, 0, -2, 2, -1, 1, 1, 1, -1, -1}; // 상 하 좌 우 우상 우하 좌상 좌하
    private final int[] dirRow = {-1, 1, -2, 2, 0, 0, 0, 0, -1, 1, -1, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[SIZE];
        for (int roomIdx = 0; roomIdx < SIZE; roomIdx++) {
            String[] roomString = places[roomIdx];
            char[][] room = new char[SIZE][SIZE];
            List<Integer> personCol = new ArrayList<>(), personRow = new ArrayList<>();
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    room[row][col] = roomString[row].charAt(col);
                    if (room[row][col] == 'P') {
                        personCol.add(col);
                        personRow.add(row);
                    }
                }
            }
            answer[roomIdx] = check(room, personCol, personRow);
        }
        return answer;
    }

    private int check(char[][] room, List<Integer> personCol, List<Integer> personRow) {
        int peopleCnt = personRow.size();
        for (int i = 0; i < peopleCnt; i++) {
            int col = personCol.get(i), row = personRow.get(i);
            for (int dir = 0; dir < 10; dir++) {
                int borderCol = col + dirCol[dir], borderRow = row + dirRow[dir];
                if (isValidIdx(borderCol, borderRow) && room[borderRow][borderCol] == 'P'
                        && !checkInternal(row, col, dir, room)) return 0;
            }
        }
        return 1;
    }

    private boolean checkInternal(int row, int col, int dir, char[][] room) {
        if (dir < 6) {
            int rowOffset = dirRow[dir] / 2, colOffset = dirCol[dir] / 2;
            return room[row + rowOffset][col + colOffset] == 'X';
        }
        char first = room[row + dirRow[dir]][col];
        char second = room[row][col + dirCol[dir]];
        return first == 'X' && second == 'X';
    }

    private boolean isValidIdx(int x, int y) {
        return x >= 0 && y >= 0 && x < SIZE && y < SIZE;
    }

    public static void main(String[] args) {
        Programmers_Level2_81302 solution = new Programmers_Level2_81302();
        System.out.println(Arrays.toString(solution.solution(new String[][]{
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}})));
    }
}
