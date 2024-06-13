package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class BOJ_G1722 {
    private static long[] factorial;
    private static int[] ans;
    private static int N, ansIdx;
    private static List<Integer> pool = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        ansIdx = 0;
        ans = new int[N];
        factorial = new long[N + 1];
        factorial[0] = 1;
        setFactorial(N);
        fillPool();

        String[] line = br.readLine().split(" ");
        int command = Integer.parseInt(line[0]);
        if (command == 1) {
            long order = Long.parseLong(line[1]);

            for (int j = N; j >= 1; j--) {
                ans[ansIdx] = minFromPool();//
                while (order > factorial[j - 1]) {
                    ans[ansIdx] = minNext(ans[ansIdx]);
                    order -= factorial[j - 1];
                }
                ansIdx++;
            }
            for (int j = 0; j < N; j++) bw.write(ans[j] + " ");
            bw.write("\n");
        } else {
            int[] sequence = new int[N];
            for (int i = 0; i < N; i++) sequence[i] = Integer.parseInt(line[i + 1]);
            long order = 0;
            for (int i = 0; i < N; i++) {
                int min = minFromPool();
                while (sequence[i] != min) {
                    min = minNext(min);
                    order += factorial[N - i - 1];
                }
            }
            bw.write((order + 1) + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void fillPool() {
        for (int i = 1; i <= N; i++) {
            pool.add(i);
        }
    }

    private static int minFromPool() {
        int re = 25, idx = -1;
        for (int i = 0; i < pool.size(); i++) {
            if (re > pool.get(i)) {
                re = pool.get(i);
                idx = i;
            }
        }
        pool.remove(idx);
        return re;
    }

    private static int minNext(int ori) {
        int min = 25, idx = -1;
        for (int i = 0; i < pool.size(); i++) {
            int num = pool.get(i);
            if (ori < num && min > num) {
                min = num;
                idx = i;
            }
        }
        pool.remove(idx);
        pool.add(ori);
        return min;
    }

    private static void setFactorial(int N) {
        factorial[1] = 1;
        for (int i = 2; i <= N; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }
}
