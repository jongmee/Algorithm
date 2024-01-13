#include <iostream>

using namespace std;
typedef long long ll;

/*
Q: 벌집에 순번을 매길 때, 중앙 1에서 N까지 몇 개의 벌집을 지나가는지
1) 한 겹에 들어가는 벌집 수는 6씩 증가한다. ex. 6, 12, 18 ...
*/

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    cin >> N;

    int offset = 0;
    for (int i = 1; i <= N * 6; i += offset * 6) {
        if (N <= i) {
            cout << offset + 1 << "\n";
            return 0;
        }
        offset++;
    }

    return 0;
}