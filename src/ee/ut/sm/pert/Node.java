package ee.ut.sm.pert;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kristjan
 * Date: 14.10.13
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class Node {
    public HashSet<Node> getPredecessors() {
        return predecessors;
    }

    private HashSet<Node> predecessors;
    private int duration;
    private String name;
    private int criticalCost;
    private Node criticalNode;

    public Node(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public void addPredecessor(Node predecessor){
        if (predecessors==null){
            predecessors=new HashSet<Node>();
        }

        if (criticalNode==null){
            criticalNode=predecessor;
        }
        else if(criticalNode.getCriticalCost()<=predecessor.getCriticalCost()){
            this.criticalCost=predecessor.getCriticalCost()+duration;
            this.criticalNode=predecessor;
        }

        predecessors.add(predecessor);
    }

    public int getCriticalCost() {
        return criticalCost;
    }

    public Node getCriticalNode(){
        return criticalNode;
    }

    public void setCriticalCost(int criticalCost) {
        this.criticalCost = criticalCost;
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
