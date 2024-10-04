package datastructure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Programmers_Level3_42627 {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(job -> job[0]));
        int idx = 0, answer = 0, jobCnt = jobs.length, totalTime = 0, cnt = 0;
        PriorityQueue<Job> pq = new PriorityQueue<>();
        while (cnt < jobCnt) {
            while (idx < jobCnt && jobs[idx][0] <= totalTime) {
                pq.add(new Job(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            if (pq.isEmpty()) {
                totalTime = (jobs[idx][0]);
            } else {
                Job now = pq.poll();
                totalTime += now.time;
                answer += (totalTime - now.start);
                cnt++;
            }
        }
        return answer / jobCnt;
    }

    private static class Job implements Comparable<Job> {
        int start, time;

        public Job(int start, int time) {
            this.start = start;
            this.time = time;
        }

        public int compareTo(Job o) {
            return Integer.compare(time, o.time);
        }
    }

    public static void main(String[] args) {
        Programmers_Level3_42627 solution = new Programmers_Level3_42627();
        System.out.println(solution.solution(new int[][]{{0, 3}, {1, 9}, {2, 6}}));
    }
}
