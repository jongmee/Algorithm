package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BOJ_S3273 {
    private static int MAX = 100002;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");

        int[] seq = new int[n];
        for (int i = 0; i < n; i++) seq[i] = parseInt(inputs[i]);

        int x = parseInt(br.readLine());

        Arrays.sort(seq);

        int left = 0, right = n, cnt = 0;
        while (left < n) {
            int tmp = getRight(left, right, x, seq);
            if (tmp != MAX) {
                cnt++;
                right = tmp;
            }
            left++;
        }

        bw.write(cnt + "\n");

        bw.flush();
        bw.close();
    }

    /*
     * left에서 right 사이만을 봐서 시간복잡도를 log(N)으로 유지한다.
     */
    private static int getRight(int left, int right, int x, int[] seq) {
        for (int i = (right - 1); i > left; i--) {
            if (seq[i] + seq[left] == x) {
                return i;
            }
        }
        return MAX;
    }
}
