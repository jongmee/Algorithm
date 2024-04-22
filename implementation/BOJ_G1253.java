package implementation;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class BOJ_G1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");

        if (N < 3) {
            bw.write("0\n");
            bw.flush();
            bw.close();
            return;
        }

        int[] numbers = new int[N];
        Map<Integer, Number> numbersMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            numbers[i] = parseInt(inputs[i]);
            if (numbersMap.containsKey(numbers[i])) {
                numbersMap.get(numbers[i]).cnt++;
                numbersMap.get(numbers[i]).indexes.add(i);
            } else {
                numbersMap.put(numbers[i], new Number(numbers[i], 1, i));
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int sum = numbers[i] + numbers[j];
                Number number = numbersMap.get(sum);
                if (number != null && !number.counted && !number.onlyContains(i, j)) {
                    result += numbersMap.get(sum).cnt;
                    numbersMap.get(sum).counted = true;
                }
            }
        }

        bw.write(result + "\n");

        bw.flush();
        bw.close();
    }

    static class Number {
        int num, cnt;
        boolean counted = false;
        Set<Integer> indexes = new HashSet<>();

        public Number(int num, int cnt, int idx) {
            this.num = num;
            this.cnt = cnt;
            indexes.add(idx);
        }

        boolean onlyContains(int idx1, int idx2) { // 해당 index만을 가지면 무조건 자기 자신임
            if (!indexes.contains(idx1) && !indexes.contains(idx2)) return false;
            if (indexes.contains(idx1) && indexes.contains(idx2) && indexes.size() == 2) return true;
            return indexes.size() == 1;
        }
    }
}
