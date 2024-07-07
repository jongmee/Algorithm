package adhoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_G10775 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int G = parseInt(br.readLine());
        int P = parseInt(br.readLine());

        int[] planes = new int[P + 1];
        for (int p = 1; p <= P; p++) planes[p] = parseInt(br.readLine());

        int[] gates = new int[G + 1];
        int cnt = 0;
        for (int p = 1; p <= P; p++) {
            int gi = planes[p];
            int nextIdx = find(gates, gi);
            if (nextIdx == -1) break;
            cnt++;
        }

        bw.write(cnt + "\n");

        bw.flush();
        bw.close();
    }

    static int find(int[] gates, int idx) {
        if (idx == 0 || gates[idx] == -1) return -1;
        if (gates[idx] == 0) {
            gates[idx] = idx - 1;
            if (gates[idx] == 0) gates[idx]--;
            return idx;
        }
        return gates[idx] = find(gates, gates[idx]);
    }
}
