package bs;

import java.io.*;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BOJ_G2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), C = parseInt(inputs[1]);
        int[] houses = new int[N];
        for (int i = 0; i < N; i++) houses[i] = parseInt(br.readLine());

        Arrays.sort(houses);
        int left = 1, right = houses[N - 1] - houses[0];
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = countWifi(houses, mid);
            if (cnt >= C) left = mid + 1;
            else right = mid - 1;
        }

        bw.write((left - 1) + "\n");

        bw.flush();
        bw.close();
    }

    static int countWifi(int[] houses, int dist) {
        int prevPos = houses[0], cnt = 1;
        for (int i = 1; i < houses.length; i++) {
            if (houses[i] - prevPos >= dist) {
                prevPos = houses[i];
                cnt++;
            }
        }
        return cnt;
    }
}
