package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BOJ_G16637 {
    static List<Integer> numbers = new ArrayList<>();
    static List<Character> operations = new ArrayList<>();
    static int numCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        String[] inputs = br.readLine().split("");

        for (int i = 0; i < N; i += 2) numbers.add(parseInt(inputs[i]));
        for (int i = 1; i < N; i += 2) operations.add(inputs[i].charAt(0));
        numCnt = numbers.size();

        dfs(1, numbers.get(0));
        bw.write(maxSum + "\n");

        bw.flush();
        bw.close();
    }

    static long maxSum = Long.MIN_VALUE;

    static void dfs(int startNumIdx, long sum) {
        if (startNumIdx >= numCnt) {
            maxSum = Long.max(maxSum, sum);
            return;
        }

        char op = operations.get(startNumIdx - 1);

        // 1. 괄호 없음
        long nextNum = numbers.get(startNumIdx);
        dfs(startNumIdx + 1, calculate(sum, op, nextNum));

        // 2. 괄호 있음
        if (startNumIdx + 1 < numCnt) {
            nextNum = calculate(numbers.get(startNumIdx), operations.get(startNumIdx), numbers.get(startNumIdx + 1));
            dfs(startNumIdx + 2, calculate(sum, op, nextNum));
        }
    }

    static long calculate(long prevSum, char op, long nextNum) {
        if (op == '+') return prevSum + nextNum;
        if (op == '-') return prevSum - nextNum;
        return prevSum * nextNum;
    }
}
