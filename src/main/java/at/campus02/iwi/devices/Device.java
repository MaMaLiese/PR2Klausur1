package at.campus02.iwi.devices;

public abstract class Device {
    protected String name;
    protected Processor processor;
    protected int releaseYear;
    protected int nrProduced;

    public Device(String name, Processor processor, int releaseYear, int nrProduced) {
        this.name = name;
        this.processor = processor;
        this.releaseYear = releaseYear;
        this.nrProduced = nrProduced;
    }

    public abstract double calculateProductionCost();
    public abstract double calculateSupportCostForAYear(int year);
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getNrProduced() {
        return nrProduced;
    }

    public void setNrProduced(int nrProduced) {
        if(nrProduced < 0){
            nrProduced = 0;
        }
        this.nrProduced = nrProduced;
    }
}
