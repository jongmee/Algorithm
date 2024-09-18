package greedy;

import java.util.Arrays;

class Programmers_Level3_42884 {
    public int solution(int[][] routes) {
        int answer = 0, carCnt = routes.length;
        Section[] sections = new Section[carCnt];
        for (int i = 0; i < carCnt; i++) {
            if (routes[i][0] <= routes[i][1]) sections[i] = new Section(routes[i][0], routes[i][1]);
            else sections[i] = new Section(routes[i][1], routes[i][0]);
        }
        Arrays.sort(sections);

        Section gap = null;
        for (int i = 1; i < carCnt; i++) {
            Section prev = sections[i - 1];
            Section now = sections[i];
            if (gap == null) {
                if (prev.end >= now.start) {
                    int end = Integer.min(prev.end, now.end);
                    gap = new Section(now.start, end);
                } else answer++;
            } else if (isContained(gap, now)) {
                int end = Integer.min(gap.end, now.end);
                gap = new Section(now.start, end);
            } else if (!isContained(gap, now)) {
                gap = null;
                answer++;
            }
        }

        return answer + 1;
    }

    private boolean isContained(Section gap, Section newSection) {
        return gap.end >= newSection.start;
    }

    private class Section implements Comparable<Section> {
        int start, end;

        public Section(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Section o) {
            if (start == o.start) return Integer.compare(end, o.end);
            return Integer.compare(start, o.start);
        }
    }

    public static void main(String[] args) {
        Programmers_Level3_42884 solution = new Programmers_Level3_42884();
        System.out.println(solution.solution(new int[][]{{1, 4}, {1, 3}, {0, 1}, {3, 5}, {3, 4}, {7, 12}}));
        System.out.println(solution.solution(new int[][]{{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}}));
    }
}
