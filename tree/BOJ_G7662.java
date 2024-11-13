package tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class BOJ_G7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            int k = parseInt(br.readLine());
            TreeSet<Long> treeSet = new TreeSet<>();
            Map<Long, Integer> countMap = new HashMap<>();

            for (int i = 0; i < k; i++) {
                String[] inputs = br.readLine().split(" ");
                if (inputs[0].equals("I")) {
                    long num = parseLong(inputs[1]);
                    treeSet.add(num);
                    if (countMap.containsKey(num)) {
                        int count = countMap.get(num);
                        countMap.put(num, count + 1);
                    } else countMap.put(num, 1);
                } else {
                    if (treeSet.isEmpty()) continue;
                    if (inputs[1].equals("1")) {
                        long maxVal = treeSet.last();
                        if (!countMap.containsKey(maxVal)) continue;
                        int count = countMap.get(maxVal);
                        if (count == 1) {
                            countMap.remove(maxVal);
                            treeSet.remove(maxVal);
                        } else countMap.put(maxVal, count - 1);
                    } else if (inputs[1].equals("-1")) {
                        long minVal = treeSet.first();
                        if (!countMap.containsKey(minVal)) continue;
                        int count = countMap.get(minVal);
                        if (count == 1) {
                            countMap.remove(minVal);
                            treeSet.remove(minVal);
                        } else countMap.put(minVal, count - 1);
                    }
                }
            }

            if (treeSet.isEmpty()) bw.write("EMPTY\n");
            else bw.write(treeSet.last() + " " + treeSet.first() + "\n");
        }

        bw.flush();
        bw.close();
    }
}
