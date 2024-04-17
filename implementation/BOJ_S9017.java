package implementation;

import java.io.*;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BOJ_S9017 {
    /*
    1. 팀 점수는 상위 네 명의 주자의 점수를 합하여 계산, 이 점수를 더하여 가장 낮은 점수를 얻는 팀이 우승
    2. 한 팀은 여섯 명의 선수로 구성, 여섯 명의 주자가 참가하지 못한 팀은 점수 계산에서 제외
    3. 동점의 경우에는 다섯 번째 주자가 가장 빨리 들어온 팀이 우승
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            int N = parseInt(br.readLine());
            String[] inputs = br.readLine().split(" "); // 등수대로 팀 번호
            int[] teamNumbers = new int[N];
            int[] memberCnt = new int[N];

            int maxTeamNumber = -1;
            for (int i = 0; i < N; i++) {
                teamNumbers[i] = parseInt(inputs[i]);
                memberCnt[teamNumbers[i]]++;
                if (maxTeamNumber < teamNumbers[i]) maxTeamNumber = teamNumbers[i];
            }

            Team[] teams = new Team[maxTeamNumber];
            for (int i = 0; i < maxTeamNumber; i++) teams[i] = new Team(i + 1);

            int rank = 1;
            for (int teamNumber : teamNumbers) {
                if (memberCnt[teamNumber] < 6) continue;
                if (teams[teamNumber - 1].cnt < 4) teams[teamNumber - 1].totalScore += rank;
                teams[teamNumber - 1].cnt += 1;
                if (teams[teamNumber - 1].cnt == 5) teams[teamNumber - 1].fifthMemberRank = rank;
                rank++;
            }

            Arrays.sort(teams);

            Team winner = null;
            int start_idx = 0;
            for (Team team : teams) {
                start_idx++;
                if (memberCnt[team.number] < 6) continue;
                winner = team;
                break;
            }

            for (int i = start_idx; i < teams.length; i++) {
                if (memberCnt[teams[i].number] < 6) continue;
                if (teams[i].totalScore != winner.totalScore) break;
                if (teams[i].fifthMemberRank < winner.fifthMemberRank) winner = teams[i];
            }

            bw.write(winner.number + "\n");
        }

        bw.flush();
        bw.close();
    }

    static class Team implements Comparable<Team> {
        int number, totalScore, fifthMemberRank, cnt;

        public Team(int number) {
            this.number = number;
        }

        @Override
        public int compareTo(Team o) {
            return Integer.compare(totalScore, o.totalScore);
        }
    }
}
