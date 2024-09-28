package bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Programmers_Level2_150368 {
    /*
    1 ≤ users의 길이 = n ≤ 100
    1 ≤ emoticons의 길이 = m ≤ 7
    이모티콘마다 할인율은 다를 수 있으며, 할인율은 10%, 20%, 30%, 40% 중 하나로 설정됩니다. (4^7=16384)
    1638400*7
     */
    private static final int[] sales = {10, 20, 30, 40};

    public int[] solution(int[][] users, int[] emoticons) {
        plusUserCnt = 0;
        totalSales = 0;
        checkAllSales(users, emoticons, new ArrayList<Integer>());
        return new int[]{plusUserCnt, totalSales};
    }

    private int plusUserCnt, totalSales;

    private void checkAllSales(int[][] users, int[] emoticons, List<Integer> saleRates) {
        if (saleRates.size() == emoticons.length) {
            int[] checkUser = checkUsers(saleRates, emoticons, users);
            if (plusUserCnt < checkUser[0] ||
                    (plusUserCnt == checkUser[0] && totalSales < checkUser[1])) {
                plusUserCnt = checkUser[0];
                totalSales = checkUser[1];
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int saleRate = sales[i];
            saleRates.add(saleRate);
            checkAllSales(users, emoticons, saleRates);
            saleRates.remove(saleRates.size() - 1);
        }
    }

    private int[] checkUsers(List<Integer> saleRates, int[] emoticons, int[][] users) {
        int plusUserCnt = 0, totalSales = 0;
        int[] saledPrices = new int[emoticons.length];
        for (int i = 0; i < emoticons.length; i++) saledPrices[i] = applySale(saleRates.get(i), emoticons[i]);
        for (int[] userData : users) {
            int totalCache = 0;
            for (int i = 0; i < emoticons.length; i++) {
                if (saleRates.get(i) >= userData[0]) {
                    totalCache += saledPrices[i];
                }
            }
            if (totalCache >= userData[1]) plusUserCnt++;
            else totalSales += totalCache;
        }
        return new int[]{plusUserCnt, totalSales};
    }

    private int applySale(int rate, int cost) { // rate: 40% (*0.6)
        int re = cost * (100 - rate);
        return re / 100;
    }

    public static void main(String[] args) {
        Programmers_Level2_150368 solution = new Programmers_Level2_150368();
        System.out.println(Arrays.toString(solution.solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000})));
        System.out.println(Arrays.toString(solution.solution(
                new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}},
                new int[]{1300, 1500, 1600, 4900})));
    }
}
