package bruteforce;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

class Programmers_Level3_84021 {
    /*
    90도 회전: x' = -y, y' = x
    180도 회전: x' = -x, y' = -y
    270도 회전: x' = y, y' = -x
     */
    private int[] dirX = {0, 1, 0, -1};
    private int[] dirY = {-1, 0, 1, 0};
    private int SIZE;

    public int solution(int[][] game_board, int[][] table) {
        SIZE = game_board.length;

        int[][] visited = new int[SIZE][SIZE];
        List<List<Node>> emptyBoards = new ArrayList<>();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (visited[row][col] == 0 && game_board[row][col] == 0) {
                    emptyBoards.add(bfs(game_board, row, col, visited, 0));
                }
            }
        }

        for (int i = 0; i < SIZE; i++) Arrays.fill(visited[i], 0);
        List<List<Node>> puzzles = new ArrayList<>();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (visited[row][col] == 0 && table[row][col] == 1) {
                    puzzles.add(bfs(table, row, col, visited, 1));
                }
            }
        }

        if (emptyBoards.size() < puzzles.size()) return match(puzzles, emptyBoards);
        else return match(emptyBoards, puzzles);
    }

    private int match(List<List<Node>> fixedList, List<List<Node>> changeList) {
        int cnt = 0;
        int[] matched = new int[changeList.size()];
        for (List<Node> fixedBlocks : fixedList) {
            for (int i = 0; i < changeList.size(); i++) {
                List<Node> changeBlocks = changeList.get(i);
                if (fixedBlocks.size() == changeBlocks.size() && matched[i] == 0
                        && matchInternal(fixedBlocks, changeBlocks)) {
                    matched[i] = 1;
                    cnt += fixedBlocks.size();
                    break;
                }
            }
        }
        return cnt;
    }

    private boolean matchInternal(final List<Node> fixedBlocks, final List<Node> changeBlocks) {
        Collections.sort(fixedBlocks);

        // 90도 회전
        List<Node> rotationBlocks = new ArrayList<>();
        for (Node node : changeBlocks) rotationBlocks.add(new Node(-1 * node.row, node.col));
        if (matchWithRotation(fixedBlocks, rotationBlocks)) return true;
        rotationBlocks.clear();

        // 180도 회전
        for (Node node : changeBlocks) rotationBlocks.add(new Node(node.col * -1, node.row * -1));
        if (matchWithRotation(fixedBlocks, rotationBlocks)) return true;
        rotationBlocks.clear();

        // 270도 회전
        for (Node node : changeBlocks) rotationBlocks.add(new Node(node.row, node.col * -1));
        if (matchWithRotation(fixedBlocks, rotationBlocks)) return true;
        rotationBlocks.clear();

        rotationBlocks.addAll(changeBlocks);
        if (matchWithRotation(fixedBlocks, rotationBlocks)) return true;
        return false;
    }

    private boolean matchWithRotation(List<Node> fixedBlocks, List<Node> rotationBlocks) {
        Collections.sort(rotationBlocks);
        int colDiff = fixedBlocks.get(0).col - rotationBlocks.get(0).col;
        int rowDiff = fixedBlocks.get(0).row - rotationBlocks.get(0).row;
        for (Node node : rotationBlocks) {
            node.col += colDiff;
            node.row += rowDiff;
        }
        for (int i = 0; i < rotationBlocks.size(); i++) {
            if (rotationBlocks.get(i).row == fixedBlocks.get(i).row
                    && rotationBlocks.get(i).col == fixedBlocks.get(i).col) continue;
            return false;
        }
        return true;
    }

    private List<Node> bfs(int[][] game_board, int row, int col, int[][] visited, int expected) {
        List<Node> nodes = new ArrayList<>();
        visited[row][col] = 1;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(col, row));
        while (!q.isEmpty()) {
            Node front = q.poll();
            nodes.add(front);

            for (int dir = 0; dir < 4; dir++) {
                int nextRow = front.row + dirY[dir], nextCol = front.col + dirX[dir];
                if (isValidIdx(nextRow, nextCol) && visited[nextRow][nextCol] == 0
                        && game_board[nextRow][nextCol] == expected) {
                    visited[nextRow][nextCol] = 1;
                    q.add(new Node(nextCol, nextRow));
                }
            }
        }
        return nodes;
    }

    private boolean isValidIdx(int row, int col) {
        return row >= 0 && col >= 0 && row < SIZE && col < SIZE;
    }

    private static class Node implements Comparable<Node> {
        int col, row;

        public Node(int col, int row) {
            this.col = col;
            this.row = row;
        }

        @Override
        public int compareTo(Node o) {
            if (row == o.row) return Integer.compare(col, o.col);
            return Integer.compare(row, o.row);
        }
    }

    public static void main(String[] args) {
        Programmers_Level3_84021 solution = new Programmers_Level3_84021();
        System.out.println(solution.solution(new int[][]{{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}},
                new int[][]{{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}}));
        System.out.println(solution.solution(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 1}}, new int[][]{{1, 1, 1}, {1, 0, 0}, {0, 0, 0}}));
    }
}
