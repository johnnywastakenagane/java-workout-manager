package domain;

public class Running extends Workout {
    private double distance;   // km

    public Running(String date, int duration, double distance) {
        super(date, duration);
        if (distance <= 0)
            throw new IllegalArgumentException("Distance must be positive!");
        this.distance = distance;
    }

    public double getDistance() { return distance; }

    @Override
    public String toString() {
        return "Running{" + super.toString() + ", distance=" + distance + "km}";
    }
}
