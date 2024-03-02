package greedy;

import java.io.*;
import java.util.Arrays;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class BOJ_S2217 {
    /*
    1. 로프 길이를 오름차순으로 정렬한다.
    2. 최대 중량은 (최소 로프 길이 * 로프 개수) 이다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int[] ropes = new int[N];
        for (int i = 0; i < N; i++) ropes[i] = parseInt(br.readLine());

        Arrays.sort(ropes);

        int maxWeight = 0;
        for (int i = 0; i < N; i++) {
            maxWeight = max(maxWeight, ropes[i] * (N - i));
        }

        bw.write(String.valueOf(maxWeight));

        bw.flush();
        bw.close();
    }
}
