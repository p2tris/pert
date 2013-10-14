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

    /*public static Node[] criticalPath(Set<Node> tasks){
        //tasks whose critical cost has been calculated
        HashSet<Node> completed = new HashSet<Node>();
        //tasks whose ciritcal cost needs to be calculated
        HashSet<Node> remaining = new HashSet<Node>(tasks);

        //Backflow algorithm
        //while there are tasks whose critical cost isn't calculated.
        while(!remaining.isEmpty()){
            boolean progress = false;

            //find a new task to calculate
            for(Iterator<Node> it = remaining.iterator();it.hasNext();){
                Node task = it.next();

                if(task.getPredecessors()==null){
                    completed.add(task);
                    progress = true;
                    it.remove();
                    continue;
                }

                if(completed.containsAll(task.getPredecessors())){
                    //all predecessors calculated, critical cost is max dependency
                    //critical cost, plus our cost
                    int critical = 0;
                    for(Node t : task.getPredecessors()){
                        if(t.criticalCost > critical){
                            critical = t.criticalCost;
                        }
                    }
                    task.criticalCost = critical+task.duration;
                    //set task as calculated an remove
                    completed.add(task);
                    it.remove();
                    //note we are making progress
                    progress = true;
                }
            }
            //If we haven't made any progress then a cycle must exist in
            //the graph and we wont be able to calculate the critical path
            if(!progress) throw new RuntimeException("Cyclic dependency, algorithm stopped!");
        }

        //get the tasks
        Node[] ret = completed.toArray(new Node[0]);
        //create a priority list
        Arrays.sort(ret, new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                //sort by cost
                int i = o2.criticalCost - o1.criticalCost;
                if (i != 0) return i;

                //using dependency as a tie breaker
                //note if a is dependent on b then
                //critical cost a must be >= critical cost of b
                if (o1.isDependent(o2)) return -1;
                if (o2.isDependent(o1)) return 1;
                return 0;
            }
        });

        return ret;
    }

    public boolean isDependent(Node t){
        //is t a direct dependency?
        if(predecessors.contains(t)){
            return true;
        }
        //is t an indirect dependency
        for(Node dep : predecessors){
            if(dep.isDependent(t)){
                return true;
            }
        }
        return false;
    }*/
}
