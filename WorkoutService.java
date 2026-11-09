package service;

import domain.Running;
import domain.Swimming;
import domain.Workout;
import repository.WorkoutRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class WorkoutService {
    private final WorkoutRepository<Workout> repo;

    public WorkoutService(WorkoutRepository<Workout> repo) {
        this.repo = repo;
    }

    public void addRunning(String date, int duration, double distance) {
        repo.add(new Running(date, duration, distance));
    }

    public void addSwimming(String date, int duration, int laps, int poolLength) {
        repo.add(new Swimming(date, duration, laps, poolLength));
    }

    // ascending by date; same date -> descending by duration
    public List<Workout> getAllSorted() {
        List<Workout> list = repo.getAll();
        list.sort(Comparator.comparing(Workout::getDate)
                .thenComparing(Workout::getDuration, Comparator.reverseOrder()));
        return list;
    }

    public List<Workout> filter(Predicate<Workout> predicate) {
        List<Workout> result = new ArrayList<>();
        for (Workout w : repo.getAll())
            if (predicate.test(w))
                result.add(w);
        return result;
    }

    public List<Workout> filterLongerThan(int minutes) {
        return filter(w -> w.getDuration() > minutes);
    }

    // month given as "yyyy.mm" -> Java 8 streams
    public int totalMinutesInMonth(String month) {
        return repo.getAll().stream()
                .filter(w -> w.getDate().startsWith(month + "."))
                .mapToInt(Workout::getDuration)
                .sum();
    }

    public boolean isEmpty() {
        return repo.getAll().isEmpty();
    }
}
