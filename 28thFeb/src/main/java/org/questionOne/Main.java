package org.questionOne;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // List of owners with no car
        List<String> ownersWithNoCars = CarRepository.getOwners()
                .stream()
                .filter(owner -> owner.getCars() == null || owner.getCars().isEmpty())
                .map(Owner::getName)
                .collect(Collectors.toList());

        System.out.println(ownersWithNoCars);

        // Name of owners with number of cars he has
        Map<String, Integer> ownerCarCount = CarRepository.getOwners()
                .stream()
                .collect(Collectors.toMap(
                        Owner::getName,
                        owner -> owner.getCars() == null ? 0 : owner.getCars().size()
                ));

        System.out.println(ownerCarCount);

        // List of cars not owned by anyone

        // Step 1: Get all owned cars
        Set<Car> ownedCars = CarRepository.getOwners()
                .stream()
                .filter(owner -> owner.getCars() != null)
                .flatMap(owner -> owner.getCars().stream())
                .collect(Collectors.toSet());

        // Step 2: Filter cars not in ownedCars
        List<Car> unownedCars = CarRepository.getCars()
                .stream()
                .filter(car -> !ownedCars.contains(car))
                .collect(Collectors.toList());

        System.out.println(unownedCars);
    }
}