package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class BOJ_G1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        
        PriorityQueue<Long> plusPq = new PriorityQueue<>((a, b) -> Long.compare(b, a));
        PriorityQueue<Long> minusPq = new PriorityQueue<>();
        int zeroCnt = 0;
        
        for (int i = 0; i < N; i++) {
            long number = parseLong(br.readLine());
            if (number < 0) minusPq.add(number);
            else if (number > 0) plusPq.add(number);
            else zeroCnt++;
        }

        long ans = getMax(minusPq, true, zeroCnt);
        ans += getMax(plusPq, false, zeroCnt);

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }

    private static long getMax(PriorityQueue<Long> pq, boolean isMinus, int zeroCnt) {
        long result = 0;
        while (!pq.isEmpty()) {
            if (pq.size() == 1) {
                if (!(isMinus && zeroCnt > 0)) result += pq.poll();
                else pq.poll();
            } else {
                long a = pq.poll(), b = pq.poll();
                if (a * b > a + b) result += a * b;
                else result += a + b;
            }
        }
        return result;
    }
}
