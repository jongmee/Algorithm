package implementation;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BOJ_G7490 {
    static int[] OPERATORS = {1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            int N = parseInt(br.readLine());
            int[] sequence = new int[N];
            for (int i = 0; i < N; i++) sequence[i] = i + 1;
            int[] operators = new int[N - 1];
            ans = new ArrayList<>();
            recurseAllConditions(sequence, operators, N, 0);
            Collections.sort(ans);
            for (String line : ans) bw.write(line);
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    static boolean can = false;
    static List<String> ans;

    static void recurseAllConditions(int[] sequence, int[] operators, int N, int startIdx) {
        if (startIdx == N - 1 && calculate(sequence, operators, N) == 0) {
            can = true;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N - 1; i++) {
                sb.append(sequence[i]);
                if (operators[i] == 1) sb.append("+");
                else if (operators[i] == 0) sb.append(" ");
                else sb.append("-");
            }
            sb.append(sequence[N - 1]).append("\n");
            ans.add(sb.toString());
            return;
        }
        if (startIdx == N - 1) return;
        for (int OPERATOR : OPERATORS) {
            operators[startIdx] = OPERATOR;
            recurseAllConditions(sequence, operators, N, startIdx + 1);
        }
    }

    static int calculate(int[] sequence, int[] operators, int N) {
        int idx = 0;
        List<Integer> numbers = new ArrayList<>();
        while (idx < N - 1) {
            int tmp = sequence[idx];
            while (idx < N - 1 && operators[idx++] == 0)
                tmp = tmp * 10 + sequence[idx];
            numbers.add(tmp);
        }
        if (operators[N - 2] != 0) numbers.add(sequence[N - 1]);
        int result = numbers.get(0);
        int numIdx = 1;
        for (int i = 0; i < N - 1; i++)
            if (operators[i] != 0) result += operators[i] * numbers.get(numIdx++);
        return result;
    }
}
