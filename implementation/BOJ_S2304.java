package implementation;

import java.io.*;
import java.util.Arrays;

import static java.lang.Integer.*;

public class BOJ_S2304 {
    /*
    만드는 창고의 조건
    1. 수직 부분과 수평 부분은 어떤 기둥의 윗면, 옆면과 닿아야 한다.
    2. 어떤 부분도 오목하게 들어갈 수 없다.
    3. 면적이 최소이다. (높이가 최소, 너비는 입력 값으로 고정됨)

    Key: 좌우 양쪽 끝에서 출발한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        Pair[] inputs = new Pair[N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            inputs[i] = new Pair(parseInt(input[0]), parseInt(input[1]));
        }
        Arrays.sort(inputs);

        int prevHeight = 0;
        int prevPosition = 0;
        int[] areaFromLeft = new int[inputs[N - 1].L + 1];
        for (int i = 0; i < N; i++) {
            for (int prev = prevPosition; prev < inputs[i].L; prev++) areaFromLeft[prev] = prevHeight;
            if (prevHeight < inputs[i].H) {
                prevHeight = inputs[i].H;
            }
            areaFromLeft[inputs[i].L] += prevHeight;
            prevPosition = inputs[i].L + 1;
        }

        prevHeight = 0;
        prevPosition = 0;
        int[] areaFromRight = new int[inputs[N - 1].L + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int prev = inputs[i].L + 1; prev < prevPosition; prev++) areaFromRight[prev] = prevHeight;
            if (prevHeight < inputs[i].H) {
                prevHeight = inputs[i].H;
            }
            areaFromRight[inputs[i].L] += prevHeight;
            prevPosition = inputs[i].L;
        }

        int result = 0;
        for (int i = 0; i < areaFromRight.length; i++) {
            result += min(areaFromLeft[i], areaFromRight[i]);
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static class Pair implements Comparable<Pair> {
        public int L;
        public int H;

        public Pair(int l, int h) {
            L = l;
            H = h;
        }

        @Override
        public int compareTo(Pair another) {
            return Integer.compare(L, another.L);
        }
    }
}
