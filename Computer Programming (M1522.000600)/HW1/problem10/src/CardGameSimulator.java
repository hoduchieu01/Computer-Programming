public class CardGameSimulator {
	private static final Player[] players = new Player[2];

	public static void simulateCardGame(String inputA, String inputB) {
		// DO NOT change the skeleton code.
		// You can add codes anywhere you want.
		int[] cardAO = new int[100];
		int[] cardAX = new int[100];
		int[] cardBO = new int[100];
		int[] cardBX = new int[100];

		// memset array
		for (int i = 48; i <= 57; i++) {
			cardAO[i] = 0;
			cardAX[i] = 0;
			cardBO[i] = 0;
			cardBX[i] = 0;
		}
		// Scanning string and find A0 and AX
		for (int i = 1; i < inputA.length(); i++) {
			char characterA = inputA.charAt(i);
			int numberEncodedA = inputA.charAt(i - 1);
			if (characterA == 'O' && i % 3 == 1) {
				cardAO[numberEncodedA] = 1;
			}
			if (characterA == 'X' && i % 3 == 1) {
				cardAX[numberEncodedA] = 1;
			}
		}

		// Scanning string and find B0 and BX
		for (int i = 1; i < inputB.length(); i++) {
			char characterB = inputB.charAt(i);
			int numberEncodedB = inputB.charAt(i - 1);
			if (characterB == 'O' && i % 3 == 1) {
				cardBO[numberEncodedB] = 1;
			}
			if (characterB == 'X' && i % 3 == 1) {
				cardBX[numberEncodedB] = 1;
			}
		}

		// Solving the first section
        /* Problem Statement: The game starts with Player A. Player A uses the card with the largest number.
        If there are two cards (O, X) with the largest number, Player A will choose the card with shape X among the two cards.
        */
		int firstCardNumber = 0;
		int currentCardNumber = 0;
		char currentCardType = 'X';
		for (int i = 57; i >= 48; i--) {
			if (cardAX[i] == 1 && i >= firstCardNumber) {
				firstCardNumber = i; // Find the firstCard of player
				currentCardNumber = firstCardNumber;
				currentCardType = 'X';
				cardAX[i] = 0;
			}
			else if (cardAO[i] == 1 && i >= firstCardNumber) {
				firstCardNumber = i; // Find the firstCard of player
				currentCardNumber = firstCardNumber;
				currentCardType = 'O';
				cardAO[i] = 0;
			}
		}
		System.out.println("Player A: " + (char) currentCardNumber + currentCardType);

		// Solving the second section
		boolean isWinnerFound = false;
		while(!isWinnerFound) {

			// Player B 's Turn
			if (cardBX[currentCardNumber] == 1 || cardBO[currentCardNumber] == 1) {
				if (currentCardType == 'X') {
					cardBO[currentCardNumber] = 0;
					currentCardType = 'O';
				}
				else if (currentCardType == 'O') {
					cardBX[currentCardNumber] = 0;
					currentCardType = 'X';
				}
			} else if (cardBX[currentCardNumber] == 0 && cardBO[currentCardNumber] == 0) {
				if (currentCardType == 'X') {
					int maxValueOfArray = 0;
					for (int j = 57; j >= 48; j--) {
						if (cardBX[j] == 1 && j >= maxValueOfArray) {
							maxValueOfArray = j;
						}
					}
					if (maxValueOfArray != 0) {
						currentCardNumber = maxValueOfArray;
						cardBX[currentCardNumber] = 0;
					}
					else {
						isWinnerFound = true;
					}
				} else if (currentCardType == 'O') {
					int maxValueOfArray = 0;
					for (int j = 57; j >= 48; j--) {
						if (cardBO[j] == 1 && j >= maxValueOfArray) {
							maxValueOfArray = j;
						}
					}
					if (maxValueOfArray != 0) {
						currentCardNumber = maxValueOfArray;
						cardBO[currentCardNumber] = 0;
					}
					else {
						isWinnerFound = true;
					}
				}
			}
			if (isWinnerFound == true) {
				System.out.println("Player A wins the game!");
				break;
			}
			else System.out.println("Player B: " + (char) currentCardNumber + currentCardType);

			// Player A 's Turn
			if (cardAX[currentCardNumber] == 1 || cardAO[currentCardNumber] == 1) {
				if (currentCardType == 'X') {
					cardAO[currentCardNumber] = 0;
					currentCardType = 'O';
				}
				else if (currentCardType == 'O') {
					cardAX[currentCardNumber] = 0;
					currentCardType = 'X';
				}
			} else if (cardAX[currentCardNumber] == 0 && cardAO[currentCardNumber] == 0) {
				if (currentCardType == 'X') {
					int maxValueOfArray = 0;
					for (int j = 57; j >= 48; j--) {
						if (cardAX[j] == 1 && j >= maxValueOfArray) {
							maxValueOfArray = j;
						}
					}
					if (maxValueOfArray != 0) {
						currentCardNumber = maxValueOfArray;
						cardAX[currentCardNumber] = 0;
					}
					else {
						isWinnerFound = true;
					}
				}
				else if (currentCardType == 'O') {
					int maxValueOfArray = 0;
					for (int j = 57; j >= 48; j--) {
						if (cardAO[j] == 1 && j >= maxValueOfArray) {
							maxValueOfArray = j;
						}
					}
					if (maxValueOfArray != 0) {
						currentCardNumber = maxValueOfArray;
						cardAO[currentCardNumber] = 0;
					}
					else {
						isWinnerFound = true;
					}
				}
			}
			if (isWinnerFound == true){
				System.out.println("Player B wins the game!");
				break;
			}
			else System.out.println("Player A: " + (char) currentCardNumber + currentCardType);
		}
	}

	private static void printWinMessage(Player player) {
		System.out.printf("Player %s wins the game!\n", player);
	}
}


class Player {
	private String name;
	private Card[] deck;

	public void playCard(Card card) {
		System.out.printf("Player %s: %s\n", name, card);
	}

	@Override
	public String toString() {
		return name;
	}
}


class Card {
	private int number;
	private char shape;

	@Override
	public String toString() {
		return "" + number + shape;
	}
}
