#include <algorithm>
#include <iostream>
#include <unordered_set>
#include <vector>

using namespace std;
typedef long long ll;

/*
Q: 집합 U에서 세 수의 합이 제일 커지는 경우(합도 집합에 있어야 함)
1) a+b+c=d라고 할 때 a,b,c,d가 서로 같아도 된다
2) 집합 자체는 중복되는 수가 없다
3) 5 ≤ 집합의크기 ≤ 1,000
*/

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    cin >> N;

    int tmp;
    vector<int> input;
    for (int i = 0; i < N; i++) {
        cin >> tmp;
        input.push_back(tmp);
    }

    sort(input.begin(), input.end());

    unordered_set<ll> sum;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            sum.insert(input[i] + input[j]);
        }
    }

    for (int i = N - 1; i >= 0; i--) {
        for (int j = 0; j < N; j++) {
            auto found = sum.find(input[i] - input[j]);
            if (found != sum.end()) {
                cout << input[i] << "\n";
                return 0;
            }
        }
    }

    return 0;
}