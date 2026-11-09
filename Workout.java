package domain;

import java.io.Serializable;

public abstract class Workout implements Serializable {
    private static final long serialVersionUID = 1L;

    private String date;      // "yyyy.mm.dd"
    private int duration;     // minutes

    public Workout(String date, int duration) {
        if (duration <= 0)
            throw new IllegalArgumentException("Duration must be positive!");
        this.date = date;
        this.duration = duration;
    }

    public String getDate() { return date; }
    public int getDuration() { return duration; }

    @Override
    public String toString() {
        return "date=" + date + ", duration=" + duration + "min";
    }
}
