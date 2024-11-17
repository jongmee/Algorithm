package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BOJ_S16206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]);

        inputs = br.readLine().split(" ");
        List<Cake> cakes = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int len = parseInt(inputs[i]);
            if (len < 10) continue;
            if (len == 10) cnt++;
            else if (len % 10 == 0) cakes.add(new Cake(len, true));
            else cakes.add(new Cake(len, false));
        }

        Collections.sort(cakes);

        int idx = 0;
        while (M >= 1 && idx < cakes.size()) {
            Cake cake = cakes.get(idx);
            cake.len -= 10;
            cnt++;
            M--;
            if (cake.len == 10) {
                cnt++;
                idx++;
            } else if (cake.len < 10) {
                idx++;
            }
        }

        bw.write(cnt + "\n");


        bw.flush();
        bw.close();
    }

    private static class Cake implements Comparable<Cake> {
        int len;
        boolean easy;

        public Cake(int len, boolean easy) {
            this.len = len;
            this.easy = easy;
        }

        @Override
        public int compareTo(Cake o) {
            if (o.easy == this.easy) return Integer.compare(this.len, o.len);
            if (this.easy) return -1;
            return 1;
        }
    }
}
