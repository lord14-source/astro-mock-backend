package com.astromock.astromock.services;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NumerologyService {

    private int reduce(int num) {
        while (num > 9 && num != 11 && num != 22) {
            num = String.valueOf(num)
                    .chars()
                    .map(Character::getNumericValue)
                    .sum();
        }
        return num;
    }

    public int calculateLifePath(String dob) {
        String digits = dob.replaceAll("-", "");
        int sum = digits.chars()
                .map(Character::getNumericValue)
                .sum();
        return reduce(sum);
    }

    public int calculateDestiny(String name) {
        Map<Character, Integer> values = Map.ofEntries(
                Map.entry('A',1), Map.entry('B',2), Map.entry('C',3),
                Map.entry('D',4), Map.entry('E',5), Map.entry('F',6),
                Map.entry('G',7), Map.entry('H',8), Map.entry('I',9),
                Map.entry('J',1), Map.entry('K',2), Map.entry('L',3),
                Map.entry('M',4), Map.entry('N',5), Map.entry('O',6),
                Map.entry('P',7), Map.entry('Q',8), Map.entry('R',9),
                Map.entry('S',1), Map.entry('T',2), Map.entry('U',3),
                Map.entry('V',4), Map.entry('W',5), Map.entry('X',6),
                Map.entry('Y',7), Map.entry('Z',8)
        );

        int total = name.toUpperCase()
                .replaceAll("[^A-Z]", "")
                .chars()
                .map(c -> values.getOrDefault((char)c, 0))
                .sum();

        return reduce(total);
    }

    public String getMeaning(int number) {
        return switch (number) {
            case 1 -> "Leader, Independent, Ambitious";
            case 2 -> "Diplomatic, Emotional, Cooperative";
            case 3 -> "Creative, Expressive, Social";
            case 4 -> "Hardworking, Practical, Disciplined";
            case 5 -> "Adventurous, Energetic, Freedom Lover";
            case 6 -> "Responsible, Caring, Family Oriented";
            case 7 -> "Spiritual, Analytical, Deep Thinker";
            case 8 -> "Powerful, Business Minded, Confident";
            case 9 -> "Compassionate, Humanitarian, Wise";
            case 11 -> "Master Intuitive, Visionary";
            case 22 -> "Master Builder, Great Achiever";
            default -> "Unknown";
        };
    }
}