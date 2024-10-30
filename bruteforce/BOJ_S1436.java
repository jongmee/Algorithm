package bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S1436 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        int cnt = 1, ans = 666;
        while (cnt < N) {
            ans++;
            if (contains666(ans)) cnt++;
        }

        System.out.println(ans);

        bw.flush();
        bw.close();
    }

    static boolean contains666(int num) {
        String str = String.valueOf(num);

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '6') {
                int cnt = 1;
                i++;
                for (; i < str.length(); i++) {
                    if (str.charAt(i) != '6') break;
                    cnt++;
                }
                if (cnt >= 3) return true;
            }
        }
        return false;
    }
}
