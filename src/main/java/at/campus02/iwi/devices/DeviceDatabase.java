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
    ArrayList<Device> findDevicesThatFulfillMinimumPerformance(HashMap<Integer,Integer> minimum){
        ArrayList<Device> erg = new ArrayList<>();

        for(Device d : devices){
            Integer cores = minimum.get(d.getProcessor().getGeneration());
            if(cores != null && d.getProcessor().getNrCores() >= cores){
                erg.add(d);
            }
        }

        return erg;
    }
    HashMap<String,Integer> numberDevicesGroupedByConstructionCost(double lowBorder, double mediumBorder){
        HashMap<String,Integer> erg = new HashMap<>();
        erg.put("low", 0);
        erg.put("medium", 0);
        erg.put("high", 0);

        for(Device d : devices){
            double price = d.calculateProductionCost() / d.getNrProduced();
            if(price < lowBorder){
                //im low dazuzaehlen
                //wir wollen es auf low-Wert raufschreiben
                //was soll also drauf sein auf dem Wert - bitte zählt nich Anzahl der Produkte dazu!!!! nur 1 dazuzählen
                erg.put("low", erg.get("low") + 1);
            }else{  //da wir im else sind, wissen wir, dass wir >= low border sind
                if(price < mediumBorder){
                    //im medium dazuzaehlen
                    erg.put("medium", erg.get("medium") + 1);
                }else{
                    erg.put("high", erg.get("high") + 1);
                }
            }
        }

        return erg;
    }
}
