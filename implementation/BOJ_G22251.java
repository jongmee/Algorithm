package implementation;

import java.io.*;

import static java.lang.Integer.parseInt;

public class BOJ_G22251 {
    static int[][] numbers = {
            {1, 1, 1, 0, 1, 1, 1},
            {0, 0, 1, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 1, 0, 0, 1},
            {1, 1, 0, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 1},
            {0, 1, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), K = parseInt(inputs[1]), P = parseInt(inputs[2]), X = parseInt(inputs[3]);

        int[] display = convertToArray(K, X);

        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (i == X) continue;
            int[] tmpChange = convertToArray(K, i);
            int cnt = 0;
            boolean can = true;
            for (int k = 0; k < K; k++) {
                for (int n = 0; n < 7; n++) {
                    if (numbers[tmpChange[k]][n] != numbers[display[k]][n]) cnt++;
                    if (cnt > P) {
                        can = false;
                        break;
                    }
                }
            }
            if (can) result++;
        }

        bw.write(result + "\n");

        bw.flush();
        bw.close();
    }

    static int[] convertToArray(int size, int num) {
        int[] array = new int[size];
        int idx = 0;
        while (num != 0) {
            array[idx++] = num % 10;
            num /= 10;
        }
        return array;
    }
}
