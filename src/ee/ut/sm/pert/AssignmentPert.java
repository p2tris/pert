package ee.ut.sm.pert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kristjan
 * Date: 14.10.13
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public class AssignmentPert {
    public static void main(String[] args) throws Exception {

        String filename = args[0];
        if (filename.isEmpty()) {
            throw new FileNotFoundException("No filename given");
        }

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        ArrayList<Node> nodeList = new ArrayList<Node>();
        String line;
        while ((line=reader.readLine())!=null){
            String[] linedata=line.split(",");

        while ((line = reader.readLine()) != null) {
            String[] linedata = line.split(",");

            Node node = new Node(linedata[0], Integer.parseInt(linedata[1]));

            nodeList.add(node);

            for (int i = 2; i < linedata.length; i++) {
                if (linedata[i].isEmpty()) {
                    continue;
                } else {
                    Node predecessor = findPredecessor(nodeList, linedata[i]);
                    node.addPredecessor(predecessor);
                }
                else {
                    Node predecessor=findPredecessor(nodeList,linedata[i]);
                    node.predecessors.add(predecessor);
                }
            }
        }

        System.out.println(nodeList);
        convertToDoc(filename);
        reader.close();

        List<Node> list = new ArrayList<Node>();
        Node lastNode = nodeList.get(nodeList.size() - 1);

        list.add(lastNode);
        getCritical(lastNode, list);

        System.out.println(list);

        //System.out.println(nodeList);
        return;
    }

    private static void getCritical(Node node, List<Node> criticalNodes) {
        if (node.getCriticalNode() == null) {
            return;
        } else {
            criticalNodes.add(node.getCriticalNode());
            getCritical(node.getCriticalNode(), criticalNodes);
        }
    }

    private static Node findPredecessor(List<Node> nodeList, String s) throws Exception {
        for (Node node : nodeList) {
            if (node.getName().equals(s)) {
                return node;
            }
        }

        throw new Exception("Error in file, predecessor is not defined");  //To change body of created methods use File | Settings | File Templates.
    }
    
    private static void convertToDoc (String filename) throws IOException{
    	BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
    	PrintWriter writer = new PrintWriter(filename+".dot", "UTF-8");
    	writer.println("digraph graphname {");
    	
        while ((line=reader.readLine())!=null){
            String[] linedata=line.split(",");
            writer.println("     " + linedata[0] + " [label=\""+ linedata[0] + " (" + linedata[1] +" days)\"];");
            for (int i=2;i<linedata.length;i++){
                if (linedata[i].isEmpty()){
                    continue;
                }
                else {
                    writer.println("     " + linedata[i] + " -> " + linedata[0] + ";");
                }
            }
        }
        writer.println("}");
        writer.close();
        System.out.println("");
    }
}
