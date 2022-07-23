public class CharacterCounter {
    public static void countCharacter(String str) {
		// DO NOT change the skeleton code.
		// You can add codes anywhere you want.
        int[] character_asc = new int[200];
        for (int j = 65; j <= 122; j++)  character_asc[j] = 0; // memset array

        for (int i = 0; i < str.length(); i++){
            for (int j = 65; j <= 122; j++) {
                char c = str.charAt(i);
                if (c == (char) j) character_asc[j]++;
            }
        }
        // print
        for(int j = 65; j <= 90; j++){
            if(character_asc[j] > 0 && character_asc[j + 32] > 0){
                System.out.printf("%c: %d times, %c: %d times\n", (char)j, character_asc[j], (char) j + 32, character_asc[j+32]);
            }
            else if(character_asc[j] == 0 && character_asc[j + 32] > 0){
                System.out.printf("%c: %d times\n", (char) j + 32, character_asc[j+32]);
            }
            else if(character_asc[j] > 0 && character_asc[j + 32] == 0){
                System.out.printf("%c: %d times\n", (char)j, character_asc[j]);
            }
        }
    }

    private static void printCount(char character, int count) {
        System.out.printf("%c: %d times\n", character, count);
    }
}
