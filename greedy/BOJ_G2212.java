package greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;

public class BOJ_G2212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int K = parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        int[] sensorsInput = new int[N];
        for (int i = 0; i < N; i++) sensorsInput[i] = parseInt(inputs[i]);

        Arrays.sort(sensorsInput);

        PriorityQueue<Sensor> pq = new PriorityQueue<>();
        for (int i = 0; i < N - 1; i++) {
            int diff = sensorsInput[i + 1] - sensorsInput[i];
            if (diff != 0)
                pq.add(new Sensor(sensorsInput[i], diff));
        }

        List<Integer> centers = new ArrayList<>();
        while (K-- > 1 && !pq.isEmpty()) {
            Sensor sensor = pq.poll();
            centers.add(sensor.point);
        }

        int result = 0;
        int startPosition = sensorsInput[0];
        for (int i = 0; i < N - 1; i++) {
            if (centers.contains(sensorsInput[i])) {
                result += sensorsInput[i] - startPosition;
                startPosition = sensorsInput[i + 1];
            }
        }
        if (N != 1 && !centers.contains(sensorsInput[N - 2]))
            result += sensorsInput[N - 1] - startPosition;

        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }

    static class Sensor implements Comparable<Sensor> {
        int point, diff;

        public Sensor(int point, int diff) {
            this.point = point;
            this.diff = diff;
        }

        public int compareTo(Sensor other) {
            return Integer.compare(other.diff, diff);
        }
    }
}
