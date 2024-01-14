#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

/*
Q: 지름길을 조합해 최단거리 찾기
*/

struct Path {
    int start, dest, cost;
};

Path* newPath(int start, int dest, int cost) {
    Path* p = new Path;
    p->start = start;
    p->dest = dest;
    p->cost = cost;
    return p;
}

bool cmp(Path* p1, Path* p2) {
    if (p1->dest < p2->dest) return true;
    return false;
}

void dp(vector<Path*> paths, int D) {
    vector<int> dp(D + 1);
    for (int i = 0; i <= D; i++) {
        dp[i] = i;
    }

    for (Path* path : paths) {
        if (dp[path->start] + path->cost < dp[path->dest]) {
            int update = dp[path->start] + path->cost;
            int offset = 0;
            for (int i = path->dest; i <= D; i++) {
                dp[i] = update + offset;
                offset++;
            }
        }
    }

    cout << dp[D] << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, D;
    cin >> N >> D;

    int start, dest, cost;
    vector<Path*> input(N);
    for (int i = 0; i < N; i++) {
        cin >> start >> dest >> cost;
        input[i] = newPath(start, dest, cost);
    }
    sort(input.begin(), input.end(), cmp);

    dp(input, D);

    return 0;
}