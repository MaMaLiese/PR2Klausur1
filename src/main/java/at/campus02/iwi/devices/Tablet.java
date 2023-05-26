package at.campus02.iwi.devices;

public class Tablet extends Device{
protected boolean arCamera;

    public Tablet(String name, Processor processor, int releaseYear, int nrProduced, boolean arCamera) {
        super(name, processor, releaseYear, nrProduced);
        this.arCamera = arCamera;
    }

    public boolean isArCamera() {
        return arCamera;
    }

    public void setArCamera(boolean arCamera) {
        this.arCamera = arCamera;
    }

    @Override
    public String toString() {
        return "Tablet " + name + ":" + nrProduced;
    }

    @Override
    public double calculateProductionCost() {
        double result = 700;
        switch(processor.getGeneration()){
            case 1:
                if(processor.getNrCores() == 1){
                    result = 150;
                }else{
                    result = 200;
                }
                break;
            case 2:
                if(processor.getNrCores() >= 8){
                    result = 450;
                }else{
                    if(processor.getNrCores() >= 4){
                        result = 400;
                    }else{
                        result = 350;
                    }
                }
                break;
        }

        if(arCamera){
            result += 50;
        }
        return result * nrProduced;
    }

    @Override
    public double calculateSupportCostForAYear(int year) {
        int diffYear = year - releaseYear;

        return nrProduced * (50 * (1 - diffYear * 0.1));
    }
}
