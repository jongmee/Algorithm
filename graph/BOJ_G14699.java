package graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BOJ_G14699 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = parseInt(inputs[0]), M = parseInt(inputs[1]);

        Map<Integer, List<Shelter>> map = new HashMap<>();
        Map<Integer, List<Shelter>> reverse = new HashMap<>();
        Map<Integer, Shelter> shelters = new HashMap<>();

        inputs = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int height = parseInt(inputs[i]);
            Shelter shelter = new Shelter(i + 1, height);
            map.put(i + 1, new ArrayList<>());
            reverse.put(i + 1, new ArrayList<>());
            shelters.put(i + 1, shelter);
        }

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int first = parseInt(inputs[0]), second = parseInt(inputs[1]);
            Shelter firstShelter = shelters.get(first), secondShelter = shelters.get(second);
            if (firstShelter.height < secondShelter.height) {
                map.get(second).add(firstShelter);
                reverse.get(first).add(secondShelter);
            } else {
                map.get(first).add(secondShelter);
                reverse.get(second).add(firstShelter);
            }
        }

        List<Shelter> starts = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            if (reverse.get(i + 1).isEmpty())
                starts.add(shelters.get(i + 1));
        }

        for (Shelter start : starts) {
            bfs(N, map, start);
        }

        for (int num = 1; num <= N; num++) {
            bw.write((shelters.get(num).cnt + 1) + "\n");
        }

        bw.flush();
        bw.close();
    }

    static void bfs(int N, Map<Integer, List<Shelter>> map, Shelter start) {
        Queue<Shelter> q = new PriorityQueue<>();
        q.add(start);
        while (!q.isEmpty()) {
            Shelter front = q.poll();
            for (Shelter next : map.get(front.num)) {
                if (next.cnt < front.cnt + 1) {
                    next.cnt = front.cnt + 1;
                    q.add(next);
                }
            }
        }
    }

    static class Shelter implements Comparable<Shelter> {
        int num, height, cnt;

        public Shelter(int num, int height) {
            this.num = num;
            this.height = height;
        }

        @Override
        public int compareTo(Shelter o) {
            return Integer.compare(o.cnt, cnt);
        }
    }
}

