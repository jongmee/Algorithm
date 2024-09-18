package implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Programmers_Level3_17678 {
    private Time[] readyQueue;
    private Time[] busTimeTable;
    private List<Time>[] takeBusCase;

    public String solution(int n, int t, int m, String[] timetable) { // 9:00부터 n회 t분 간격, 최대 m명
        readyQueue = new Time[timetable.length];
        for (int i = 0; i < timetable.length; i++) readyQueue[i] = parseTime(timetable[i]);
        Arrays.sort(readyQueue);

        busTimeTable = new Time[n];
        busTimeTable[0] = new Time(9, 0);
        for (int i = 1; i < n; i++) busTimeTable[i] = busTimeTable[i - 1].plus(t);

        takeBusCase = new List[n];
        for (int i = 0; i < n; i++) takeBusCase[i] = new ArrayList<>();

        int queueIdx = 0;
        for (int busIdx = 0; busIdx < n; busIdx++) {
            while (queueIdx < timetable.length && busTimeTable[busIdx].canTake(readyQueue[queueIdx]) && takeBusCase[busIdx].size() < m) {
                takeBusCase[busIdx].add(readyQueue[queueIdx]);
                queueIdx++;
            }
        }

        // 마지막 버스를 마지막에 탄다
        Time answer = null;
        if (takeBusCase[n - 1].size() < m) answer = busTimeTable[n - 1];
        else {
            Time lastTime = takeBusCase[n - 1].get(m - 1);
            answer = lastTime.minusOneMin();
        }

        return toAnserString(answer);
    }

    private String toAnserString(Time answer) {
        String re = "";
        if (answer.h < 10) re += "0" + answer.h;
        else re += answer.h;
        re += ":";
        if (answer.m < 10) re += "0" + answer.m;
        else re += answer.m;
        return re;
    }

    private Time parseTime(String timeStr) {
        String[] parts = timeStr.split(":");
        return new Time(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    private class Time implements Comparable<Time> {
        int h, m;

        public Time(int h, int m) {
            this.h = h;
            this.m = m;
        }

        public Time plus(int m) {
            int newMin = this.m + m;
            int quotient = newMin / 60;
            int rest = newMin % 60;
            return new Time(this.h + quotient, rest);
        }

        public Time minusOneMin() {
            int newMin = this.m - 1;
            if (newMin >= 0) return new Time(this.h, newMin);
            return new Time(this.h - 1, 60 + newMin);
        }

        public boolean canTake(Time other) { // 버스 시간을 기준으로 param 을 태울 수 있는지
            return this.equals(other) || this.isAfter(other);
        }

        public boolean equals(Time other) {
            return h == other.h && m == other.m;
        }

        public boolean isAfter(Time other) {
            return h > other.h || h == other.h && m > other.m;
        }

        @Override
        public int compareTo(Time o) {
            if (o.h == h) return Integer.compare(m, o.m);
            return Integer.compare(h, o.h);
        }

        @Override
        public String toString() {
            return "Time {h = " + h + ", m = " + m + "}";
        }
    }

    public static void main(String[] args) {
        Programmers_Level3_17678 solution = new Programmers_Level3_17678();
        System.out.println(solution.solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        System.out.println(solution.solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
        System.out.println(solution.solution(2, 1, 2, new String[]{"09:00", "09:00", "09:00", "09:00"}));
        System.out.println(solution.solution(1, 1, 5, new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"}));
        System.out.println(solution.solution(1, 1, 1, new String[]{"23:59"}));
        System.out.println(solution.solution(10, 60, 45, new String[]{"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
    }
}
