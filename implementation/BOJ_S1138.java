package implementation;

import java.io.*;
import java.util.LinkedList;

public class BOJ_S1138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N;
        N = Integer.parseInt(br.readLine());

        String[] inputs = br.readLine().split(" ");
        int[] cnts = new int[N];
        for (int i = 0; i < N; i++) {
            cnts[i] = Integer.parseInt(inputs[i]);
        }

        LinkedList<Integer> ans = new LinkedList<>();
        ans.add(N);
        for (int i = N - 2; i >= 0; i--) {
            int goalCnt = cnts[i];
            int goalHeight = i + 1;
            for (int j = 0; j < ans.size(); j++) {
                int preHeight = ans.get(j);
                if (goalCnt <= 0) {
                    ans.add(j, goalHeight);
                    break;
                } else if (j == ans.size() - 1) {
                    ans.add(goalHeight);
                    break;
                }
                if (preHeight > goalHeight) goalCnt--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Integer i : ans) {
            sb.append(i);
            sb.append(" ");
        }
        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }
}
