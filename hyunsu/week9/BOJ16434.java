import java.io.*;
import java.util.*;

public class BOJ16434 {

    static int roomN, atk;
    static Room[] rooms;

    static boolean canClear(int curAtk, long maxHp) {
        long curHp = maxHp;

        // 현재 maxHp로 모든 방을 꺨 수 있는지 확인
        for (Room room : rooms) {
            // 몬스터
            if (room.t == 1) {
                // 1. 현재 공격력만큼 몬스터 깎기
                // 2. 몬스터의 공격력만큼 내 체력 깎기
                // 3. 반복하면서 누가 먼저 0이 되는지 => while 사용시 시간초과, 비효율적
                // ---------파기--------

                // 몬스터의 체력이 현재 공격력으로 나누어 떨어지는지 확인하여 몇턴 나오는지 계산
                int turn;
                if (room.h % curAtk == 0) {
                    turn = room.h / curAtk;
                } else {
                    turn = room.h / curAtk + 1;
                }

                curHp -= (long) room.a * (turn - 1); // 용사가 먼저 공격하므로 -1
            } else {
                // 물약
                curAtk += room.a;
                curHp += room.h;
                if (curHp > maxHp) {
                    curHp = maxHp;
                }
            }

            if (curHp <= 0) {
                return false;
            }
        }

        return true;
    }

    static long solve() {
        // 최대 나올 수 있는 maxHp: 방의 개수 * 몬스터 공격력 * 몬스터 공격력
        long l = 1, r = (long) (roomN * 1e12), ret = 0L;
        while (l <= r) {
            long mid = (l + r) / 2;

            if (canClear(atk, mid)) {
                r = mid - 1;
                ret = mid;
            } else {
                l = mid + 1;
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        roomN = Integer.parseInt(st.nextToken());
        atk = Integer.parseInt(st.nextToken());

        rooms = new Room[roomN];
        for (int i = 0; i < roomN; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            rooms[i] = new Room(t, a, h);
        }

        System.out.println(solve());
    }

    static class Room {

        int t, a, h;

        public Room(int t, int a, int h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }
}
