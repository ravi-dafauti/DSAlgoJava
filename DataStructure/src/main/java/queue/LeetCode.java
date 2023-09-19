package queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCode {


    //933. Number of Recent Calls
    class RecentCounter {

        Deque<Integer> deque;
        int rangeLeft;
        int rangeRight;

        public RecentCounter() {
            deque = new LinkedList<>();
            int rangeLeft = -3000;
            int rangeRight = 0;
        }

        public int ping(int t) {
            deque.offerLast(t);
            rangeLeft = t - 3000;
            rangeRight = t;

            while (!deque.isEmpty() && deque.peekFirst() <= rangeLeft)
                deque.pollFirst();
            return deque.size();
        }
    }


    //649. Dota2 Senate
    public String predictPartyVictory(String senate) {
        int n = senate.length();

        Queue<Integer> r = new LinkedList<>();
        Queue<Integer> d = new LinkedList<>();
        for(int i = 0; i < senate.length(); i++) {
            if(senate.charAt(i) == 'R')
                r.offer(i);
            else
                d.offer(i);
        }

        while (!r.isEmpty() && !d.isEmpty()) {
            int iR = r.poll();
            int iD = d.poll();
            if(iR < iD) {
                r.offer(iR + n);
            } else {
                d.offer(iD + n);
            }
        }
        if(r.size() > 0)
            return "Radiant";
        else return "Dire";
    }
}
