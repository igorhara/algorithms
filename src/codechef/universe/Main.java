package codechef.universe;

import java.util.*;

/**
 * Created by igorhara on 25/08/17.
 *
 * https://www.codechef.com/problems/UNIVERSE
 *
 */
public class Main {


    private static int[][] planetLink;
    private static Node[][] portals;
    private static Node[][] queries;


    public static  class Node {
        int planet;
        int universe;

        public Node(int planet, int universe) {
            this.planet = planet;
            this.universe = universe;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (planet != node.planet) return false;
            return universe == node.universe;
        }

        @Override
        public int hashCode() {
            int result = planet;
            result = 31 * result + universe;
            return result;
        }
    }


    public static void addConnection(int index,int p1,int p2){
        planetLink[index]=new int[]{p1,p2};
    }

    public static void addPortals(int index,int p1,int u1,int p2,int u2){
        portals[index]=new Node[]{new Node(p1,u1),new Node(p2,u2)};
    }

    public static void addQuery(int index,int p1,int u1,int p2,int u2){
        queries[index]=new Node[]{new Node(p1,u1),new Node(p2,u2)};
    }

    public static void main(String args[]){
        getData();

        for(Node[] query: queries){
            Node start = query[0];
            Node target = query[1];

            System.out.println (findSteps(target, start));

        }


    }

    private static String findSteps(Node target, Node start) {
        List<Node> toCheck = new ArrayList<Node>();
        List<Node> checked = new ArrayList<Node>();
        toCheck.add(start);

        Map<Node,Integer> steps = new HashMap<>();
        steps.put(start,0);
        while(steps.get(target)==null && !toCheck.isEmpty()){

            Node node=null;
            node = getNextNode(toCheck,checked, steps);

            searchLinks(toCheck, checked, steps, node);
            searchPortals(toCheck, checked, steps, node);
        }

        if(steps.get(target)==null){
            return "Impossible";
        }else{
            return steps.get(target).toString();
        }
    }

    private static void searchLinks(List<Node> toCheck, List<Node> checked, Map<Node, Integer> steps, Node node) {
        int step = steps.get(node);
        for(int[] next: planetLink){
            int p =-1;
            if(next[0]==node.planet){
              p = next[1];
            }else if (next[1]==node.planet){
              p = next[0];
            }
            if(p!=-1){
              Node newNode = new Node(p,node.universe);
              if(steps.get(newNode)==null || steps.get(newNode) > step+1){
                  steps.put(newNode,step+1);
              }
              if(!checked.contains(newNode)){
                  toCheck.add(newNode);
              }
            }
        }
    }

    private static void searchPortals(List<Node> toCheck, List<Node> checked, Map<Node, Integer> steps, Node node) {
        int step = steps.get(node);
        for(Node[] next: portals){
            Node newNode = null;
            if(next[0].equals(node) ){
                newNode = next[1];
            }else if (next[1].equals(node)){
                newNode = next[0];
            }
            if(newNode!=null){
                if(steps.get(newNode)==null || steps.get(newNode) > step+1){
                    steps.put(newNode,step+1);
                }
                if(!checked.contains(newNode)){
                    toCheck.add(newNode);
                }
            }
        }
    }

    private static Node getNextNode(List<Node> toCheck, List<Node> checked, Map<Node, Integer> steps) {
        Node node =null;
        for (Node nodeToSee: toCheck) {
           if(node ==null){
               node = nodeToSee;
           }else{
               int stepNode = steps.get(node);
               int stepToSee = steps.get(nodeToSee);
               if(stepNode<stepToSee){
                   node = nodeToSee;
               }
           }
        }
        toCheck.remove(node);
        checked.add(node);
        return node;
    }

    private static void getData() {

        int planets,nPortals,nQueries;
        Scanner reader = new Scanner(System.in);


        planets = reader.nextInt();
        nPortals = reader.nextInt();
        nQueries = reader.nextInt();

        System.out.println("");

        planetLink = new int[planets-1][2];
        for(int i = 0; i < planets-1;i++ ){
            int p1 = reader.nextInt();
            int p2 = reader.nextInt();

            addConnection(i,p1,p2);
            System.out.println("");
        }
        portals = new Node[nPortals][2];
        for(int i = 0; i < nPortals;i++ ){
            int p1 = reader.nextInt();
            int u1 = reader.nextInt();
            int p2 = reader.nextInt();
            int u2 = reader.nextInt();

            addPortals(i,p1,u1,p2,u2);
            System.out.println("");
        }

        queries = new Node[nQueries][2];
        for(int i = 0; i < nQueries;i++ ){
            int p1 = reader.nextInt();
            int u1 = reader.nextInt();
            int p2 = reader.nextInt();
            int u2 = reader.nextInt();

            addQuery(i,p1,u1,p2,u2);
            System.out.println("");
        }
    }


}
