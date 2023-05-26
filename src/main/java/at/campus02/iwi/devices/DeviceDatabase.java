package at.campus02.iwi.devices;

import java.util.ArrayList;
import java.util.HashMap;

public class DeviceDatabase {
private ArrayList<Device> devices;
    public DeviceDatabase(){
        devices = new ArrayList<>();
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device d){
        devices.add(d);
    }
    double calculateSumOfProductionCosts(){
        double sum = 0;
        for(Device d : devices){
            sum += d.calculateProductionCost();
        }
        return sum;
    }
    Device findDeviceWithCheapestProductionCost(){
        double cheapest = 0 ;
        Device cDevice = null;

        for(Device d : devices){
            double cost = d.calculateProductionCost();
            if(cDevice == null || cost < cheapest)
            {
                cDevice = d;
                cheapest = cost;
            }
        }
        return cDevice;
    }
    double calculateSupportCostUntilYear(Device device, int year){
        if(year < device.releaseYear){
            return 0;
        }
        double erg = 0;
        for(int y = device.releaseYear; y <= year; ++y){
            erg += device.calculateSupportCostForAYear(y);
        }
        return erg;
    }
    Device findDeviceWithHighestSupportCostUntil(int year){
        Device highestD = null;
        double highestCost = -1;

        for(Device d : devices){
            double cost = calculateSupportCostUntilYear(d, year);
            if(cost > highestCost){
                highestCost = cost;
                highestD = d;
            }
        }
        return highestD;
    }

    //anders Bsp. ---Prozessoren  Schlüssel=Generation, Wert=Anz. d. cores
    ArrayList<Device> findDevicesThatFulfillMinimumPerformance(HashMap<Integer,Integer> minimum){
        ArrayList<Device> erg = new ArrayList<>();

        for(Device d : devices){
            //im Kurs folgendermaßen gelöst:
            //int procGeneration = d.getProcessor().getGeneration();
            //int procCores = d.getProcessor().getNrCores();  ---wieviele cores wir tatsächlich haben
            //int minCores = minimum.get(procGeneration);
            Integer cores = minimum.get(d.getProcessor().getGeneration());  //jedes device hat prozessor. und jeder prozesser hat generation
            //erfuellen wir die Mindestanforderung
            //if (procCores >= minCores) {e.add(d);}
            if(cores != null && d.getProcessor().getNrCores() >= cores){  //if(minimum.containsKey(procGeneration))
                //in procGeneration ist Schlüssel drinnen

                //Unterschied zw. gegebener Lösung und Lösung von Kurs: gegebene Lösung ist kompakter geschrieben
                erg.add(d);
            }
        }
        //schauen, ob Werte überhaupt in Hashmap drinnen (Anz.d.Cores)...(wir bekommen null zurück wenn Schlüsselwert nicht drinnen ist)

        return erg;
    }
    HashMap<String,Integer> numberDevicesGroupedByConstructionCost(double lowBorder, double mediumBorder){
        HashMap<String,Integer> erg = new HashMap<>();
        erg.put("low", 0);
        erg.put("medium", 0);
        erg.put("high", 0);

        // in low = 0, medium = 0, high = 0; ---- Hilfsvariablen; Alternative variante (AV)

        for(Device d : devices){
            double price = d.calculateProductionCost() / d.getNrProduced();
            if(price < lowBorder){
                //im low dazuzaehlen
                //wir wollen es auf low-Wert raufschreiben
                //was soll also drauf sein auf dem Wert - bitte zählt nich Anzahl der Produkte dazu!!!! nur 1 dazuzählen
                //wozu wollen wir 1 dazuzählen? (es steht schon in der Hashmap drinnen auf welchen wert wir zugreifen wollen
                //von welchem schlüssel wollen wir Wert: wie greife ich zu auf hashmap: .get
                //wenn ich allerdings nicht weiß wieviele Fälle es gibt, geht das natürlich im Code nicht mehr
                erg.put("low", erg.get("low") + 1);  //AV low = low +1
            }else{  //da wir im else sind, wissen wir, dass wir >= low border sind
                if(price < mediumBorder){
                    //im medium dazuzaehlen
                    erg.put("medium", erg.get("medium") + 1);  //AV medium + 1
                }else{
                    erg.put("high", erg.get("high") + 1); //AV high + 1
                }
            }
        }

        //AV: in hashmap gehe ich nur einmal ganz am Ende
       //return wird noch angepasst. hashmap heißt erg
        return erg;
    }
}
