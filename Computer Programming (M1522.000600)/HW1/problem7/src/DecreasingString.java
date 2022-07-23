public class DecreasingString {
    public static void printLongestDecreasingSubstringLength(String inputString) {
		// DO NOT change the skeleton code.
		// You can add codes anywhere you want.
        int size = inputString.length();
        int[] arr = new int[size];
        // memset array from 1
        for(int i = 0; i < size; i++){
            arr[i] = 1;
        }
        // for from the last to begin O (n)
        for(int i = size - 1; i > 0; i--){
            char current = inputString.charAt(i);
            char previous = inputString.charAt(i-1);
            if(previous > current){
                arr[i-1] = arr[i] + 1;
            }
            else{
                arr[i-1] = 1;
            }
        }
        // find the largest value of array ~ answer
        int res = 1;
        for (int i = 0; i < size; i++) {
            if (arr[i] >= res) res = arr[i];
        }
        System.out.println(res);
    }
}
