package adhoc;

import java.io.*;

import static java.lang.Integer.parseInt;

public class BOJ_G25181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int[] sequence = new int[N];
        int[] orginalSeq = new int[N];
        String[] inputs = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            sequence[i] = parseInt(inputs[i]);
            orginalSeq[i] = sequence[i];
        }

        for (int i = 0; i < N; i++) {
            int original = sequence[i];
            boolean found = false;
            for (int other = 0; other < N; other++) {
                if (other != i && sequence[other] != original && orginalSeq[i] != sequence[other] && orginalSeq[other] != sequence[i]) {
                    sequence[i] = sequence[other];
                    sequence[other] = original;
                    found = true;
                    break;
                }
            }
            if (!found && orginalSeq[i] == sequence[i]) {
                bw.write(-1 + "\n");
                bw.flush();
                bw.close();
                return;
            }
        }

        for (int s : sequence) bw.write(s + " ");

        bw.flush();
        bw.close();
    }
}
