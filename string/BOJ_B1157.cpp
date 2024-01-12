#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

/*
Q: 가장 많이 사용된 알파벳 구하기
1) 대소문자 구분 없음
2) 여러개면 ? 출력
3) 단어길이: 1,000,000
*/

int alphabets[30] = {
    0,
};

int findMaxAlphabet() {
    int tmpAlphabets[30];
    copy(alphabets, alphabets+30, tmpAlphabets);
    sort(tmpAlphabets, tmpAlphabets+30, greater<int>());
    if(tmpAlphabets[0] == tmpAlphabets[1]) return -1;
    int* index = find(alphabets, alphabets+30, tmpAlphabets[0]);
    return distance(alphabets, index) + 'A';
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string input;
    cin >> input;

    int diff = 'a'-'A';
    int offset = 'A';
    for(char c: input) {
        if(c >= 'a') {
            alphabets[c-diff-offset]++;
        } else {
            alphabets[c-offset]++;
        }
    }

    int result = findMaxAlphabet();
    if(result == -1) cout << "?\n";
    else cout << (char)result << "\n";
    
    return 0;
}