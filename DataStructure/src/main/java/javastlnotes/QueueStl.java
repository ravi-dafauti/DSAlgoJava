package javastlnotes;

import java.util.LinkedList;
import java.util.Queue;

public class QueueStl {
    Queue<Integer> queue = new LinkedList<>();

    public void usage() {
        queue.offer(1); // return true if added else returns false.
        queue.add(1); // return true if added else throws exception is capacity is violated, offer give false in this case.

        /**
         * Retrieves and removes the head of this queue,
         * or returns {@code null} if this queue is empty.
         **/
        Integer front = queue.poll();
        /**
         * Retrieves and removes the head of this queue.  This method differs
         * from {@link #poll() poll()} only in that it throws an exception if
         * this queue is empty.
         * */
        Integer front_1 = queue.remove();

        queue.isEmpty();
        queue.size();

        /**
         * Retrieves, but does not remove, the head of this queue,
         * or returns {@code null} if this queue is empty.
         *
         * @return the head of this queue, or {@code null} if this queue is empty
         */
        Integer front_2 = queue.peek();

        /**
         * Retrieves, but does not remove, the head of this queue.  This method
         * differs from {@link #peek peek} only in that it throws an exception
         * if this queue is empty.
         *
         * @return the head of this queue
         * @throws NoSuchElementException if this queue is empty
         */
        Integer front_3 = queue.element();
    }

}
