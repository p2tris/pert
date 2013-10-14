package ee.ut.sm.pert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kristjan
 * Date: 14.10.13
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class Node {

    private List<Node> predecessors;
    private int duration;
    private String name;
    private int criticalCost;
    private Node criticalNode;

    public Node(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public List<Node> getPredecessors() {
        return predecessors;
    }

    public void addPredecessor(Node predecessor) {
        if (predecessors == null) {
            predecessors = new ArrayList<Node>();
        }

        if (criticalNode == null) {
            criticalNode = predecessor;
        } else if (criticalNode.getCriticalCost() <= predecessor.getCriticalCost()) {
            setCriticalCost(predecessor.getCriticalCost() + duration);
            setCriticalNode(predecessor);
        }

        predecessors.add(predecessor);
    }

    public int getCriticalCost() {
        return criticalCost;
    }

    public void setCriticalCost(int criticalCost) {
        this.criticalCost = criticalCost;
    }

    public Node getCriticalNode() {
        return criticalNode;
    }

    public void setCriticalNode(Node criticalNode) {
        this.criticalNode = criticalNode;
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
    public String toString() {
        return name;// + "," + duration + "," + criticalCost;// + "," + predecessors;
    }
}
