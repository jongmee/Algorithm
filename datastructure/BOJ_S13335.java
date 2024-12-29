package datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

import static java.lang.Integer.parseInt;

public class BOJ_S13335 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int n = parseInt(inputs[0]), w = parseInt(inputs[1]), L = parseInt(inputs[2]);
        inputs = br.readLine().split(" ");
        int[] trucks = new int[n];
        for (int i = 0; i < n; i++) trucks[i] = parseInt(inputs[i]);

        Deque<Integer> dq = new ArrayDeque<>(); // 다리를 queue로 관리
        for (int i = 0; i < w; i++) dq.add(0); // 다리의 빈 부분은 0으로 표시

        int sum = 0, time = 0, truckNum = 0;
        do {
            sum -= dq.pollFirst(); // 다리 가장 앞 부분의 트럭(혹은 빈 칸)을 제거한다.
            if (truckNum < n && sum + trucks[truckNum] <= L) { // 최대 무게를 넘지 않는 트럭이면 다리에 추가한다.
                sum += trucks[truckNum];
                dq.add(trucks[truckNum++]);
            } else {
                dq.add(0); // 다리의 빈 부분은 0으로 표시
            }
            time++;
        } while (sum > 0); // 다리에 트럭이 없다면 모든 트럭을 옮긴 것


        bw.write(time + "\n");

        bw.flush();
        bw.close();
    }
}
