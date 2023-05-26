package at.campus02.iwi.devices;

public class Smartphone extends Device{
    protected int nrCameras;
    public Smartphone(String name, Processor processor, int releaseYear, int nrProduced, int nrCameras) {
        super(name, processor, releaseYear, nrProduced);
        this.nrCameras = nrCameras;
    }

    public int getNrCameras() {
        return nrCameras;
    }

    public void setNrCameras(int nrCameras) {
        this.nrCameras = nrCameras;
    }
    @Override
    public String toString() {
        return "Smartphone " + name + ":" + nrProduced;
    }

    @Override
    public double calculateProductionCost() {
        double result = processor.getGeneration() * 100;
        result += nrCameras * 20;
        if(nrProduced >= 1000000){
            result *= 0.8;
        }else{
            if(nrProduced >= 100000){
                result *= 0.9;
            }
        }
        return result * nrProduced;
    }

    @Override
    public double calculateSupportCostForAYear(int year) {
        int diffYears = year - releaseYear;
        if(diffYears == 0 || diffYears == 1){
            return 0.0;
        }

        return processor.getGeneration() * 40 * nrProduced;
    }
}
