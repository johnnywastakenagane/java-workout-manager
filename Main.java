import domain.Workout;
import repository.BinaryFileWorkoutRepository;
import repository.WorkoutRepository;
import service.WorkoutService;
import ui.UI;

public class Main {
    public static void main(String[] args) {
        WorkoutRepository<Workout> repo = new BinaryFileWorkoutRepository<>("workouts.bin");
        WorkoutService service = new WorkoutService(repo);

        if (service.isEmpty()) {
            service.addRunning("2026.10.05", 30, 5.2);
            service.addSwimming("2026.10.05", 45, 40, 25);   // same date, longer -> shown first
            service.addRunning("2026.11.02", 60, 10.0);
            service.addSwimming("2026.09.20", 50, 30, 50);
        }

        new UI(service).run();
    }
}
