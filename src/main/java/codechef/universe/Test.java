package codechef.universe;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by igorhara on 26/08/17.
 */
public class Test {

    public static void main(String[] args){
        int planets = 8 * 100;

        int nPortals= 5 * 100;

        int universes = 50;

        int nQueries = 10;

        Random rand = new Random();


        Main.planetLink = new int[planets-1][2];
        for(int i = 0; i < planets-1;i++ ){
            int p1 = rand.nextInt(planets)+1;
            int p2 = rand.nextInt(planets)+1;

            Main.addConnection(i,p1,p2);
        }
        Main.portals = new Main.Node[nPortals][2];
        for(int i = 0; i < nPortals;i++ ){
            int p1 = rand.nextInt(planets)+1;
            int u1 = rand.nextInt(universes)+1;
            int p2 = rand.nextInt(planets)+1;
            int u2 = rand.nextInt(universes)+1;

            Main.addPortals(i,p1,u1,p2,u2);

        }

        for(int i = 0; i < nQueries;i++ ){
            int p1 = rand.nextInt(planets)+1;
            int u1 = rand.nextInt(universes)+1;
            int p2 = rand.nextInt(planets)+1;
            int u2 = rand.nextInt(universes)+1;

            Main.Node start = new Main.Node(p1,u1);
            Main.Node target = new Main.Node(p2,u2);
            System.out.printf(" %d %d %d %d => ", p1,u1,p2,u2);
            System.out.println (Main.findSteps(target, start));

        }

    }
}
