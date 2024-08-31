package implementation;

import java.util.ArrayList;
import java.util.List;

class BitonicSequence {
    /*
    증가했다가 감소하는 수열의 최대 길이. (연속되는 같은 수 안 됨)

    [solution1]
    1. 증가하는 수열 먼저 찾고 그 다음 감소하는 길이를 더하기
    2. 만약 연속되는 수가 있다면 마지막 수부터 다시 시작

    [solution2]
    위 방법은 flag에 따른 분기가 있어 실수 여지가 많다. 아래는 좀 더 쉬운, 오버헤드는 좀 더 큰 방법이다.
    1. peak(정점)를 구한다.
    2. 모든 peak를 순회하며 가장 긴 수열을 찾는다.

     */
    public int solution1(int[] nums) {
        int answer = 0;

        int idx = 0, totalLen = nums.length;
        while (idx + 1 < totalLen && nums[idx] >= nums[idx + 1]) idx++;

        int tmpLen = 0;
        while (idx < totalLen) {
            int flag = 0;
            while (idx < totalLen && isIncrement(idx, nums)) {
                tmpLen++;
                idx++;
                flag = 1;
            }

            if (flag == 1) {
                while (idx < totalLen && isDecrement(idx, nums)) {
                    tmpLen++;
                    idx++;
                    flag = 2;
                }
            }

            if (flag != 2) idx++;

            if (flag == 2) answer = Integer.max(answer, tmpLen);
            tmpLen = 1;
        }

        return answer;
    }

    private boolean isIncrement(int idx, int[] nums) {
        if (idx == 0) return true;
        return nums[idx - 1] < nums[idx];
    }

    private boolean isDecrement(int idx, int[] nums) {
        if (idx == 0) return true;
        return nums[idx - 1] > nums[idx];
    }

    public int solution2(int[] nums) {
        int answer = 0;
        int totalLen = nums.length;
        List<Integer> peaks = new ArrayList<>();
        for (int idx = 1; idx < totalLen - 1; idx++) {
            if (nums[idx] > nums[idx - 1] && nums[idx] > nums[idx + 1]) peaks.add(idx);
        }

        for (int peakIdx : peaks) {
            int left = peakIdx, right = peakIdx, tempLen = 1;
            while (left - 1 >= 0 && nums[left] > nums[left - 1]) {
                left--;
                tempLen++;
            }
            while (right + 1 < totalLen && nums[right] > nums[right + 1]) {
                right++;
                tempLen++;
            }
            answer = Integer.max(answer, tempLen);
        }
        return answer;
    }

    public static void main(String[] args) {
        BitonicSequence T = new BitonicSequence();
        System.out.println(T.solution2(new int[]{1, 2, 1, 2, 3, 2, 1}));
        System.out.println(T.solution2(new int[]{1, 1, 2, 3, 5, 7, 4, 3, 1, 2}));
        System.out.println(T.solution2(new int[]{3, 2, 1, 3, 2, 4, 6, 7, 3, 1}));
        System.out.println(T.solution2(new int[]{1, 3, 1, 2, 1, 5, 3, 2, 1, 1}));
    }
}
