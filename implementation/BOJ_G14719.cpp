#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

/*
Q: 블록 높이들이 주어질 때, 고이는 빗물의 양
1) Point: 밑에서부터 한 줄씩 확인한다.
*/

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int H, W;
    cin >> H >> W;

    vector<vector<bool>> blocks(H, vector<bool>(W, false));

    int input;
    for (int w = 0; w < W; w++) {
        cin >> input;
        for (int i = 0; i < input; i++) blocks[i][w] = true;
    }

    bool flag = false;
    int cnt, total = 0;
    for (int h = 0; h < H; h++) {
        cnt = 0;
        flag = false;
        for (int w = 0; w < W; w++) {
            if (flag) {
                if (blocks[h][w]) {
                    total += cnt;
                    cnt = 0;
                } else {
                    cnt++;
                }
            } else if (blocks[h][w]) {
                flag = true;
            }
        }
    }

    cout << total << "\n";

    return 0;
}