package ui;

import domain.Workout;
import service.WorkoutService;

import java.util.List;
import java.util.Scanner;

public class UI {
    private final WorkoutService service;
    private final Scanner scanner = new Scanner(System.in);

    public UI(WorkoutService service) {
        this.service = service;
    }

    public void run() {
        while (true) {
            System.out.println("\n1. Add workout\n2. Show all (sorted)\n3. Show workouts longer than...\n4. Total minutes in a month\n0. Exit");
            String option = scanner.nextLine().trim();
            try {
                switch (option) {
                    case "1" -> addWorkout();
                    case "2" -> print(service.getAllSorted());
                    case "3" -> print(service.filterLongerThan(readInt("Minimum minutes: ")));
                    case "4" -> {
                        String month = readString("Month (yyyy.mm): ");
                        System.out.println("Total: " + service.totalMinutesInMonth(month) + " minutes");
                    }
                    case "0" -> { return; }
                    default -> System.out.println("Invalid option!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addWorkout() {
        String type = readString("Type (running / swimming): ").toLowerCase();
        String date = readString("Date (yyyy.mm.dd): ");
        int duration = readInt("Duration (minutes): ");
        if (type.equals("running")) {
            double distance = Double.parseDouble(readString("Distance (km): "));
            service.addRunning(date, duration, distance);
        } else if (type.equals("swimming")) {
            int laps = readInt("Laps: ");
            int pool = readInt("Pool length (m): ");
            service.addSwimming(date, duration, laps, pool);
        } else {
            System.out.println("Unknown type!");
            return;
        }
        System.out.println("Added.");
    }

    private String readString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private int readInt(String message) {
        return Integer.parseInt(readString(message));
    }

    private void print(List<Workout> list) {
        if (list.isEmpty()) System.out.println("Nothing to show.");
        for (Workout w : list) System.out.println(w);
    }
}
