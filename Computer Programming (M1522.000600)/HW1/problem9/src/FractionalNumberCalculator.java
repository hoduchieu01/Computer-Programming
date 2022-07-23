public class FractionalNumberCalculator {
	// Converting String to Int
	static int StringtoInt(String str){
		int input;
		try{
			input = Integer.parseInt(str);
		}
		catch(NumberFormatException e){
			input = 1;
		}
		return input;
	}
	// Calculating GCD
	public static int GCD(int a, int b){
		if (b == 0)
			return a;
		return GCD(b, a % b);
	}
	public static void printCalculationResult(String equation) {
		// DO NOT change the skeleton code.
		// You can add codes anywhere you want.
		int firstNumerator;
		int firstDenominator;
		int secondNumerator;
		int secondDenominator;
		if (equation.length() == 0) return;

		// Finding an operator in the equation
		int posOperator = 0;
		char mainOperator = ' ';
		for (int i = 0; i < equation.length(); i++) {
			char c = equation.charAt(i);
			if (c == '+' || c == '-' || c == '*' || (c == '/' && equation.charAt(i - 1) == ' ' && equation.charAt(i + 1) == ' ')) {
				posOperator = i;
				mainOperator = c;
			}
		}

		// Pre-processing the first number
		boolean checkFractionFirst = false;
		int pos = 0;
		for(int i = 0; i < posOperator - 1; i ++) {
			char c1 = equation.charAt(i);
			if (c1 == '/') {
				checkFractionFirst = true;
				pos = i;
			}
		}

		if(checkFractionFirst) {
			String afirstnumber = equation.substring(0, pos);
			String bfirstnumber = equation.substring(pos + 1, posOperator - 1);
			firstNumerator = StringtoInt(afirstnumber);
			firstDenominator = StringtoInt(bfirstnumber);
		}
		else{
			String firstnumber = equation.substring(0, posOperator - 1);
			firstNumerator = StringtoInt(firstnumber);
			firstDenominator = 1;
		}

		// Pre-processing the second number
		boolean checkFractionSecond = false;
		int pos2 = 0;
		for(int i = posOperator + 1; i < equation.length(); i ++) {
			char c2 = equation.charAt(i);
			if (c2 == '/') {
				checkFractionSecond = true;
				pos2 = i;
			}
		}
		if(checkFractionSecond) {
			String asecondnumber = equation.substring(posOperator + 2, pos2);
			String bsecondnumber = equation.substring(pos2 + 1, equation.length());
			secondNumerator = StringtoInt(asecondnumber);
			secondDenominator = StringtoInt(bsecondnumber);
		}
		else{
			String secondnumber = equation.substring(posOperator + 2, equation.length());
			secondNumerator = StringtoInt(secondnumber);
			secondDenominator = 1;
		}

		// Calculating the equation
		int finalNumerator = 1;
		int finalDenominator = 1;
		if(mainOperator == '+'){
			finalNumerator = firstNumerator * secondDenominator + secondNumerator * firstDenominator;
			finalDenominator = firstDenominator * secondDenominator;
		}
		if(mainOperator == '-'){
			finalNumerator = firstNumerator * secondDenominator - secondNumerator * firstDenominator;
			finalDenominator = firstDenominator * secondDenominator;
		}
		if(mainOperator == '*'){
			finalNumerator = firstNumerator * secondNumerator;
			finalDenominator = firstDenominator * secondDenominator;
		}
		if(mainOperator == '/'){
			finalNumerator = firstNumerator * secondDenominator;
			finalDenominator = firstDenominator * secondNumerator;
		}

		// Fractional Number & Get the Final Result
		int d = GCD(finalNumerator, finalDenominator);
		finalNumerator = finalNumerator / d;
		finalDenominator = finalDenominator / d;
		// if the result is negative
		if(finalDenominator < 0){
			finalDenominator = (-1) * finalDenominator;
			finalNumerator = (-1) * finalNumerator;
		}
		// if the result is a natural number
		if(finalDenominator == 1)
			System.out.println(finalNumerator);
		else
			System.out.println(finalNumerator + "/" + finalDenominator);
	}
}

class FractionalNumber {
	private int numerator;
	private int denominator;
}
