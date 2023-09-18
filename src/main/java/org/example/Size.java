package org.example;

import java.util.Comparator;

public class Size {
    private final int vertical;
    private final int horizontal;

    public Size(String coordinates) {
        this.vertical = normalizeCoordinates(coordinates);
        this.horizontal = normalizeCoordinates(coordinates);
    }

    private int normalizeCoordinates(String coordinates) {
        int result;
        var splittedArray = coordinates.split("x");


        try {
            if (splittedArray.length < 2){
                throw new CoordinatesValidException("Вы ввели меньше двух значений");
            }
        }
        catch (CoordinatesValidException e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        // Проверка на одинаковые значения
        try {
            if (splittedArray[0] != splittedArray[1]){
                throw new CoordinatesValidException("Неравные значения");
            }
        }
        catch (CoordinatesValidException e){
            System.out.println(e.getMessage());
            System.out.println("Нормализую значения...");
        }

        result = Integer.parseInt(splittedArray[0]);

        // Проверка на допустимые значения
        try {
                int coordInt = Integer.parseInt(splittedArray[0]);
                if (coordInt < 10 || coordInt > 1000){
                    throw new CoordinatesValidException("Недопустимое значение координат");
                }

        }
        catch (CoordinatesValidException e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        result = Integer.parseInt(coordinates.split("x")[0]);


        return result;
    }

    @Override
    public String toString() {
        return getVertical()+"x"+getHorizontal();
    }

    public int getVertical() {
        return vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }
}
