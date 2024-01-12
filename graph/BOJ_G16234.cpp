#include <algorithm>
#include <iostream>
#include <queue>
#include <utility>
#include <vector>

using namespace std;
typedef long long ll;

/*
Q: 나라 간 인구차가 조건에 맞을 때까지 분배되는데, 몇 회 분배되는지
1) 인접한 두 나라 차가 L명 이상 R명 이하면 국격을 연다.
2) 이동 가능한 나라들(연합)이 (연합의 인구수)/(연합을 이루고 있는 칸의 개수)로 인구가 조정된다.
3) 가능할 때까지 조정한다.
*/

#define MAX 54
#define BORDER 100000

struct Nation {
    int x, y, polulation;
    vector<Nation*> others;
};
Nation* area[MAX][MAX];  // y, x

int x[4] = {0, 1, 0, -1};  // 상 우 하 좌
int y[4] = {-1, 0, 1, 0};

Nation* makeNation(int x, int y, int polulation) {
    Nation* n = new Nation;
    n->x = x;
    n->y = y;
    n->polulation = polulation;
    return n;
}

int getDifference(int X, int Y, int dir) {
    int diff = area[Y][X]->polulation - area[Y + y[dir]][X + x[dir]]->polulation;
    if (diff >= 0) return diff;
    return diff * (-1);
}

int openBorder(int N, int L, int R) {
    int flag = 0;
    for (int i = 1; i <= N; i++) {      // y
        for (int j = 1; j <= N; j++) {  // x
            for (int dir = 0; dir <= 3; dir++) {
                int diff = getDifference(j, i, dir);
                if (diff >= L && diff <= R) {
                    flag = 1;
                    (area[i][j]->others).push_back(area[i + y[dir]][j + x[dir]]);
                }
            }
        }
    }
    return flag;
}

vector<Nation*> group;
int bfs(Nation* start, int visited[MAX][MAX]) {
    if (visited[start->y][start->x] == 1) return 0;
    queue<Nation*> q;
    q.push(start);
    visited[start->y][start->x] = 1;

    int ret = 0;
    while (!q.empty()) {
        Nation* target = q.front();
        ret += target->polulation;
        group.push_back(target);
        q.pop();

        for (Nation* n : target->others) {
            if (n->y != BORDER && n->x != BORDER && visited[n->y][n->x] == 0) {
                visited[n->y][n->x] = 1;
                q.push(n);
            }
        }
    }
    return ret;
}

void setPolulation(int N, int visited[MAX][MAX]) {
    for (int i = 1; i <= N; i++) {      // y
        for (int j = 1; j <= N; j++) {  // x
            group.clear();
            int sum = bfs(area[i][j], visited);
            if (sum != 0) {
                int resultPopulation = sum / group.size();
                for (Nation* n : group) {
                    n->polulation = resultPopulation;
                }
            }
        }
    }
}

void closeBorder(int N) {
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            area[i][j]->others.clear();
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, L, R;
    cin >> N >> L >> R;

    for (int i = 0; i < MAX; i++) fill(area[i], area[i] + MAX, makeNation(BORDER, BORDER, BORDER));

    int tmp;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> tmp;
            area[i][j] = makeNation(j, i, tmp);
        }
    }

    int flag = 1;
    int cnt = 0;
    while (true) {
        flag = openBorder(N, L, R);
        if (flag == 0) break;
        cnt++;
        int visited[MAX][MAX] = {
            0,
        };
        setPolulation(N, visited);
        closeBorder(N);
    }

    cout << cnt << "\n";

    return 0;
}