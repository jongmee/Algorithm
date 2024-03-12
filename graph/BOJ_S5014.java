package graph;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BOJ_S5014 {
    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int totalFloors = parseInt(inputs[0]), startFloor = parseInt(inputs[1]), destFloor = parseInt(inputs[2]);
        int upAmount = parseInt(inputs[3]), downAmount = parseInt(inputs[4]);

        if (startFloor == destFloor) {
            write(bw, 0);
            closeWriter(bw);
            return;
        }
        int[] dir = {upAmount, downAmount * (-1)};

        Queue<Integer> q = new LinkedList<>();
        int[] counts = new int[totalFloors + 1];
        Arrays.fill(counts, MAX);

        q.add(startFloor);
        counts[startFloor] = 0;

        while (!q.isEmpty()) {
            int front = q.poll();
            if (front == destFloor) break;
            for (int i = 0; i < 2; i++) {
                int next = front + dir[i];
                if (next >= 1 && next <= totalFloors && counts[front] + 1 < counts[next]) {
                    q.add(next);
                    counts[next] = counts[front] + 1;
                }
            }
        }

        if (counts[destFloor] == MAX) bw.write("use the stairs");
        else write(bw, counts[destFloor]);

        closeWriter(bw);
    }

    static void write(BufferedWriter bw, int value) throws IOException {
        bw.write(String.valueOf(value));
    }

    static void closeWriter(BufferedWriter bw) throws IOException {
        bw.flush();
        bw.close();
    }
}
