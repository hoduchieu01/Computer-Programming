## Chapter 2: Primitive Data and Definite Loops

### This file contains my implementation for all exercises of the Chapter 2: Primitive Data and Definite Loops.

#### BJP5 Exercise 2.1: displacement
```
double s0 = 12.0;
double v0 = 3.5;
double a = 9.8;
double t = 10;

System.out.println(s0 + v0*t + 0.5 * a * t *t);
```
#### BJP5 Exercise 2.2: loopSquares
```
for(int i = 1; i <= 10; i++){
    System.out.print(i * i + " ");
}
```
#### BJP5 Exercise 2.3: fibonacci
```
int x = 0;
int y = 1;
for (int i = 1; i <= 12; i++){
    
    int z = x + y;
    x = y;
    y = z;
    System.out.print(x + " ");
}
```
#### BJP5 Exercise 2.4: starSquare
```
for(int i = 0; i < 4; i++){
    for(int j = 0; j < 5; j++){
        System.out.print("*");
    }
    System.out.println();
}
```
#### BJP5 Exercise 2.5: starTriangle
```
for(int i = 1; i <= 5; i++){
    for(int j = 0; j < i; j++){
        System.out.print("*");
    }
    System.out.println();
}
```
#### BJP5 Exercise 2.6: numberTriangle
```
for(int i = 1; i <= 7; i++){
    for(int j = 1; j <= i; j ++){
        System.out.print(i);
    }
    System.out.println();
}
```
#### BJP5 Exercise 2.7: spacedNumbers
```
for(int i = 1; i <= 5; i ++){
    for(int j = 5 - i; j >= 1; j--){
        System.out.print(" ");
    }
    System.out.println(i);
}
```
#### BJP5 Exercise 2.8: spacesAndNumbers
```
for(int i = 1; i <= 5; i++){
    for(int j = 5 - i; j >= 1; j --){
        System.out.print(" ");
    }
    for(int j = 1; j <= i; j++){
    System.out.print(i);
    }
    System.out.println();
}
```
#### BJP5 Exercise 2.9: waveNumbers40
```
for(int i = 0; i < 40; i++) System.out.print("-");
System.out.println();
for(int i = 0; i < 10; i++) System.out.print("_-^-");

System.out.println();
for(int i = 1; i <= 9; i++)
    for(int j=0; j < 2;j++) System.out.print(i);
System.out.print("00");
              

for(int i = 1; i <= 9; i++)
    for(int j=0; j < 2;j++) System.out.print(i);
System.out.println("00");

for(int i = 0; i < 40; i++) System.out.print("-");
```
#### BJP5 Exercise 2.10: numbersOutput60
```
for(int i = 0; i < 6; i++) {
    for(int j = 0; j < 9; j++)
        System.out.print(" ");
    System.out.print("|");
}

System.out.println();

for(int i = 1; i <= 60; i++)
    System.out.print(i%10);
```
#### BJP5 Exercise 2.11: numbersOutputConstant
```
public class NumbersOutput {
    public static final int repetitions = 6;
    public static final int range = 10;
	
	public static void main(String[] args) {
		for(int k = 1; k <= repetitions; k++) {
			for(int i = 1; i < range; i++) 
				System.out.print(" ");
				System.out.print("|");
			}
			System.out.println();
			for(int j = 1; j <= (range * repetitions); j++) 
				System.out.print(j%range);
	}
}
```
#### BJP5 Exercise 2.12: nestedNumbers
```
for (int i = 1; i <= 3; i++) {
    for (int j = 0; j < 10; j++) {
        for (int k = 1; k <= 3; k++) {
            System.out.print(j);
        }
    }
    System.out.println();
}
```
#### BJP5 Exercise 2.13: nestedNumbers2
```
for (int i = 1; i <= 5; i++) {
    for (int j = 9; j >= 0; j--) {
        for (int k = 1; k <= 5; k++) {
            System.out.print(j);
        }
    }
    System.out.println();
}
```
#### BJP5 Exercise 2.14: nestedNumbers3
```
for(int i = 1; i <= 4; i++) {
    for(int j = 9; j >= 1; j--) {
        for(int k = j - 1; k >= 0; k--)
            System.out.print(j);
    }
    System.out.println();
}
```
#### BJP5 Exercise 2.15: printDesign
```
public static void printDesign() {
    // your code goes here
    for(int i = 1; i <= 5; i ++){
        for(int j = 6 - i; j >= 1; j--) System.out.print("-");
        for(int k = 1; k <= i * 2 -1; k ++) System.out.print(i * 2 - 1);
        for(int j = 6 - i; j >= 1; j--) System.out.print("-");
     System.out.println();
    }  
}
```
#### BJP5 Exercise 2.16: SlashFigure
```
public class SlashFigure {
	public static void main(String[] args) {
		for(int i = 1; i <= 6; i++) {
            for(int j = 0; j < 2 * i - 2; j++)
                System.out.print("\\");
            for(int j = 0; j < -4 * i + 26; j++)
                System.out.print("!");
            for(int j = 0; j < 2 * i - 2; j++)
                System.out.print("/");
            System.out.println();
	  }
	}
}
```
#### BJP5 Exercise 2.17: SlashFigure2
```
public class SlashFigure2 {
    public static final int HEIGHT = 4;
    public static void main(String[] args) {
        for (int i = 1; i <= HEIGHT; i++) {
            for (int j = 1; j <= 2 * i - 2; j++) {
                System.out.print("\\");
            }
            
            for (int j = 1; j <= 4 * HEIGHT - 4 * i + 2; j++) {
                System.out.print("!");
            }
            
            for (int j = 1; j <= 2 * i - 2; j++) {
                System.out.print("/");
            }
            
            System.out.println();
        }
    }
}
```
#### BJP5 Exercise 2.18: pseudocodeWindow
```
    System.out.println("+===+===+");
		System.out.println("|   |   |");
		System.out.println("|   |   |");
		System.out.println("|   |   |");
		System.out.println("+===+===+");
		System.out.println("|   |   |");
		System.out.println("|   |   |");
		System.out.println("|   |   |");
		System.out.println("+===+===+");
```
#### BJP5 Exercise 2.19: Window
```
public class Window {
public static final int R = 7;
    
	public static void main(String[] args) {
        
		System.out.print("+");
		for(int i = 1; i <= 2; i++) {
			for(int j = 1; j <= R; j++)
				System.out.print("=");
			System.out.print("+");
		}
			System.out.println();
				for(int k = 1; k <= R; k++) { 
				   for(int i = 1; i <= 3; i++) {
					   System.out.print("|");
					   for(int j = 1; j <= R; j++)
					   System.out.print(" ");
				   }
				System.out.println();
		}
				System.out.print("+");
				for(int i = 1; i <= 2; i++) {
					for(int j = 1; j <= R; j++)
						System.out.print("=");
					System.out.print("+");
				}
				window();
	}
	public static void window() {
			System.out.println();
				for(int k = 1; k <= R; k++) { 
				   for(int i = 1; i <= 3; i++) {
					   System.out.print("|");
					   for(int j = 1; j <= R; j++)
					   System.out.print(" ");
				   }
				System.out.println();
		}
				System.out.print("+");
				for(int i = 1; i <= 2; i++) {
					for(int j = 1; j <= R; j++)
						System.out.print("=");
					System.out.print("+");
	}
	}
}
```
#### BJP5 Exercise 2.20: StarFigure
```
public class StarFigure{
   public static void main(String[] args){
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 16 - i * 4; j ++){
                System.out.print("/");
            }
            for (int j = 0; j < i * 8; j++)
                System.out.print("*");
            for (int j = 0; j < 16 - i * 4; j ++){
                System.out.print("\\");
            }
            System.out.println();
        }   
    }
}
```
#### BJP5 Exercise 2.21: StarFigure2
```
public class StarFigure2 {
    public static final int R = 7;
    
	public static void main(String[] args) {
		for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= (4*R) - 4 * i; j++) {
                System.out.print("/");
            
		}
		for (int j = 1; j <= 8 * i - 8; j++) {
            System.out.print("*");
		}
		for (int j = 1; j <= (4*R) - 4 * i; j++) {
            System.out.print("\\");
	}
		System.out.println();
	}
}
}
```
#### BJP5 Exercise 2.22: DollarFigure
```
public class DollarFigure {
	public static void main(String[] args) {
		for(int i = 1; i <= 7; i++) {
            for(int j = 0; j < 2 * i - 2; j++)
                System.out.print("*");
            for(int j = 0; j < -i + 8; j++)
                System.out.print("$");
            for(int j = 0; j < -2 * i + 16; j++)
                System.out.print("*");
            for(int j = 0; j < -i + 8; j++)
                System.out.print("$");
            for(int j = 0; j < 2 * i - 2; j++)
                System.out.print("*");
            System.out.println();
	}
	}
}
```
#### BJP5 Exercise 2.23: DollarFigure2
```
public class DollarFigure2 {
    public static final int R= 5;
    
	public static void main(String[] args) {
		for(int i = 1; i <= R; i++) {
            for(int j = 0; j < 2 * i - 2; j++)
                System.out.print("*");
            for(int j = 0; j <= -i + R; j++)
                System.out.print("$");
            for(int j = 0; j <= -2 * i + (R*2+1); j++)
                System.out.print("*");
            for(int j = 0; j <= -i + R; j++)
                System.out.print("$");
            for(int j = 0; j < 2 * i - 2; j++)
                System.out.print("*");
            System.out.println();

	}
	}
}
```
#### BJP5 Exercise 2.24: TwoRectangles
```
public class TwoRectangles {
    // Class constants WIDTH and HEIGHT
    public static final int WIDTH = 7;
    public static final int HEIGHT = 4;
    public static void main(String[] args) {
        for(int i = 0; i < HEIGHT * 2; i ++){
            if(i >= HEIGHT){
                for(int j = 0; j < WIDTH; j++){
                    System.out.print(" ");
                }
            }
            for(int j = 0; j < WIDTH; j ++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
```
