package implementation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;

class Programmers_Level2_67257 {
    private List<Long> nums;
    private List<Character> ops;
    private List<Character> uniqueOps;
    private int opsSize;

    public long solution(String expression) {
        maxAns = 0;
        int len = expression.length();
        LinkedHashSet<Character> uniqueOpsSet = new LinkedHashSet<>();
        nums = new ArrayList<>();
        ops = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            long tmp = 0;
            while (i < len && !isChar(expression.charAt(i))) {
                tmp = tmp * 10 + (expression.charAt(i) - '0');
                i++;
            }
            nums.add(tmp);
            if (i < len) {
                ops.add(expression.charAt(i));
                uniqueOpsSet.add(expression.charAt(i));
            }
        }
        opsSize = uniqueOpsSet.size();
        uniqueOps = new ArrayList<>(uniqueOpsSet);
        simulate(new int[opsSize], 0);
        return maxAns;
    }

    Deque<Character> priority = new ArrayDeque<>();

    private void simulate(int[] visited, int depth) {
        if (depth == opsSize) {
            calculateTotal();
            return;
        }
        for (int i = 0; i < opsSize; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                priority.addLast(uniqueOps.get(i));
                simulate(visited, depth + 1);
                visited[i] = 0;
                priority.pollLast();
            }
        }
    }

    private long maxAns;

    private void calculateTotal() {
        List<Long> numBuffer = new ArrayList<>(nums);
        List<Character> opBuffer = new ArrayList<>(ops);
        List<Long> tmpNums = new ArrayList<>();
        List<Character> tmpOps = new ArrayList<>();
        for (char p : priority) {
            tmpNums.clear();
            tmpOps.clear();
            boolean isCalculated = false;
            for (int i = 0; i < opBuffer.size(); i++) {
                if (opBuffer.get(i) == p) {
                    long num1 = numBuffer.get(i), num2 = numBuffer.get(i + 1);
                    if (isCalculated) {
                        num1 = tmpNums.get(tmpNums.size() - 1);
                        tmpNums.remove(tmpNums.size() - 1);
                    }
                    long result = calculate(num1, num2, p);
                    tmpNums.add(result);
                    isCalculated = true;
                } else {
                    if (!isCalculated) tmpNums.add(numBuffer.get(i));
                    tmpOps.add(opBuffer.get(i));
                    if (i == opBuffer.size() - 1) tmpNums.add(numBuffer.get(i + 1));
                    isCalculated = false;
                }
            }
            opBuffer.clear();
            opBuffer.addAll(tmpOps);
            numBuffer.clear();
            numBuffer.addAll(tmpNums);
        }
        long result = numBuffer.get(0);
        maxAns = Long.max(maxAns, Math.abs(result));
    }

    private boolean isChar(char c) {
        return c == '+' || c == '-' || c == '*';
    }

    private long calculate(long num1, long num2, char op) {
        if (op == '+') return num1 + num2;
        if (op == '-') return num1 - num2;
        return num1 * num2;
    }

    public static void main(String[] args) {
        Programmers_Level2_67257 solution = new Programmers_Level2_67257();
        System.out.println(solution.solution("100-200*300-500+20"));
    }
}
