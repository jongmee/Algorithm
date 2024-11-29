package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S2529 {
    private static char[] ops;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        k = parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        ops = new char[k];
        for (int i = 0; i < k; i++) ops[i] = inputs[i].charAt(0);
        minNum = Long.MAX_VALUE;
        maxNum = Long.MIN_VALUE;

        int[] visited = new int[10], nowArr = new int[k + 1];
        for (int i = 9; i >= 0; i--) {
            visited[i] = 1;
            nowArr[0] = i;
            find(nowArr, 1, i, visited);
            visited[i] = 0;
            nowArr[0] = 0;
        }

        bw.write(maxString() + "\n" + minString() + "\n");

        bw.flush();
        bw.close();
    }

    private static String maxString() {
        String maxString = String.valueOf(maxNum);
        while (maxString.length() < k + 1) maxString = "0" + maxString;
        return maxString;
    }

    private static String minString() {
        String minString = String.valueOf(minNum);
        while (minString.length() < k + 1) minString = "0" + minString;
        return minString;
    }


    private static long minNum, maxNum;

    private static void find(int[] nowArr, int idx, int prevNum, int[] visited) {
        if (idx == k + 1) {
            long num = 0;
            for (int n : nowArr) num = num * 10 + n;
            minNum = Long.min(minNum, num);
            maxNum = Long.max(maxNum, num);
            return;
        }

        for (int i = 9; i >= 0; i--) {
            if (visited[i] == 0 && check(prevNum, i, ops[idx - 1])) {
                visited[i] = 1;
                nowArr[idx] = i;
                find(nowArr, idx + 1, i, visited);
                visited[i] = 0;
                nowArr[idx] = 0;
            }
        }
    }

    private static boolean check(int prevNum, int nowNum, char op) {
        if (op == '<' && prevNum < nowNum) return true;
        if (op == '>' && prevNum > nowNum) return true;
        return false;
    }
}
