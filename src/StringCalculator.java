import java.util.*;

class StringCalculator {

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    int add(String input) {
        List<String> delimiters = new ArrayList<>();
        String[] del_temp = {",", "\n"};

        delimiters.add(del_temp[0]);
        delimiters.add(del_temp[1]);

        if (Objects.equals(input, "")) return 0;

        else if (input.startsWith("//")) {
            if (Objects.equals("[", input.substring(2,3))) {
                for (int i = 0; i < input.length()-2; i++) {
                    if (Objects.equals(input.substring(i, i+3), "]\\n")) {
                        del_temp = input.substring(3, i).split("]\\Q[");
                        Collections.addAll(delimiters, del_temp);
                        input = input.substring(i+3);
                    }
                }
            }
            else {
                for (int i = 0; i < input.length()-1; i++) {
                    if (Objects.equals(input.substring(i, i+2), "\\n")) {
                        delimiters.add(input.substring(2, i));
                        input = input.substring(i+2);
                    }
                }
            }
            Collections.sort(delimiters);
            Collections.reverse(delimiters);
        }

//        System.out.println("delimiters: " + delimiters); //выпилить
        System.out.println(input); //выпилить


        for (String delimiter : delimiters) {
            input = input.replaceAll("\\Q" + delimiter + "\\E", "*");
        }

        if (input.contains("**")) throw new IllegalArgumentException("Two delimiters in a row");

        int result = 0;
        String[] nums = input.split("\\Q*");
        List<String> negative_nums = new ArrayList<>();

        for (String num : nums) {
            if (isNumeric(num)) {
                if (Integer.parseInt(num) < 0) negative_nums.add(num);
                else if (Integer.parseInt(num) <= 1000) result += Integer.parseInt(num);
            } else throw new IllegalArgumentException("Wrong delimiter");
        }

        if (negative_nums.size()!=0) throw new IllegalArgumentException("negative nums: " + negative_nums);

        return result;

        //          //[++][+]\n1++2+3
        //          //[{}][{123}][\n123]\n123,2{}123\n1233{123}123

    }
}



class Main {
    public static void main(String[] args) {
        System.out.print("Enter string: "); //выпилить
        Scanner read = new Scanner(System.in);
        String str = read.nextLine();
        StringCalculator calc = new StringCalculator();
        System.out.println(calc.add(str));
    }
}