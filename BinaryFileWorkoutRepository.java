package repository;

import domain.Workout;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileWorkoutRepository<T extends Workout> extends WorkoutRepository<T> {
    private final String fileName;

    public BinaryFileWorkoutRepository(String fileName) {
        this.fileName = fileName;
        loadFromFile();
    }

    @Override
    public void add(T item) {
        super.add(item);
        saveToFile();
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            items = (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load file: " + e.getMessage());
            items = new ArrayList<>();
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(items);
        } catch (IOException e) {
            System.out.println("Could not save file: " + e.getMessage());
        }
    }
}
