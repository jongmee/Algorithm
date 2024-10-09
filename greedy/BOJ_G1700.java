package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class BOJ_G1700 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), K = parseInt(inputs[1]);
        inputs = br.readLine().split(" ");

        int[] orders = new int[K];
        Deque<Integer>[] deque = new Deque[K + 1];

        for (int i = 0; i < K; i++) {
            orders[i] = parseInt(inputs[i]);
            if (deque[orders[i]] == null) deque[orders[i]] = new ArrayDeque<>(List.of(i));
            else deque[orders[i]].add(i);
        }

        int ans = 0;
        Set<Integer> plugs = new HashSet<>();
        for (int i = 0; i < K; i++) {
            if (plugs.contains(orders[i])) {
                deque[orders[i]].pollFirst();
            } else if (plugs.size() < N && !plugs.contains(orders[i])) {
                plugs.add(orders[i]);
                deque[orders[i]].pollFirst();
            } else if (plugs.size() >= N) {
                int removePlug = -1, maxIdx = -1;
                for (int plug : plugs) {
                    if (deque[plug].isEmpty()) {
                        removePlug = plug;
                        break;
                    }
                    int idx = deque[plug].peekFirst();
                    if (idx > maxIdx) {
                        maxIdx = idx;
                        removePlug = plug;
                    }
                }
                ans++;
                plugs.remove(removePlug);
                plugs.add(orders[i]);
                deque[orders[i]].pollFirst();
            }
        }

        bw.write(ans + "\n");
        
        bw.flush();
        bw.close();
    }
}
