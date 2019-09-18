public abstract class ARS {
    private String name;
    private int popularity;
    private int averagePrice;
    private int numberOfWorkers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(int averagePrice) {
        this.averagePrice = averagePrice;
    }

    public int getNumberOfWorkers() {
        return numberOfWorkers;
    }

    public void setNumberOfWorkers(int numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }

    @Override
    public String toString() {
        return this.getClass().getName()+"{" +
                "name='" + name + '\'' +
                ", popularity=" + popularity +
                ", averagePrice=" + averagePrice +
                ", numberOfWorkers=" + numberOfWorkers +
                '}';
    }
}
