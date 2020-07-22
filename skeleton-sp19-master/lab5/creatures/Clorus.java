package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    private int r;

    private int g;

    private int b;

    public Clorus(double e){
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus(){
        this(1);
    }

    public String name(){
        return super.name();
    }


    public Color color(){
        return color(r,g,b);
    }

    public void move() {
        // TODO
        energy = energy - 0.03;
        if(energy < 0){
            energy = 0;
        }
    }

    public void stay(){
        energy = energy - 0.01;
        if(energy < 0){
            energy = 0;
        }
    }

    public void attack(Creature c){
        energy = energy + c.energy();
    }

    public Clorus replicate(){
        energy = 0.5 * energy;
        Clorus sonC = new Clorus(energy);
        return sonC;
    }
    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for(Direction key:neighbors.keySet()){
            if(neighbors.get(key).name().equals("plip")){
                plipNeighbors.add(key);
            }else if(neighbors.get(key).name().equals("empty")){
                emptyNeighbors.add(key);
            }
        }

        // Rule 1
        if(emptyNeighbors.size()==0){
            return new Action(Action.ActionType.STAY);
        }else if(plipNeighbors.size() > 0){ //Rule 2
            return new Action(Action.ActionType.ATTACK,randomEntry(plipNeighbors));
        }else if(energy >= 1){ //Rule 3
            return new Action(Action.ActionType.REPLICATE,randomEntry(emptyNeighbors));
        }else { //Rule 4
            return new Action(Action.ActionType.MOVE,randomEntry(emptyNeighbors));
        }

    }


}
