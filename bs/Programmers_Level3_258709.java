package bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Programmers_Level3_258709 {
    private int[][] dice;
    private int diceCnt, maxWinCnt;
    private List<Integer> ans;

    public List<Integer> solution(int[][] dice) {
        this.dice = dice;
        diceCnt = dice.length;
        maxWinCnt = -1;
        calculated = new HashSet<>();
        ans = new ArrayList<>();

        pickDice(0, new HashSet<>());
        Collections.sort(ans);
        return ans.stream().map(a -> a + 1).collect(Collectors.toList());
    }

    private Set<Set<Integer>> calculated;

    private void pickDice(int startDice, Set<Integer> picked) {
        if (diceCnt / 2 == picked.size()) {
            Set<Integer> BDices = makeBDice(picked);
            if (!calculated.contains(picked) || !calculated.contains(BDices)) {
                calculateAllCases(picked, BDices);
                calculated.add(picked);
                calculated.add(BDices);
            }
            return;
        }
        for (int i = startDice; i < diceCnt; i++) {
            picked.add(i);
            pickDice(i + 1, picked);
            picked.remove(i);
        }
    }

    private Set<Integer> makeBDice(Set<Integer> ADices) {
        Set<Integer> BDices = new HashSet<>();
        for (int i = 0; i < diceCnt; i++) {
            if (!ADices.contains(i)) BDices.add(i);
        }
        return BDices;
    }

    private List<Integer> ADiceSums;
    private List<Integer> BDiceSums;

    private void calculateAllCases(Set<Integer> ADices, Set<Integer> BDices) { // 특정 주사위를 잡은 상황에서 A가 몇 번 이기냐
        ADiceSums = new ArrayList<>();
        sumDice(new ArrayList<>(ADices), 0, 0, ADiceSums);
        Collections.sort(ADiceSums);
        BDiceSums = new ArrayList<>();
        sumDice(new ArrayList<>(BDices), 0, 0, BDiceSums);
        Collections.sort(BDiceSums);

        int AWinCnt = 0, BWinCnt = 0, totalCnt = ADiceSums.size();
        for (int aSum : ADiceSums) {
            int BIdxLowerBound = bsLowerBound(BDiceSums, aSum);
            int sameValueLastIdx = BIdxLowerBound;
            while (sameValueLastIdx < totalCnt && BDiceSums.get(sameValueLastIdx) == aSum) sameValueLastIdx++;
            int drawCnt = sameValueLastIdx - BIdxLowerBound;
            int winCnt = BIdxLowerBound;
            int loseCnt = totalCnt - drawCnt - winCnt;
            AWinCnt += winCnt;
            BWinCnt += loseCnt;
        }

        if (AWinCnt < BWinCnt && maxWinCnt < BWinCnt) {
            maxWinCnt = BWinCnt;
            ans = new ArrayList<>(BDices);
        }
        if (BWinCnt < AWinCnt && maxWinCnt < AWinCnt) {
            maxWinCnt = AWinCnt;
            ans = new ArrayList<>(ADices);
        }
    }

    private void sumDice(List<Integer> pickedDices, int diceIdx, int sum, List<Integer> sums) {
        if (diceIdx == pickedDices.size()) {
            sums.add(sum);
            return;
        }
        int[] nowDice = dice[pickedDices.get(diceIdx)];
        for (int i = 0; i < 6; i++) {
            sumDice(pickedDices, diceIdx + 1, sum + nowDice[i], sums);
        }
    }

    private int bsLowerBound(List<Integer> sums, int target) {
        int start = 0, end = sums.size(), mid = -1;
        while (start < end) {
            mid = start + (end - start) / 2;
            if (sums.get(mid) < target) start = mid + 1;
            if (sums.get(mid) >= target) end = mid;
        }
        if (start < sums.size() && sums.get(start) < target) return start + 1;
        return start;
    }

    public static void main(String[] args) {
        Programmers_Level3_258709 solution = new Programmers_Level3_258709();
        System.out.println(solution.solution(new int[][]{{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}}));
        System.out.println(solution.solution(new int[][]{{1, 2, 3, 4, 5, 6}, {2, 2, 4, 4, 6, 6}}));
        System.out.println(solution.solution(new int[][]{{40, 41, 42, 43, 44, 45}, {43, 43, 42, 42, 41, 41}, {1, 1, 80, 80, 80, 80}, {70, 70, 1, 1, 70, 70}}));
    }
}
