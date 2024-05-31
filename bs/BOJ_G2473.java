package bs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_G2473 {
    static long[] ans = new long[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");

        int acidCnt = 0, baseCnt = 0;
        long[] acids = new long[N];
        long[] bases = new long[N];
        for (int i = 0; i < N; i++) {
            long val = Long.parseLong(line[i]);
            if (val > 0) acids[acidCnt++] = val;
            else bases[baseCnt++] = val * -1;
        }

        Arrays.sort(acids, 0, acidCnt);
        Arrays.sort(bases, 0, baseCnt);

        long minVal = Long.MAX_VALUE;
        for (int i = 0; i < acidCnt; i++) {
            if (baseCnt == 0) break;
            for (int j = i + 1; j < acidCnt; j++) {
                long sum = acids[i] + acids[j];
                int idx = bs(bases, baseCnt, sum);
                if (idx > 0) {
                    minVal = 0;
                    saveAns(bases[idx] * -1, acids[i], acids[j]);
                    break;
                } else {
                    idx = (idx * -1) - 1;
                    if (idx >= 0 && idx < baseCnt && minVal > Math.abs(sum - bases[idx])) {
                        minVal = Math.abs(sum - bases[idx]);
                        saveAns(bases[idx] * -1, acids[i], acids[j]);
                    }
                    idx--;
                    if (idx >= 0 && idx < baseCnt && minVal > Math.abs(sum - bases[idx])) {
                        minVal = Math.abs(sum - bases[idx]);
                        saveAns(bases[idx] * -1, acids[i], acids[j]);
                    }
                }
            }
        }
        for (int i = 0; i < baseCnt; i++) {
            if (acidCnt == 0) break;
            for (int j = i + 1; j < baseCnt; j++) {
                long sum = bases[i] + bases[j];
                int idx = bs(acids, acidCnt, sum);
                if (idx > 0) {
                    minVal = 0;
                    saveAns(bases[j] * -1, bases[i] * -1, acids[idx]);
                } else {
                    idx = (idx * -1) - 1;
                    if (idx >= 0 && idx < acidCnt && minVal > Math.abs(sum - acids[idx])) {
                        minVal = Math.abs(sum - acids[idx]);
                        saveAns(bases[j] * -1, bases[i] * -1, acids[idx]);
                    }
                    idx--;
                    if (idx >= 0 && idx < acidCnt && minVal > Math.abs(sum - acids[idx])) {
                        minVal = Math.abs(sum - acids[idx]);
                        saveAns(bases[j] * -1, bases[i] * -1, acids[idx]);
                    }
                }
            }
        }
        if (acidCnt >= 3) {
            long firstAcids = acids[0] + acids[1] + acids[2];
            if (minVal > firstAcids) {
                minVal = firstAcids;
                saveAns(acids[0], acids[1], acids[2]);
            }
        }
        if (baseCnt >= 3) {
            long firstBases = bases[0] + bases[1] + bases[2];
            if (minVal > firstBases) {
                saveAns(bases[2] * -1, bases[1] * -1, bases[0] * -1);
            }
        }

        for (int i = 0; i < 3; i++) bw.write(ans[i] + " ");

        bw.flush();
        bw.close();
    }

    // Arrays.binarySearch 교체 연습
    static int bs(long[] bases, int size, long target) {
        int left = 0, right = size - 1;
        while (left <= right) {
            int mid = right + ((left - right) / 2);
            if (bases[mid] == target) return mid;
            if (bases[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return (left + 1) * -1;
    }

    static void saveAns(long a, long b, long c) {
        ans[0] = a;
        ans[1] = b;
        ans[2] = c;
    }
}
