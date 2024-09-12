package implementation;

class Programmers_Level2_49993 {
    public int solution(String skill, String[] skill_trees) {
        int answer = skill_trees.length;
        
        char[] orders = new char[skill.length()];
        for (int i = 0; i < orders.length; i++) orders[i] = skill.charAt(i);
        
        int[] appear = new int[27];
        int offset = 'A' - '0';
        for (char c : skill.toCharArray()) {
            int idx = c - '0';
            appear[idx - offset] = 1;
        }

        for (String skillTree : skill_trees) {
            char[] tree = skillTree.toCharArray();
            int orderPointer = 0;
            boolean flag = true;
            for (char c : tree) {
                if (orderPointer == orders.length) break;
                if (c == orders[orderPointer]) orderPointer++;
                else if (appear[c - '0' - offset] == 1) {
                    flag = false;
                    break;
                }
            }
            if (!flag) answer--;
        }
        return answer;
    }

    public static void main(String[] args) {
        Programmers_Level2_49993 solution = new Programmers_Level2_49993();
        System.out.println(solution.solution("Z", new String[]{"Z"}));
        System.out.println(solution.solution("CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"}));
    }
}
