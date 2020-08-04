import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    PriorityQueue<Flight> minStart;
    PriorityQueue<Flight> minEnd;
    Comparator<Flight> StartComparator = Comparator.comparingInt(i -> i.startTime);
    Comparator<Flight> EndComparator = Comparator.comparingInt(i -> i.endTime);



    public FlightSolver(ArrayList<Flight> flights) {
        /* FIX ME */
        minStart = new PriorityQueue<>(StartComparator);
        minEnd = new PriorityQueue<>(EndComparator);
        for (Flight i : flights){
            minStart.add(i);
            minEnd.add(i);
        }

    }

    public int solve() {
        /* FIX ME */
        int max = 0;
        int curmax = 0;
        while (!minStart.isEmpty() && !minEnd.isEmpty()){
            if(minStart.peek().startTime <= minEnd.peek().endTime){
                curmax += minStart.peek().passengers;
                minStart.poll();
            }else{
                curmax -= minEnd.peek().passengers;
                minEnd.poll();
            }
            if(curmax > max){
                max = curmax;
            }
        }
        return max;
    }

}
