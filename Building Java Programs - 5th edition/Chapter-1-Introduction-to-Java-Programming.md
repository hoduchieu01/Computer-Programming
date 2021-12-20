## Chapter 1: Introduction to Java Programming

### This file contains my implementation for all exercises of the Chapter 1: Introduction to Java Programming.

#### BJP5 Exercise 1.1: Stewie
```
public class Stewie{
    public static void main(String[] args){
    System.out.println("//////////////////////");
    System.out.println("|| Victory is mine! ||");
    System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
    }
}
```
#### BJP5 Exercise 1.2: Spikey
```
public class Spikey{
    public static void main(String args[]){
        System.out.println("  \\/  ");
        System.out.println(" \\\\// ");
        System.out.println("\\\\\\///");
        System.out.println("///\\\\\\");
        System.out.println(" //\\\\ ");
        System.out.println("  /\\  ");
    }
}
```
#### BJP5 Exercise 1.3: WellFormed
```
public class WellFormed{
    public static void main(String args[]){
        System.out.println("A well-formed Java program has\na main method with { and }\nbraces.\n");
        System.out.println("A System.out.println statement\nhas ( and ) and usually a");
        System.out.println("String that starts and ends");
        System.out.println("with a \" character.");
        System.out.println("(But we type \\\" instead!)");
    } 
}
```
#### BJP5 Exercise 1.4: Difference
```
public static class Difference{
    public static void main(String[] args){
        System.out.println("What is the difference between");
        System.out.println("a ' and a \"?  Or between a \" and a \\\"?\n");
        System.out.println("One is what we see when we're typing our program.");
        System.out.println("The other is what appears on the \"console.\"");
    }
}
```
#### BJP5 Exercise 1.5: MuchBetter
```
public class MuchBetter{
    public static void main(String[] args){
        System.out.println("A \"quoted\" String is");
        System.out.println("'much' better if you learn");
        System.out.println("the rules of \"escape sequences.\"");
        System.out.println("Also, \"\" represents an empty String.");
        System.out.println("Don't forget: use \\\" instead of \" !");
        System.out.println("'' is not the same as \"");                      
    }
}
```
#### BJP5 Exercise 1.6: Meta
```
public class Meta{
    public static void main(String[] args){
        System.out.println("public class Hello {");
        System.out.println("    public static void main(String[] args) {");
        System.out.println("        System.out.println(\"Hello, world!\");");
        System.out.println("    }");
        System.out.println("}");
    }
}
```
#### BJP5 Exercise 1.7: Mantra
```
public class Mantra{
    public static void main(String[] args){
            print();
            print();
    }
    public static void print(){
        System.out.println("There's one thing every coder must understand:");
        System.out.println("The System.out.println command.\n");
    }
}
```
#### BJP5 Exercise 1.8: Stewie2
```
public class Stewie2{
    public static void main(String[] args){
        System.out.println("//////////////////////");
        for(int i = 0; i <= 4; i++){
            doubleLine();
        }
    }
    public static void doubleLine(){
        textLine();
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
    }
    public static void textLine(){
        System.out.println("|| Victory is mine! ||");
    }
}
```
#### BJP5 Exercise 1.9: Egg
```
public class Egg {
    public static void main(String[] args) {
        System.out.println("  _______");
        System.out.println(" /       \\");
        System.out.println("/         \\");
        System.out.println("-\"-'-\"-'-\"-");
        System.out.println("\\         /");
        System.out.println(" \\_______/");
    }
}
```
#### BJP5 Exercise 1.10
```
public class Egg2{
    public static void main(String[] args){
        printTop();
        printBot();
        printMiddle();
        printTop();
        printBot();
        printMiddle();
        printBot();
        printTop();
        printMiddle();
        printBot();
    }
    public static void printTop(){
        System.out.println("  _______  ");
        System.out.println(" /       \\ ");
        System.out.println("/         \\");                   
    }
    public static void printMiddle(){
        System.out.println("-\"-'-\"-'-\"-");
    }
    public static void printBot(){
        System.out.println("\\         /");
        System.out.println(" \\_______/");

    }
}
```
#### BJP5 Exercise 1.11: TwoRockets
```
public class TwoRockets {
    public static void main(String[] args) {
        printTriangle();
        printSquare();
        printUSA();
        printSquare();
        printTriangle();
    }                                        
    public static void printTriangle() {
        System.out.println("   /\\       /\\");
        System.out.println("  /  \\     /  \\");
        System.out.println(" /    \\   /    \\");
    }
    public static void printSquare() {
        System.out.println("+------+ +------+");
        System.out.println("|      | |      |");
        System.out.println("|      | |      |");
        System.out.println("+------+ +------+");
    }
    public static void printUSA() {
        System.out.println("|United| |United|");
        System.out.println("|States| |States|");
    }
}
```
#### BJP5 Exercise 1.12: FightSong
```
public class FightSong {
    public static void main(String[] args) {
        printGo();
        printNewLine();
        printParagraph();
        printNewLine();
        printParagraph();
        printNewLine();
        printGo();
    }                                                            
    public static void printGo() {
        System.out.println("Go, team, go!");
        System.out.println("You can do it.");
    }
    public static void printWest() {
        System.out.println("You're the best,");
        System.out.println("In the West.");
    }
    public static void printNewLine() {
        System.out.println();
    }
    public static void printParagraph() {
        printGo();
        printWest();
        printGo();
    }
}
```
#### BJP5 Exercise 1.13: StarFigures
```
public class StarFigures {
    public static void main(String[] args) {
        printLines();
        printX();
        System.out.println();
        printLines();
        printX();
        printLines();
        System.out.println();
        printThreeStars();
        printLines();
        printX();
    }
    public static void printLines() {
        System.out.println("*****");
        System.out.println("*****");
    }
    public static void printX() {
        System.out.println(" * *");
        System.out.println("  *");
        System.out.println(" * *");
    }
    public static void printThreeStars() {
        System.out.println("  *");
        System.out.println("  *");
        System.out.println("  *");
    }
}
```
#### BJP5 Exercise 1.14: Lanterns
```
public class Lanterns {
    public static void main(String[] args) {
        printTwoTriangles();
        printBars();
        printLine();
        printTwoTriangles();
        printFiveStars();
        printBars();
        printBars();
        printFiveStars();
        printFiveStars();
    }
    public static void printTwoTriangles() {
        printTriangle();
        System.out.println();
        printTriangle();
    }    
    public static void printTriangle() {
        System.out.println("    *****");
        System.out.println("  *********");
        System.out.println("*************");
    }
    public static void printBars() {
        System.out.println("* | | | | | *");
    }
    public static void printLine() {
        System.out.println("*************");
    }
    public static void printFiveStars() {
        System.out.println("    *****");
    }
}
```
#### BJP5 Exercise 1.15: EggStop
```
public class EggStop {
    public static void main(String[] args) {
        printEgg();
        System.out.println();
        printEgg();
        printLine();
        System.out.println();
        printTop();
        printStop();
        printBottom();
        printLine();
    }
    public static void printEgg() {
        printTop();
        printBottom();
    }
    public static void printLine() {
        System.out.println("+--------+");
    }
    public static void printTop() {
        System.out.println("  ______");
        System.out.println(" /      \\");
        System.out.println("/        \\");
    }
    public static void printBottom() {
        System.out.println("\\        /");
        System.out.println(" \\______/");
    }
    public static void printStop() {
        System.out.println("|  STOP  |");
    }
}
```
#### BJP5 Exercise 1.16: Shining
```
public class Shining{
    public static void main(String[] args){
        for(int i = 0; i < 1000; i++)
               printContent();
    }
    public static void printContent(){
        System.out.println("All work and no play makes Jack a dull boy.");
    }
}
```
#### BJP5 Exercise 1.17: FarewellGoodBye
```
public class FarewellGoodBye{
    public static void main(String[] args){
        partOne();
        partTwo();
        partThree();
    }
    public static void printTop(){
        System.out.println("Farewell,");
        System.out.println("goodbye,");
        System.out.println("au revoir,");
    }
    public static void partOne(){
        printTop();
        System.out.println("good night!\nIt's time, to go,\nand I'll be out of sight!\n");
    }
    public static void partTwo(){
        printTop();
        System.out.println("take care!\nI'll say, goodbye,\nthat's neither here nor there!\n");
    }
    public static void partThree(){
        printTop();
        System.out.println("see you later!\nI hope, you think,\nI'm a lover, not a hater!\n");
    }
}
```
