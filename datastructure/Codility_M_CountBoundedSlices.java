package datastructure;

import java.util.Objects;
import java.util.TreeSet;

public class Codility_M_CountBoundedSlices {
    private static final int MAX = 1000000000;

    public int slowSolution(int K, int[] A) { // O(N^2) => brute force
        int N = A.length, ans = 0;
        for (int start = 0; start < N; start++) { // (0,0), (0,1), (0,2) ...
            int min = A[start], max = A[start];
            for (int next = start; next < N; next++) {
                min = Integer.min(min, A[next]);
                max = Integer.max(max, A[next]);
                if (max - min <= K) ans++;
                else break;
                if (ans == MAX) return MAX;
            }
        }
        return ans;
    }

    public int fastSolution(int K, int[] A) {
        int N = A.length, ans = 0, next = 0;
        TreeSet<Node> treeSet = new TreeSet<>();
        for (int start = 0; start < N; start++) {
            while (next < N) {
                treeSet.add(new Node(next, A[next]));
                if (treeSet.last().val - treeSet.first().val <= K) next++;
                else break;
            }
            ans += next - start;
            if (ans >= MAX) return MAX;
            int tmp;
            while (!treeSet.isEmpty() && (tmp = treeSet.last().idx) <= start) treeSet.remove(new Node(tmp, A[tmp]));
            while (!treeSet.isEmpty() && (tmp = treeSet.first().idx) <= start) treeSet.remove(new Node(tmp, A[tmp]));
        }
        return ans;
    }

    private static class Node implements Comparable<Node> {
        int idx, val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            if (this.val == o.val) return Integer.compare(this.idx, o.idx);
            return Integer.compare(this.val, o.val);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return idx == node.idx && val == node.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(idx, val);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", val=" + val +
                    '}';
        }
    }

    public static void main(String[] args) {
        Codility_M_CountBoundedSlices solution = new Codility_M_CountBoundedSlices();
        System.out.println(solution.fastSolution(2, new int[]{3, 5, 7, 6, 3}));
    }
}
