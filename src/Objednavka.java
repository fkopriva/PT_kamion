public class Objednavka {

    private int cisloObj;
    private int sidlo;
    private int pocetPalet;

    public Objednavka(int cisloObj, int sidlo, int pocetPalet) {
        this.cisloObj = cisloObj;
        this.sidlo = sidlo;
        this.pocetPalet = pocetPalet;
    }

    public String toString() {
        return "Cislo objednavky: " + cisloObj + " misto urceni: " + sidlo + " pocet palet: " + pocetPalet + " ";
    }

    public int getCisloObj() {
        return cisloObj;
    }

    public int getPocetPalet() {
        return pocetPalet;
    }

    public int getSidlo() {
        return sidlo;
    }
}


