package repository;

import domain.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutRepository<T extends Workout> {
    protected List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }
}
