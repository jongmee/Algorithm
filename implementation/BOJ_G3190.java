package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class BOJ_G3190 {
    private static final int[] dirX = {1, 0, -1, 0};
    private static final int[] dirY = {0, 1, 0, -1};
    private static final int APPLE = 3;
    private static int N, K, L;
    private static int[][] map, moveMap;
    private static Map<Integer, Character> changes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = parseInt(br.readLine());
        map = new int[N][N];
        K = parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            String[] inputs = br.readLine().split(" ");
            int x = parseInt(inputs[1]) - 1, y = parseInt(inputs[0]) - 1;
            map[y][x] = APPLE;
        }

        L = parseInt(br.readLine());
        changes = new HashMap<>();
        for (int i = 0; i < L; i++) {
            String[] inputs = br.readLine().split(" ");
            changes.put(parseInt(inputs[0]), inputs[1].charAt(0));
        }

        Deque<Integer> snakeX = new ArrayDeque<>(), snakeY = new ArrayDeque<>();
        snakeX.add(0);
        snakeY.add(0);

        moveMap = new int[N][N];
        moveMap[0][0] = 1;

        int dirIdx = 0, time = 0;
        while (true) {
            if (changes.containsKey(time)) {
                if (changes.get(time) == 'D') dirIdx = (dirIdx + 1) % 4;
                else {
                    if (dirIdx == 0) dirIdx = 3;
                    else dirIdx--;
                }
            }
            time++;


            int nextX = snakeX.peekFirst() + dirX[dirIdx], nextY = snakeY.peekFirst() + dirY[dirIdx];
            if (isWall(nextX, nextY) || moveMap[nextY][nextX] == 1) break;

            if (map[nextY][nextX] != APPLE) {
                moveMap[snakeY.pollLast()][snakeX.pollLast()] = 0;
            } else map[nextY][nextX] = 0;

            moveMap[nextY][nextX] = 1;
            snakeX.addFirst(nextX);
            snakeY.addFirst(nextY);
        }

        bw.write(time + "\n");

        bw.flush();
        bw.close();
    }

    private static boolean isWall(int x, int y) {
        return !(x >= 0 && y >= 0 && x < N && y < N);
    }
}
