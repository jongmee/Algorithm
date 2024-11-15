package datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

import static java.lang.Integer.parseInt;

public class BOJ_G5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = parseInt(br.readLine());
        while (T-- > 0) {
            String operations = br.readLine();
            int n = parseInt(br.readLine());
            String input = br.readLine();
            input = input.replace("[", "");
            input = input.replace("]", "");
            String[] numStrs = input.split(",");

            Deque<Integer> nums = new ArrayDeque<>();
            for (int i = 0; i < n; i++) nums.addLast(parseInt(numStrs[i]));
            
            boolean dir = true, isError = false;
            for (char operation : operations.toCharArray()) {
                if (operation == 'R') dir = !dir;
                else if (nums.isEmpty()) {
                    isError = true;
                    break;
                } else {
                    if (dir) nums.pollFirst();
                    else nums.pollLast();
                }
            }

            if (isError) bw.write("error\n");
            else if (nums.isEmpty()) bw.write("[]\n");
            else if (dir) {
                bw.write("[" + nums.pollFirst());
                while (!nums.isEmpty()) bw.write("," + nums.pollFirst());
                bw.write("]\n");
            } else {
                bw.write("[" + nums.pollLast());
                while (!nums.isEmpty()) bw.write("," + nums.pollLast());
                bw.write("]\n");
            }
        }
        
        bw.flush();
        bw.close();
    }
}
