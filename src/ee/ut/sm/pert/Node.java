package ee.ut.sm.pert;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Kristjan
 * Date: 14.10.13
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class Node {
    private int duration;
    private String name;
    public HashSet<Node> predecessors;

    public Node(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name+","+duration+","+predecessors;
    }
}
