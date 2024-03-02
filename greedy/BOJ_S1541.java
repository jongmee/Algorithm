package greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BOJ_S1541 {
    /*
    음수를 최대로 만든다.
    '55-50+40' 라면 - 뒤에 +가 나오는 동안 괄호로 묶어준다.
    '55-(50+40)'
    '55-50-40' 라면 묶을 필요 없다.

    55 50 40
    - +
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String expression = br.readLine();

        String[] numbersInput = expression.split("[+\\-]");
        int numbersCnt = numbersInput.length;
        int[] numbers = new int[numbersCnt];
        for (int i = 0; i < numbersCnt; i++) numbers[i] = parseInt(numbersInput[i]);

        int idx = 0;
        char[] operators = new char[numbersCnt - 1];
        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '-') operators[idx++] = c;
        }

        int sum = numbers[0];
        boolean flag = false;
        List<Integer> group = new ArrayList<>();
        for (int i = 0; i < numbersCnt - 1; i++) {
            if (operators[i] == '-') {
                flag = true;
                if (!group.isEmpty()) {
                    sum -= sumAllIn(group);
                    group.clear();
                }
                group.add(numbers[i + 1]);
            } else {
                if (flag) group.add(numbers[i + 1]);
                else sum += numbers[i + 1];
            }
        }

        if (flag) sum -= sumAllIn(group);

        bw.write(String.valueOf(sum));

        bw.flush();
        bw.close();
    }

    static int sumAllIn(List<Integer> group) {
        int sum = 0;
        for (Integer number : group) sum += number;
        return sum;
    }
}
