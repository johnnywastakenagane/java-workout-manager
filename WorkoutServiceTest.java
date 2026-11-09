package service;

import domain.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.WorkoutRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutServiceTest {
    private WorkoutService service;

    @BeforeEach
    void setUp() {
        service = new WorkoutService(new WorkoutRepository<>());   // in-memory, no file
        service.addRunning("2026.10.05", 30, 5.2);
        service.addSwimming("2026.10.05", 45, 40, 25);
        service.addRunning("2026.11.02", 60, 10.0);
    }

    @Test
    void filterLongerThan40() {
        List<Workout> result = service.filterLongerThan(40);
        assertEquals(2, result.size());
        for (Workout w : result)
            assertTrue(w.getDuration() > 40);
    }

    @Test
    void filterLongerThanIsStrict() {
        // exactly 60 is NOT longer than 60
        assertTrue(service.filterLongerThan(60).isEmpty());
    }

    @Test
    void filterLongerThanZeroReturnsAll() {
        assertEquals(3, service.filterLongerThan(0).size());
    }
}
