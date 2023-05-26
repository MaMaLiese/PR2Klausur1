package at.campus02.iwi.devices;

import java.util.Comparator;

public class DeviceComparator implements Comparator<Device> { //comparable ist interface das wir implementieren bei Klasse selber
    //deshalb kann ich separate Klassen machen, die implementieren dann comparator
    @Override
    public int compare(Device o1, Device o2) {  //0 liefere ich zurück, wenn es mir vom sortieren her wurscht ist
        //wenn Objekt 1 vorher in der Sortierung sein soll - soll negativen Wert liefern (-1 muss aber nicht unbedingt -1 sein)
        // kann auch -12 sein - es gibt fixfertigen
        //sortieralgorithmus
       int s = Integer.compare(o1.getProcessor().getGeneration(), o2.getProcessor().getGeneration());
        if(s == 0){
            //nach Prozessorkernen sortieren
           return Integer.compare(o1.getProcessor().getNrCores(), o2.getProcessor().getNrCores());
        }
        //weil erstes Kriterium (prozessorgeneration) schon eindeutige sortierung vorgibt
        return s; //vgl. wenn ich nachname sortiere und Nachnamen schon unterschiedlich sind, brauch ich mich nicht um Vornamen kümmern

        //normal immer nur "zurückliefern" und nie "ausgeben" (ausser in main) - im comparator-Bsp ausnahme

    }
}
