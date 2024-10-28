package implementation;

class Programmers_Level2_178870 {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {0, 0};
        int start = 0, end = 0, sum = sequence[0], tmpLen = 1, minLen = Integer.MAX_VALUE, totalLen = sequence.length;
        while (start < totalLen) {
            if (sum == k) {
                if (minLen > tmpLen) {
                    minLen = tmpLen;
                    answer = new int[]{start, end};
                }
                sum -= sequence[start];
                start++;
                tmpLen--;
            } else if (sum > k) {
                sum -= sequence[start];
                start++;
                tmpLen--;
            } else if (sum < k) {
                end++;
                if (end >= totalLen) break;
                sum += sequence[end];
                tmpLen++;
            }
        }
        return answer;
    }
}
