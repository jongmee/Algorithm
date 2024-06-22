package bs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class BOJ_G7453 {
    static int N;
    static long[] ABSum, CDSum;
    static long[] AArray, BArray, CArray, DArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = parseInt(br.readLine());
        ABSum = new long[N * N];
        CDSum = new long[N * N];
        AArray = new long[N];
        BArray = new long[N];
        CArray = new long[N];
        DArray = new long[N];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            AArray[i] = parseLong(line[0]);
            BArray[i] = parseLong(line[1]);
            CArray[i] = parseLong(line[2]);
            DArray[i] = parseLong(line[3]);
        }

        sumArrays();
        Arrays.sort(ABSum);
        Arrays.sort(CDSum);
        countSumZero();

        bw.write(cnt + "\n");

        bw.flush();
        bw.close();
    }

    static void sumArrays() {
        for (int ac = 0; ac < N; ac++) {
            for (int bd = 0; bd < N; bd++) {
                ABSum[ac * N + bd] = AArray[ac] + BArray[bd];
                CDSum[ac * N + bd] = CArray[ac] + DArray[bd];
            }
        }
    }

    static long cnt = 0;

    static void countSumZero() {
        for (int ac = 0; ac < N; ac++) {
            for (int bd = 0; bd < N; bd++) {
                long target = ABSum[ac * N + bd] * -1;
                int idx = bsLowerBound(CDSum, target);
                if (idx >= 0) {
                    int sameCnt = countSameVal(target, idx);
                    cnt += sameCnt;
                }
            }
        }
    }

    static int countSameVal(long target, int targetIdx) {
        long upperVal = target + 1;
        int upperIdx = bsLowerBound(CDSum, upperVal);
        if (upperIdx < 0) upperIdx = upperIdx * -1;
        return upperIdx - targetIdx;
    }

    static int bsLowerBound(long[] array, long target) {
        int left = 0, right = array.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (array[mid] >= target) right = mid;
            else left = mid + 1;
        }
        if (left < array.length && array[left] == target) return left;
        return -1 * left;
    }
}
