#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

/*
Q: 금, 은, 동메달 개수로 국가들의 순위 정하기
1) 메달 수가 같다면 등수가 같다. 1등, 공동 2등, 4등 ..
*/

struct Result {
    int num, gold, silver, bronze, score;
};

Result* newResult(int num, int gold, int silver, int bronze) {
    Result* r = new Result;
    r->num = num;
    r->gold = gold;
    r->silver = silver;
    r->bronze = bronze;
    r->score = gold * 5 + silver * 3 + bronze * 1;
    return r;
}

bool cmp(Result* result1, Result* result2) {
    if (result1->gold > result2->gold)
        return true;
    else if (result1->gold == result2->gold && result1->silver > result2->silver)
        return true;
    else if (result1->gold == result2->gold && result1->silver == result2->silver && result1->bronze > result2->bronze)
        return true;
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, K;
    cin >> N >> K;

    vector<Result*> nations(N);
    int num, gold, silver, bronze;
    for (int i = 0; i < N; i++) {
        cin >> num >> gold >> silver >> bronze;
        nations[i] = newResult(num, gold, silver, bronze);
    }

    sort(nations.begin(), nations.end(), cmp);

    int rank = 0;
    int prevScore = -1;
    int sameRankCount = 1;
    for (Result* result : nations) {
        if (prevScore != result->score) {
            if (sameRankCount == 1)
                rank++;
            else
                rank += sameRankCount;
            sameRankCount = 1;
        } else
            sameRankCount++;
        if (result->num == K) {
            cout << rank << "\n";
            break;
        }
        prevScore = result->score;
    }

    return 0;
}