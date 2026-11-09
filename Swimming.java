package domain;

public class Swimming extends Workout {
    private int laps;
    private int poolLength;   // meters

    public Swimming(String date, int duration, int laps, int poolLength) {
        super(date, duration);
        if (laps <= 0 || poolLength <= 0)
            throw new IllegalArgumentException("Laps and pool length must be positive!");
        this.laps = laps;
        this.poolLength = poolLength;
    }

    public int getLaps() { return laps; }
    public int getPoolLength() { return poolLength; }

    @Override
    public String toString() {
        return "Swimming{" + super.toString() + ", laps=" + laps + ", pool=" + poolLength + "m}";
    }
}
