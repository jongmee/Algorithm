package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class BOJ_S1002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            String[] inputs = br.readLine().split(" ");
            int x1 = parseInt(inputs[0]), y1 = parseInt(inputs[1]);
            int r1 = parseInt(inputs[2]);
            int x2 = parseInt(inputs[3]), y2 = parseInt(inputs[4]);
            int r2 = parseInt(inputs[5]);

            double dist = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
            if (hasSameCircle(x1, y1, x2, y2, r1, r2)) bw.write("-1\n");
            else if (includeCircle(r1, r2, dist)) bw.write("0\n");
            else if (includeCircleAndMeetOnePoint(r1, r2, dist)) bw.write("1\n");
            else if (meetOnePoint(r1, r2, dist)) bw.write("1\n");
            else if (meetTwoPoint(r1, r2, dist)) bw.write("2\n");
            else bw.write("0\n");

        }
        bw.flush();
        bw.close();
    }

    private static boolean hasSameCircle(int x1, int y1, int x2, int y2, int r1, int r2) {
        return x1 == x2 && y1 == y2 && r1 == r2;
    }

    private static boolean includeCircle(int r1, int r2, double dist) {
        if (r1 < r2) return dist + r1 < r2;
        return dist + r2 < r1;
    }

    private static boolean includeCircleAndMeetOnePoint(int r1, int r2, double dist) {
        if (r1 < r2) return dist + r1 == r2;
        return dist + r2 == r1;
    }

    private static boolean meetOnePoint(int r1, int r2, double dist) {
        return r1 + r2 == dist;
    }

    private static boolean meetTwoPoint(int r1, int r2, double dist) {
        return r1 + r2 > dist;
    }
}
