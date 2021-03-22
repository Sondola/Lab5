package comparators;

import data.Car;

import java.util.Comparator;

public class CarComparator implements Comparator<Car> {
    public int compare(Car car1, Car car2) {
        if (car1.getName().length() > car2.getName().length()) return 1;
        else if (car1.getName().equals(car2.getName())) return 0;
        else return -1;
    }
}
