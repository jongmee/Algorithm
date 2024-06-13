import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class BOJ_G1174 {
    private static List<Long> numbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long N = Long.parseLong(br.readLine());
        long cnt = 0, ans = -1;
        for (int depth = 1; depth < 11; depth++) {
            dfs(depth, 0, 10, 0);
        }

        numbers.sort(Long::compare);

        for (Long number : numbers) {
            cnt++;
            if (cnt == N) {
                ans = number;
                break;
            }
        }

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
    }

    private static void dfs(int limit, int depth, int start, long sum) {
        if (limit == depth) {
            numbers.add(sum);
            return;
        }
        for (int i = start - 1; i >= 0; i--) {
            dfs(limit, depth + 1, i, sum * 10 + i);
        }
    }
}
