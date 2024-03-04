package greedy;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class BOJ_S15903 {
    /*
    [Idea 1]
    1. 카드를 오름차순으로 정렬한다.
    2. 가장 작은 두 수를 더해 저장한다.
    -> m번 동안 시간 복잡도는 O(nlogn * m) = 1,000 * 3 * 1,000 * 15 = 45,000,000
    -> 716 ms

    [Idea 2]
    1. PriorityQueue로 카드를 오름차순으로 관리한다.
    -> Heap으로 구현되어 있기 때문에 O(nlogn) = 1,000 * 3 = 3,000
    -> 168 ms
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] conditions = br.readLine().split(" ");
        int n = parseInt(conditions[0]), m = parseInt(conditions[1]);

        String[] cardsInput = br.readLine().split(" ");

        long result = runWithIdea2(cardsInput, n, m);

        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }

    static long runWithIdea2(String[] cardsInput, int n, int m) {
        PriorityQueue<Long> cards = new PriorityQueue<>(n);
        for (String card : cardsInput) cards.add(parseLong(card));

        while (m-- > 0) {
            long card1 = cards.poll();
            long card2 = cards.poll();
            cards.add(card1 + card2);
            cards.add(card1 + card2);
        }

        long result = 0;
        while (!cards.isEmpty()) result += cards.poll();
        return result;
    }

    static long runWithIdea1(String[] cardsInput, int n, int m) {
        long[] cards = new long[n];
        for (int i = 0; i < n; i++) cards[i] = parseLong(cardsInput[i]);

        while (m-- > 0) {
            Arrays.sort(cards);
            long sum = cards[0] + cards[1];
            cards[0] = sum;
            cards[1] = sum;
        }

        long result = 0;
        for (long card : cards) result += card;
        return result;
    }
}
