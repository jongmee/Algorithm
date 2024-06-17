package adhoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_G27172 {
    static int[] nums = new int[1_000_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        int[] scores = new int[N + 1]; // 인덱스에 점수를 저장
        int[] inputNums = new int[N + 1];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(line[i]);
            inputNums[i + 1] = num;
            nums[num] = i + 1; // 인덱스 저장
        }

        Arrays.sort(inputNums);

        for (int i = 1; i < N; i++) {
            for (int j = inputNums[i] * 2; j <= inputNums[N]; j += inputNums[i]) {
                int idx = nums[j];
                if (idx != 0) {
                    scores[nums[inputNums[i]]]++;
                    scores[idx]--;
                }
            }
        }

        for (int i = 1; i <= N; i++) bw.write(scores[i] + " ");

        bw.flush();
        bw.close();
    }
}
