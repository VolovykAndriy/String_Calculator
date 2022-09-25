import java.util.*;

class StringCalculator {

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static int add(String numbers) {
        List<String> delimiters = new ArrayList<>();

        delimiters.add(",");
        delimiters.add("\\n");

        if (numbers.isEmpty()) return 0;
        else if (numbers.startsWith("//")) {
            if (Objects.equals('[', numbers.charAt(2))) {
                Collections.addAll(delimiters, numbers.substring(3, numbers.indexOf("]\\n")).split("]\\Q["));
                numbers = numbers.substring(numbers.indexOf("]\\n") + 3);
            } else {
                delimiters.add((numbers.substring(2, numbers.indexOf("\\n"))));
                numbers = numbers.substring(numbers.indexOf("\\n") + 2);
            }
            Collections.sort(delimiters);
            Collections.reverse(delimiters);
        }

        for (String delimiter : delimiters) {
            numbers = numbers.replaceAll("\\Q" + delimiter + "\\E", ",");
        }

        if (numbers.contains(",,")) throw new IllegalArgumentException("Two or more delimiters together");

        int result = 0;
        String[] numbersList = numbers.split(",");
        List<String> negativeNums = new ArrayList<>();

        for (String num : numbersList) {
            if (isNumeric(num)) {
                if (Integer.parseInt(num) < 0) negativeNums.add(num);
                else if (Integer.parseInt(num) <= 1000) result += Integer.parseInt(num);
            } else throw new IllegalArgumentException("Not allowed delimiter");
        }

        if (negativeNums.size() != 0) {
            throw new IllegalArgumentException("Negative numbers: " + negativeNums);
        }

        return result;
    }
}


class Main {
    public static void main(String[] args) {
        System.out.print("Enter string: ");
        Scanner read = new Scanner(System.in);
        String inputNumbers = read.nextLine();
        System.out.println(StringCalculator.add(inputNumbers));
    }
}