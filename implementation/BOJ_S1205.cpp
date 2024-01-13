#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

/*
Q: 주어진 점수 랭킹에서, 입력 점수의 순위 결정하기
1) 랭킹에 올라갈 수 있는 점수 개수 제한이 있다
2) (같은 점수 있을 때)100, 90, 90, 80일 때 1, 2, 2, 4등
3) 점수가 랭킹 리스트에 올라갈 수 없을 정도로 낮다면 -1 출력
*/

ll scores[55];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, P;
    ll newScore;
    fill(scores, scores+55, -1);
    cin >> N >> newScore >> P;

    if (N == 0) {
        cout << 1 << "\n";
        return 0;
    }

    for (int i = 0; i < N; i++) {
        cin >> scores[i];
    }

    int position = 0;
    for (int i = P - 1; i >= 0; i--) {
        if (scores[i] > newScore) { 
            position = i+1;
            break;
        }
    }

    int start = position;
    while(scores[position] == newScore) {
        position++;
    }
    
    if(position+1 > P) cout << -1 << "\n";
    else cout << start+1 << "\n";

    return 0;
}