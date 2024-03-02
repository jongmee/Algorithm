package linear;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BOJ_S1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();
        int M = parseInt(br.readLine());

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        for (char c : word.toCharArray()) left.push(c);

        Character top;
        while (M > 0) {
            String command = br.readLine();
            if (command.charAt(0) == 'L' && !left.empty()) {
                top = left.pop();
                right.push(top);
            } else if (command.charAt(0) == 'D' && !right.empty()) {
                top = right.pop();
                left.push(top);
            } else if (command.charAt(0) == 'B' && !left.empty()) {
                left.pop();
            } else if (command.charAt(0) == 'P') {
                char newChar = command.charAt(2);
                left.push(newChar);
            }
            M--;
        }

        StringBuilder editedWord = new StringBuilder();
        for (Character c : left) editedWord.append(c);
        while (!right.empty()) editedWord.append(right.pop());
        bw.write(editedWord.toString());

        bw.flush();
        bw.close();
    }
}
