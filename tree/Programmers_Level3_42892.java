package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Programmers_Level3_42892 {
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        int len = nodeinfo.length;
        Node[] nodes = new Node[len];
        for (int i = 0; i < len; i++) nodes[i] = new Node(i, nodeinfo[i][0], nodeinfo[i][1]);
        Arrays.sort(nodes);

        List<List<Node>> nodesByLevel = new ArrayList<>();
        int level = 0, height = nodes[0].y;
        for (int i = 0; i < len; i++) {
            if (nodes[i].y < height) {
                height = nodes[i].y;
                level++;
            }
            if (nodesByLevel.size() <= level) nodesByLevel.add(new ArrayList<>());
            nodesByLevel.get(level).add(nodes[i]);
        }

        if (nodesByLevel.size() == 1) return new int[][]{{1}, {1}};
        for (Node node : nodesByLevel.get(1)) {
            node.parent = nodes[0];
            if (node.x < nodes[0].x) nodes[0].left = node;
            else nodes[0].right = node;
        }

        for (int lev = 2; lev <= level; lev++) {
            for (Node node : nodesByLevel.get(lev)) {
                findParent(nodesByLevel.get(lev - 1), node);
            }
        }

        preOrderList = new int[len];
        postOrderList = new int[len];
        preIdx = 0;
        postIdx = 0;
        preOrder(nodes[0]);
        postOrder(nodes[0]);

        return new int[][]{preOrderList, postOrderList};
    }

    private int[] preOrderList, postOrderList;
    private int preIdx, postIdx;

    private void preOrder(Node node) {
        preOrderList[preIdx++] = node.val + 1;
        if (node.left != null) preOrder(node.left);
        if (node.right != null) preOrder(node.right);
    }

    private void postOrder(Node node) {
        if (node.left != null) postOrder(node.left);
        if (node.right != null) postOrder(node.right);
        postOrderList[postIdx++] = node.val + 1;
    }

    private void findParent(List<Node> allUpLevelNodes, Node node) {
        int size = allUpLevelNodes.size();
        if (allUpLevelNodes.get(0).x > node.x) {
            node.parent = allUpLevelNodes.get(0);
            allUpLevelNodes.get(0).left = node;
            return;
        }
        if (allUpLevelNodes.get(size - 1).x < node.x) {
            node.parent = allUpLevelNodes.get(size - 1);
            allUpLevelNodes.get(size - 1).right = node;
            return;
        }

        for (int i = 0; i < size - 1; i++) {
            if (allUpLevelNodes.get(i).x < node.x && allUpLevelNodes.get(i + 1).x > node.x) {
                if (isParent(allUpLevelNodes.get(i), node)) {
                    node.parent = allUpLevelNodes.get(i);
                    allUpLevelNodes.get(i).right = node;
                } else {
                    node.parent = allUpLevelNodes.get(i + 1);
                    allUpLevelNodes.get(i + 1).left = node;
                }
                return;
            }
        }
    }

    private boolean isParent(Node candidate, Node node) {
        while (candidate.parent != null) {
            Node parent = candidate.parent;
            if (parent.left == candidate && parent.x < node.x) return false;
            if (parent.right == candidate && parent.x > node.x) return false;
            candidate = parent;
        }
        return true;
    }

    private class Node implements Comparable<Node> {
        final int val, x, y;
        Node left, right, parent;

        public Node(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (y == o.y) return Integer.compare(x, o.x);
            return Integer.compare(o.y, y);
        }
    }

    public static void main(String[] args) {
        Programmers_Level3_42892 solution = new Programmers_Level3_42892();
        System.out.println(Arrays.deepToString(solution.solution(new int[][]{{5, 3}})));
        System.out.println(Arrays.deepToString(solution.solution(new int[][]{{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}})));
    }
}
