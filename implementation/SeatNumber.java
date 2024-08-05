package implementation;

import java.util.Arrays;

class SeatNumber {
    /*
    개수를 다 세는 것이 아니라, cnt를 통해 가장 겉 줄(바깥 껍질)의 좌석 수를 세서 k가 그 줄에 들어갈 수 있는지 확인한 후,
    좌석을 하나씩 본다. 이보다 더 최적화하고 싶다면 각 껍질의 좌석 수를 누적합하여 이진 탐색할 수 있다.
     */
    public int[] solution(final int c, final int r, int k) {
        if (c * r < k) return new int[]{0, 0};
        int startPoint = 1, cPoint = c, rPoint = r;
        while (true) {
            int cnt = c * 2 + (r - 2) * 2;
            if (k <= cnt) {
                final int lastC = c - startPoint + 1, lastR = r - startPoint + 1;
                int tmpCnt = 0;
                for (int pointR = startPoint; pointR <= lastR; pointR++) {
                    tmpCnt++;
                    if (tmpCnt == k) return new int[]{startPoint, pointR};
                }
                for (int pointC = startPoint + 1; pointC <= lastC; pointC++) {
                    tmpCnt++;
                    if (tmpCnt == k) return new int[]{pointC, lastR};
                }
                for (int pointR = lastR - 1; pointR >= startPoint; pointR--) {
                    tmpCnt++;
                    if (tmpCnt == k) return new int[]{lastC, pointR};
                }
                for (int pointC = lastC - 1; pointC >= startPoint + 1; pointC--) {
                    tmpCnt--;
                    if (tmpCnt == k) return new int[]{pointC, startPoint};
                }
            }
            startPoint++;
            cPoint -= 2;
            rPoint -= 2;
            k -= cnt;
            if (cPoint < 0 || rPoint < 0) return new int[]{-1, -1};
        }
    }

    public static void main(String[] args) {
        SeatNumber T = new SeatNumber();
        System.out.println(Arrays.toString(T.solution(6, 5, 12)));
        System.out.println(Arrays.toString(T.solution(6, 5, 20)));
        System.out.println(Arrays.toString(T.solution(6, 5, 30)));
        System.out.println(Arrays.toString(T.solution(6, 5, 31)));
    }
}
