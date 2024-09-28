package tree;

import java.util.Arrays;

class Programmers_Level3_150367 {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int j = 0; j < numbers.length; j++) {
            long number = numbers[j];
            String binary = Long.toBinaryString(number);
            int zeroCnt = getZeroCntToMakeFullBinary(binary);
            binary = "0".repeat(zeroCnt) + binary;
            can = false;
            int depth = getDepth(binary), rootIdx = binary.length() / 2;
            if (binary.charAt(rootIdx) == '1') {
                can = true;
                int gap = 1;
                for (int i = 1; i < depth; i++) gap *= 2;
                travel(binary, rootIdx, gap, true);
            }
            if (can) answer[j] = 1;
            else answer[j] = 0;
        }
        return answer;
    }

    private boolean can;

    private void travel(String binary, int start, int gap, boolean parentExist) {
        if (!can) return;
        if (start < 0 || start >= binary.length()) return;
        if (!parentExist && binary.charAt(start) == '1') {
            can = false;
            return;
        }
        if (gap == 0) return;
        travel(binary, start - gap, gap / 2, binary.charAt(start) == '1');
        travel(binary, start + gap, gap / 2, binary.charAt(start) == '1');
    }

    private int getZeroCntToMakeFullBinary(String binary) {
        int gap = 2;
        int size = 1;
        while (true) {
            if (binary.length() <= size) {
                int diff = size - binary.length();
                return diff;
            }
            size += gap;
            gap *= 2;
        }
    }

    private int getDepth(String str) {
        int len = str.length() + 1, re = 1;
        while (len >> re >= 2) re++;
        return re - 1;
    }

    public static void main(String[] args) {
        Programmers_Level3_150367 solution = new Programmers_Level3_150367();
        System.out.println(Arrays.toString(solution.solution(new long[]{2147516555l})));
        System.out.println(Arrays.toString(solution.solution(new long[]{7, 42, 5})));
    }
}
