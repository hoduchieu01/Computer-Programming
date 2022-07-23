public class NumberCounter {

    public static void countNumbers(String str0, String str1, String str2) {
		// DO NOT change the skeleton code.
		// You can add codes anywhere you want.
        int input_number0;
        int input_number1;
        int input_number2;
        input_number0 = StringtoInt(str0);
        input_number1 = StringtoInt(str1);
        input_number2 = StringtoInt(str2);
        int multiply = 1;
        multiply = input_number0 * input_number1 * input_number2;
        // Test String to Int Multiply
        // System.out.println(multiply);
        String str = String.valueOf(multiply);
        // System.out.println(str);

        // counting the frequency of each number (0~9) from the multiply result.
        int[] character_asc = new int[58];
        for(int i = 0; i <= 57; i++) character_asc[i] = 0;
        for (int i = 0; i < str.length(); i++) {
            // check the ASCII from 48 to 57 (0~9)
            for (int j = 48; j <= 57; j++) {
                char c = str.charAt(i);
                if (c == (char) (j)) character_asc[j]++;
            }
        }
        for(int i = 48; i <= 57; i++){
            if(character_asc[i] > 0){
                System.out.printf("%c: %d times\n", (char) (i), character_asc[i]);
            }
        }
        System.out.print("length: " + str.length());
    }

    // Convert String to Int
    static int StringtoInt(String str){
        int input;
        try{
            input = Integer.parseInt(str);
        }
        catch(NumberFormatException e){
            input = 0;
        }
        return input;
    }
    private static void printNumberCount(int number, int count) {
        System.out.printf("%d: %d times\n", number, count);
    }
}
