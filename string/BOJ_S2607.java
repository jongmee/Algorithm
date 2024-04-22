package string;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class BOJ_S2607 {
    /*
     비슷한 단어는
     1. 두 단어가 같은 구성을 갖는 경우
     2. 한 단어에서 한 문자를 더하거나, 빼거나, 하나의 문자를 다른 문자로 바꾸어 나머지 한 단어와 같은 구성을 갖게 되는 경우
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());
        String[] words = new String[N];
        for (int i = 0; i < N; i++) words[i] = br.readLine();

        var firstCharacters = wordCnts(words[0]);

        int result = 0;
        for (int i = 1; i < words.length; i++) {
            var tmpFirstCharacters = new HashMap<>(firstCharacters);
            var nextCharacters = wordCnts(words[i]);
            var finalNextCharacters = wordCnts(words[i]);
            for (var entry : nextCharacters.entrySet()) {
                char character = entry.getKey();
                int nextCount = entry.getValue();
                int firstCount = tmpFirstCharacters.getOrDefault(character, 0);
                if (nextCount <= firstCount && firstCount != 0) {
                    tmpFirstCharacters.remove(character);
                    tmpFirstCharacters.put(character, firstCount - nextCount);
                    finalNextCharacters.remove(character);
                    finalNextCharacters.put(character, 0);
                } else if (nextCount > firstCount) {
                    finalNextCharacters.remove(character);
                    finalNextCharacters.put(character, nextCount - firstCount);
                    tmpFirstCharacters.remove(character);
                    tmpFirstCharacters.put(character, 0);
                }
            }

            int sumFirst = 0;
            for (var value : tmpFirstCharacters.values()) sumFirst += value;
            int sumNext = 0;
            for (var value : finalNextCharacters.values()) sumNext += value;
            if (sumFirst + sumNext <= 1 || (sumFirst == 1 && sumNext == 1)) result++;
        }

        bw.write(result + "\n");

        bw.flush();
        bw.close();
    }

    static Map<Character, Integer> wordCnts(String word) {
        Map<Character, Integer> wordCnts = new HashMap<>();
        for (char c : word.toCharArray()) {
            Integer cnt = wordCnts.get(c);
            if (cnt == null) wordCnts.put(c, 1);
            else {
                wordCnts.remove(c);
                wordCnts.put(c, cnt + 1);
            }
        }
        return wordCnts;
    }
}
