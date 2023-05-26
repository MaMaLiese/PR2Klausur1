package statischeMeth;

public class StaticTest {
    private int hansi1;
    private int hansi2;

    private static int hansi3 = 3;
    //kann ich ohne objekt/instanz aufrufen
    //deswegen kan nich auch nicht auf attribute zugreifen (nur auf statische

    public static int meineMethode(int zahl){
        //hansi1, hansi2 geht nicht
        return hansi3 + zahl;
    }
}
