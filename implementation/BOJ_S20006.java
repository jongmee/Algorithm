package implementation;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BOJ_S20006 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int p = parseInt(inputs[0]), m = parseInt(inputs[1]);

        int roomsIdx = 0;
        int[] roomsLevel = new int[p];
        List<List<Player>> rooms = new ArrayList<>();
        initRooms(rooms, p);

        for (int idx = 0; idx < p; idx++) {
            inputs = br.readLine().split(" ");
            int inputLevel = parseInt(inputs[0]);
            String name = inputs[1];
            boolean roomFound = false;
            for (int i = 0; i < roomsIdx; i++) {
                if (rooms.get(i).size() < m && checkRange(roomsLevel[i], inputLevel)) {
                    rooms.get(i).add(new Player(inputLevel, name));
                    roomFound = true;
                    break;
                }
            }
            if (!roomFound) {
                rooms.get(roomsIdx).add(new Player(inputLevel, name));
                roomsLevel[roomsIdx] = inputLevel;
                roomsIdx++;
            }
        }

        for (List<Player> room : rooms) {
            StringBuilder sb = new StringBuilder();
            if (room.size() == m) sb.append("Started!\n");
            else if (room.isEmpty()) continue;
            else sb.append("Waiting!\n");

            Collections.sort(room);

            for (Player player : room) {
                sb.append(player.level)
                        .append(" ")
                        .append(player.name)
                        .append("\n");
            }
            bw.write(sb.toString());
        }
        bw.flush();
        bw.close();
    }

    static void initRooms(List<List<Player>> rooms, int p) {
        for (int i = 0; i < p; i++) rooms.add(new ArrayList<>());
    }

    static boolean checkRange(int level, int inputLevel) {
        return level - 10 <= inputLevel && level + 10 >= inputLevel;
    }

    static class Player implements Comparable<Player> {
        int level;
        String name;

        Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return name.compareTo(o.name);
        }
    }
}
