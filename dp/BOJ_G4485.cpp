#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

/*
Q: 각 칸에 cost가 주어져 있을 때, 도착지까지 최소 cost를 구하는 문제
1. N의 범위가 N ≤ 125 이기에 DP로 풀었지만, Dijkstra(& pq)로 풀면 훨씬 빠를 것 같다.
*/

#define BORDER 100000
int x[4] = {0, 1, 0, -1};
int y[4] = {-1, 0, 1, 0};

int getMininumCost(vector<vector<int>> cave, int N) {
    vector<vector<int>> minCave(N + 2, vector<int>(N + 2, BORDER));
    minCave[1][1] = cave[1][1];
    for (int replay = 0; replay < N; replay++) {
        for (int i = 1; i <= N; i++) {      // y
            for (int j = 1; j <= N; j++) {  // x
                for (int dir = 0; dir < 4; dir++) {
                    if (minCave[i + y[dir]][j + x[dir]] > minCave[i][j] + cave[i + y[dir]][j + x[dir]]) {
                        minCave[i + y[dir]][j + x[dir]] = minCave[i][j] + cave[i + y[dir]][j + x[dir]];
                    }
                }
            }
        }
    }
    return minCave[N][N];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, testcase = 1;
    while (true) {
        cin >> N;
        if (N == 0) break;
        vector<vector<int>> cave(N + 2, vector<int>(N + 2, BORDER));
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                cin >> cave[i][j];
            }
        }
        cout << "Problem " << testcase << ": " << getMininumCost(cave, N) << "\n";
        testcase++;
    }
    return 0;
}