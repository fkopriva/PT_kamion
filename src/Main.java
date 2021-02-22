import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        Thread obj = new MyThread();
        Thread routes = new MyThread();
        obj.start();
        routes.start();
    }
}

class MyThread extends Thread {
    public static Gen gen = new Gen();
    final public String FILENAME = "objednavky.csv";
    public Queue<Objednavka> fronta = new LinkedList<>();


    @Override
    public void run() {
        readFile(FILENAME);
        gen.gen();
    }

    private void readFile(String file) {
        String[] params;
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) {
                params = line.split(";");

                if ("6".equals(params[2])) {

                } else {
                    fronta.add(new Objednavka(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2])));
                    chechPredchudce();
                }
                //System.out.println(fronta);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chechPredchudce() {
        int[] predchudce = gen.getNeco();
        int i = 0;
        Iterator<Objednavka> iter = fronta.iterator();
        while (iter.hasNext()) {
            int sidlo = iter.next().getSidlo();
            if (sidlo == predchudce[i]) {
                //udelas
            } else {
                i = 0;
                continue;
            }
        }
    }

   /* private boolean checkCasAPocet() {

    }*/
}















