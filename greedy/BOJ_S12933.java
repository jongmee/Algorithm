package greedy;

import java.io.*;

public class BOJ_S12933 {
    static char[] letters = {'q', 'u', 'a', 'c', 'k'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String sound = br.readLine();

        int[] ducks = new int[sound.length()];
        int cnt = 0;
        for (char s : sound.toCharArray()) {
            int idx = findIdx(s);
            boolean found = false;
            for (int i = 0; i <= cnt; i++) {
                if (ducks[i] % letters.length == idx) {
                    ducks[i]++;
                    if (i == cnt) cnt++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                cnt = 0;
                break;
            }
        }

        for (int i = cnt - 1; i >= 0; i--) {
            if (ducks[i] % letters.length != 0) {
                cnt = 0;
                break;
            }
        }

        if (cnt == 0) cnt = -1;
        bw.write(cnt + "\n");

        bw.flush();
        bw.close();
    }

    static int findIdx(char c) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == c) return i;
        }
        return -1;
    }
}
