#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

/*
Q: 줄세우기
1. 한 명씩 줄세운다.
2. 본인 앞에 본인보다 큰 학생이 있다면 그 중 가장 앞 학생 앞에 선다.
3. 그 뒤 모든 학생들은 뒤로 물러선다.
4. 뒤로 물러선 횟수를 구한다. 같은 키는 없다.
*/

#define STUDENT 20

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int P, testcase;
    cin >> P;
    while (P--) {
        cin >> testcase;
        int cnt = 0;
        vector<int> order(STUDENT);
        for (int s = 0; s < STUDENT; s++) {
            cin >> order[s];
            for (int i = 0; i < s; i++) {
                if (order[i] > order[s]) {
                    int tmp = order[s];
                    for (int j = s; j >= i + 1; j--) {
                        order[j] = order[j - 1];
                        cnt++;
                    }
                    order[i] = tmp;
                    break;
                }
            }
        }
        cout << testcase << " " << cnt << "\n";
    }

    return 0;
}