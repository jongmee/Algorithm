package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Programmers_Level3_43164 {
    private Map<String, List<Ticket>> routes;
    private List<String> answer;
    private int totalTicketsCnt;

    public List<String> solution(String[][] tickets) {
        answer = new ArrayList<>();
        routes = new HashMap<>();
        totalTicketsCnt = tickets.length;
        
        for (int i = 0; i < totalTicketsCnt; i++) {
            String[] ticket = tickets[i];
            String from = ticket[0], to = ticket[1];
            if (routes.containsKey(from)) {
                routes.get(from).add(new Ticket(i, to));
            } else {
                routes.put(from, new ArrayList<>(List.of(new Ticket(i, to))));
            }
        }
        for (List<Ticket> route : routes.values()) Collections.sort(route);

        int[] visited = new int[totalTicketsCnt];
        dfs(new Ticket(-1, "ICN"), visited, new ArrayList<>(List.of("ICN")), 0);

        return answer;
    }

    private void dfs(Ticket start, int[] visited, List<String> nowRoute, int cnt) {
        if (!answer.isEmpty()) return;
        if (cnt == totalTicketsCnt) {
            answer = new ArrayList<>(nowRoute);
            return;
        }
        List<Ticket> nexts = routes.get(start.to);
        if (nexts == null) return;
        for (Ticket next : nexts) {
            if (visited[next.idx] == 0) {
                visited[next.idx] = 1;
                nowRoute.add(next.to);
                dfs(next, visited, nowRoute, cnt + 1);
                visited[next.idx] = 0;
                nowRoute.remove(nowRoute.size() - 1);
            }
        }
    }

    private static class Ticket implements Comparable<Ticket> {
        int idx;
        String to;

        public Ticket(int idx, String to) {
            this.idx = idx;
            this.to = to;
        }

        @Override
        public int compareTo(Ticket o) {
            return this.to.compareTo(o.to);
        }
    }

    public static void main(String[] args) {
        Programmers_Level3_43164 solution = new Programmers_Level3_43164();
        System.out.println(solution.solution(new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}}));
        System.out.println(solution.solution(new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}}));
    }
}
