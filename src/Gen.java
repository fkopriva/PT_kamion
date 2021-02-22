import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Gen {
    final private static String FILENAME = "sidla.csv";
    final private static short POCET_SIDEL = 2000;
    final private static short POCET_CEST = 2;
    final private static short VLASTNI_UZEL = 0;
    final private static short KILOMETRY = 50;
    final private static Random rand = new Random();
   // final private static int[] rozdeleni = {(int)(POCET_SIDEL*0.25), (int)(POCET_SIDEL*0.25), (int)(POCET_SIDEL*0.20), (int)(POCET_SIDEL*0.15), (int)(POCET_SIDEL*0.1), (int)(POCET_SIDEL*0.05)};

    //private static int[][] pomer;
    private static int[][] matrix = new int[POCET_SIDEL][POCET_SIDEL];
    private static int counter = 0;
    /*private static int maxEdges;// = POCET_CEST * POCET_SIDEL;
    private static Graph graph;
    private static Sidla[] sidla;*/
    public int[] neco = new int[matrix.length];

    public void gen() {
        final Test pruchod = new Test();

        int[] distances = new int[neco.length];
        distances[0] = 0;
        int j = 1;

        genNMatrix();
        //createRoutes();

        genMatrix();

        neco = pruchod.doDijkstra(matrix, 0);

       /* for (int i = 0; i < POCET_SIDEL; i++) {
            for (int k = 0; k < POCET_SIDEL; k++) {
                System.out.print(matrix[i][k] + " ");
            }
            System.out.println();
        }

        /*for (int i : neco) {
            System.out.println(i);
        }*/
        //genRestNumbs();

        for (int i = 1; i < neco.length; i++) {
            int node = neco[i];
            int temp = i;
            do {
                distances[j] += matrix[node][temp];
                temp = node;
                node = neco[node];
            } while (temp != 0);
            j++;
        }

        for (int p : distances) {
            System.out.println(p);
        }
        //System.out.println("empty count " + count);



























        /*BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(new File(FILENAME)));
            int i = 0;
            String sidlo = "Sidlo ";

            while (i <= POCET_SIDEL) {
                bw.write(sidlo + i + "\n");
                i++;
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        //genMatrix();
        //createRoutes();

       // fillRestNumbs();

        //vypis();

       // fillGraph();
        //printGraph();

        //System.out.println(" me " + maxEdges);

        //fillPomer();
        //rozdelSidla();

       /*for (int i =0; i < sidla.length; i++){
            System.out.println(sidla[i]);
        }*/

       // Graph.BellmanFord(graph, 0);

       // printArr(Graph.disc, POCET_SIDEL);
        //System.out.println("maxEdges " + maxEdges);


    }

   /* private static int rndWithEx(int start, int end, int[] exclude ) {
        int random = start + rand.nextInt(end - start + 1 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }*/

    private static void genNMatrix(){
        for (int i = 0; i < POCET_SIDEL; i++){
            for (int j = 0; j < POCET_SIDEL; j++){
                if (i == j){
                    matrix[i][j] = VLASTNI_UZEL;
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }/*


    private static void createRoutes() {
        int index;
        int tmp;
        int[] random = new int[POCET_CEST];
        int Edges = 0;

        genNMatrix();
        for (int i = 0; i < POCET_SIDEL; i++) {
            //random = new int[POCET_CEST];
            index = 0;
            for (int j = 0; j < POCET_CEST; j++) {
                tmp = rndWithEx(0, POCET_SIDEL - 1, random);
                random[index] = tmp;
                index++;

                Edges++;
                if((Integer.MAX_VALUE) == (matrix[tmp][i])){
                    matrix[i][tmp] = (rand.nextInt(KILOMETRY) + 1);
                    } else {
                    matrix[i][tmp] = matrix[tmp][i];
                }
            }
            Arrays.fill(random, 0);
           index = 0;
        }
        //System.out.println("Edges " + Edges);
        maxEdges = Edges;
    }*/


    private static void genMatrix() {
        for (int i = 0; i < POCET_SIDEL; i++) {
            for (int j = 0; j < POCET_SIDEL; j++) {
                if (j < i) {
                    continue;
                } else if (j == i) {
                    fillDiagonal(j);
                } else {
                    if (i >= (POCET_SIDEL - POCET_CEST)) {
                        counter++;
                    }
                    genRoutes(i);
                    break;
                }
            }
        }
    }

   private static void fillDiagonal(final int row) {
        matrix[row][row] = VLASTNI_UZEL;
    }

    private static void genRoutes(final int index) {
        int row = index + 1;
        List<Short> routes = new ArrayList<>();
        while (routes.size() < (POCET_CEST - counter)) {
            short numb = (short) (rand.nextInt(POCET_SIDEL - row) + (row));
            if (routes.contains(numb)) {
                continue;
            }
            routes.add(numb);
        }
        Collections.sort(routes);
        genNumbs(routes, index);
    }

    private static void genNumbs(final List<Short> routes, final int index) {
        if (!routes.isEmpty()) {
            for (short route : routes) {
                int numb = (rand.nextInt(KILOMETRY ) + 5);
                matrix[index][route] = (numb);
                //maxEdges++;
                matrix[route][index] = (numb);
               // maxEdges += 2;
            }
        }
    }

    /*private static void genRestNumbs() {
        int numb;

        for (int i = 0; i < POCET_SIDEL; i++) {
            for (int j = 0; j < POCET_SIDEL; j++) {
                numb = matrix[i][j];
                if (numb > 0 && numb < 100) {
                    matrix[j][i] = numb;
                }
            }
        }
    }


   /* private static void vypis() {
        for (int i = 0; i < POCET_SIDEL; i++) {
            for (int j = 0; j < POCET_SIDEL; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
   }

   private static void fillGraph(){
        String tmp;
        graph = new Graph(POCET_SIDEL, maxEdges);
        int count = 0;
        for (int i = 0; i < POCET_SIDEL; i++){
            for (int j =0; j < POCET_SIDEL; j++){
                tmp = matrix[i][j];
                if(!tmp.equals(Double.toString(NENI_CESTA)) && !tmp.equals(Short.toString(VLASTNI_UZEL))) {

                    graph.edge[count].src = i;
                    graph.edge[count].dest = j;
                    graph.edge[count].weight = Integer.parseInt(matrix[i][j]);
                    count++;
                }
            }
        }
   }

   private static void printGraph(){ //pouze pro testovani naplneni grafu
        int count = 0;
        for (int i = 0; i < graph.edge.length; i++){
           /* System.out.print("source " + graph.edge[i].src + "=> ");
            for (int j = 0; j < graph.edge.length; j++){
                System.out.print(" " +graph.edge[j].dest + " ");
            }
            System.out.println();
            //while (graph.edge[i].dest)
            if(graph.edge[i].weight == 0) count++;
            System.out.println("source " + graph.edge[i].src +
                    " destination " + graph.edge[i].dest +
                    " weight " + graph.edge[i].weight);*/
       // }

 /*  }
    private static void printArr(int dist[], int V)
    {
        System.out.println("Vertex   Distance from Source");
        for (int i=0; i<V; ++i)
            System.out.println(i+"\t\t"+ dist[i]);
    }

    private static void fillPomer(){
        pomer = new int[2][6];

        for (int i = 0; i < 6; i++){
            pomer[0][i] = rozdeleni[i];
            pomer[1][i] = 0;
        }
    }

    private static void rozdelSidla(){
        sidla = new Sidla[POCET_SIDEL];
        int i = 0;
        int cele = 0;
        int pulka = 0;
        int pruchod = 0;
        int index = 4;

        while (i < POCET_SIDEL && index >= 0){
            if (pomer[1][5] != pomer[0][5]){
                int a = pruchod % 2;
                switch (a){
                    case 0: if (cele == 6){
                        cele = 0;

                    } if(cele == 5){
                        pruchod++;
                    }
                            sidla[i] = new Sidla(cele + 1,0 );
                            pomer[1][cele] += 1;
                            cele++;
                            i++;
                            break;
                    case 1: if(pulka == 3){
                        pulka = 0;

                    } if(pulka == 2) {
                        pruchod++;
                    }

                        sidla[i] = new Sidla(pulka + 1,0 );
                        pomer[1][pulka] += 1;
                        pulka++;
                        i++;
                        break;
                }

            } else {
                cele = 0;
                while(pomer[1][index] != pomer[0][index]){
                    if (cele == index + 1) cele = 0;
                    sidla[i] = new Sidla(cele + 1,0 );
                    pomer[1][cele] += 1;
                    cele++;
                    i++;
                }
                index--;
            }
        }
        for (int k = 0; k < 6; k++){
            System.out.println(pomer[1][k]);
        }
    }*/

    public int[] getNeco() {
        return neco;
    }
}
