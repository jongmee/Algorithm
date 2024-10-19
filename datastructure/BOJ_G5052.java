package datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class BOJ_G5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            int n = parseInt(br.readLine());
            String[] numbers = new String[n];
            for (int i = 0; i < n; i++) numbers[i] = br.readLine();
            Arrays.sort(numbers);
            Node root = new Node(' ');
            FOUND = false;
            for (int i = 0; i < n; i++) {
                addNode(numbers[i].toCharArray(), 0, root);
                if (FOUND) break;
            }
            if (FOUND) bw.write("NO\n");
            else bw.write("YES\n");
        }
        bw.flush();
        bw.close();
    }

    private static boolean FOUND = false;

    private static void addNode(char[] vals, int idx, Node parent) {
        if (FOUND) return;
        if (idx >= vals.length) return;
        char val = vals[idx];
        if (!parent.children.containsKey(val)) {
            Node newChild = new Node(val);
            if (idx == vals.length - 1) newChild.isLast = true;
            parent.addChild(newChild);
            addNode(vals, idx + 1, newChild);
        } else {
            Node child = parent.children.get(val);
            if (child.isLast) {
                FOUND = true;
            } else addNode(vals, idx + 1, child);
        }
    }

    private static class Node {
        char val;
        boolean isLast = false;
        Map<Character, Node> children = new HashMap<>();

        public Node(char val) {
            this.val = val;
        }

        public void addChild(Node child) {
            this.children.put(child.val, child);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }
}
