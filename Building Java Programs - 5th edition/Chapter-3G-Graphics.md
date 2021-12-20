## Chapter 3G: Graphics

### This file contains my implementation for all exercises of the Chapter 3G: Graphics

#### BJP5 Exercise 3G.1: Mickey Box
```
public class MickeyBox {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(220, 150);
        Graphics g = panel.getGraphics();
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 220, 150);
        g.setColor(Color.BLUE);
        g.fillOval(50, 25, 40, 40);
        g.fillOval(130, 25, 40, 40);
        g.setColor(Color.RED);
        g.fillRect(70, 45, 80, 80);
        g.setColor(Color.BLACK);
        g.drawLine(70, 85, 150, 85);
    }
}
```
#### BJP5 Exercise 3G.2: Mickey Box 2
```
public class MickeyBox2 {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(450, 150);
        Graphics g = panel.getGraphics();
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 450, 150);
        drawFigure(g, 50, 25);
        drawFigure(g, 250, 45);
    }
    
    public static void drawFigure(Graphics g, int x, int y) {
        g.setColor(Color.BLUE);
        g.fillOval(x, y, 40, 40);
        g.fillOval(x + 80, y, 40, 40);
        g.setColor(Color.RED);
        g.fillRect(x + 20, y + 20, 80, 80);
        g.setColor(Color.BLACK);
        g.drawLine(x + 20, y + 60, x + 100, y + 60);        
    }
}
```
#### BJP5 Exercise 3G.3: Face
```
public class Face {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(320, 180);
        Graphics g = panel.getGraphics();
        drawFace(g, 10, 30);
        drawFace(g, 150, 50);
    }
    
    public static void drawFace(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawOval(x, y, 100, 100);   
        g.setColor(Color.BLUE);
        g.fillOval(x + 20, y + 30, 20, 20);    
        g.fillOval(x + 60, y + 30, 20, 20);
        g.setColor(Color.RED);       
        g.drawLine(x + 30, y + 70, x + 70, y + 70);
    }
}
```
#### BJP5 Exercise 3G.4: Face2
```
public class Face2 {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(520, 180);
        Graphics g = panel.getGraphics();
        for(int i = 0; i < 5; i++)
            drawFace(g, 10 + 100 * i, 30);
    }
    
    public static void drawFace(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawOval(x, y, 100, 100);     // face outline
        
        g.setColor(Color.BLUE);
        g.fillOval(x + 20, y + 30, 20, 20);     // eyes
        g.fillOval(x + 60, y + 30, 20, 20);
        
        g.setColor(Color.RED);          // mouth
        g.drawLine(x + 30, y + 70, x + 70, y + 70);
    }
}
```
#### BJP5 Exercise 3G.5: ShowDesign
```
public class ShowDesign {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(200, 200);
        Graphics g = panel.getGraphics();
        for(int i = 0; i < 4; i++)
            g.drawRect(20 + 20 * i, 20 + 20 * i, 160 - 40 * i, 160 - 40 * i);
    }
}
```
#### BJP5 Exercise 3G.6: ShowDesign2
```
public class ShowDesign2 {
    public static void main(String[] args) {
        showDesign(300, 100);
    }
    
    public static void showDesign(int width, int height) {
        DrawingPanel panel = new DrawingPanel(width, height);
        Graphics g = panel.getGraphics();
        for(int i = 1; i <= 4; i++)
            g.drawRect(30 * i, 10 * i, width - 60 * i, height - 20 * i);
    }
}
```
#### BJP5 Exercise 3G.7: Squares
```
public class Squares {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(300, 200);
        Graphics g = panel.getGraphics();
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 300, 200);
        g.setColor(Color.RED);
        
        for(int i = 1; i <= 5; i++)
            g.drawRect(50, 50, 20 * i, 20 * i);
            
        g.setColor(Color.BLACK);
        g.drawLine(50, 50, 150, 150);
    }
}
```
#### BJP5 Exercise 3G.8: Squares2
```
public class Squares2 {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(400, 300);
        Graphics g = panel.getGraphics();
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 400, 300);
        drawFigure(g, 50, 50);
        drawFigure(g, 250, 10);
        drawFigure(g, 180, 115);
    }
    
    public static void drawFigure(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        
        for(int i = 1; i <= 5; i++)
            g.drawRect(x, y, 20 * i, 20 * i);
            
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x + 100, y + 100);
    }
}
```
#### BJP5 Exercise 3G.9: Squares3
```
public class Squares3 {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(400, 300);
        Graphics g = panel.getGraphics();
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 400, 300);
        drawFigure(g, 50, 50, 100);
        drawFigure(g, 250, 10, 50);
        drawFigure(g, 180, 115, 180);
    }
    
    public static void drawFigure(Graphics g, int x, int y, int size) {
        int separation = size / 5;
        g.setColor(Color.RED);
        
        for(int i = 1; i <= 5; i++)
            g.drawRect(x, y, separation * i, separation * i);
            
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x + size, y + size);
    }
}
```
#### BJP5 Exercise 3G.10: Stairs
```
public class Stairs {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(110, 110);
        Graphics g = panel.getGraphics();
        for(int i = 0; i < 10; i++) {
            g.drawRect(5, 5 + 10 * i, 10 + 10 * i, 10);
        }
    }
}
```
#### BJP5 Exercise 3G.11: Stairs2
```
public class Stairs2 {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(110, 110);
        Graphics g = panel.getGraphics();
        for(int i = 0; i < 10; i++) {
            g.drawRect(5, 5 + 10 * i, 100 - 10 * i, 10);
        }
    }
}
```
#### BJP5 Exercise 3G.11b: Stairs3
```
public class Stairs3 {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(110, 110);
        Graphics g = panel.getGraphics();
        for(int i = 0; i < 10; i++) {
            g.drawRect(95 - 10 * i, 5 + 10 * i, 10 + 10 * i, 10);
        }
    }
}
```
#### BJP5 Exercise 3G.11c: Stairs4
```
public class Stairs4 {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(110, 110);
        Graphics g = panel.getGraphics();
        for(int i = 0; i < 10; i++) {
            g.drawRect(5 + 10 * i, 5 + 10 * i, 100 - 10 * i, 10);
        }
    }
}
```
#### BJP5 Exercise 3G.12: Triangle
```
public class Triangle {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(600, 200);
        Graphics g = panel.getGraphics();
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 600, 200);
        g.setColor(Color.BLUE);
        
        for(int y = 10; y < 200; y += 10) {
            int x1 = 3 * y / 2;
            int x2 = 3 * (400 - y) / 2;
            g.drawLine(x1, y, x2, y);
        }
        
        g.drawLine(0, 0, 300, 200);
        g.drawLine(300, 200, 600, 0);
    }
}
```
#### BJP5 Exercise 3G.13: Football
```
public class Football {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(250, 250);
        Graphics g = panel.getGraphics();
        g.setColor(Color.black);
		    g.drawRect(10, 30, 200, 200);
		    for(int i = 0; i < 20; i++) {
            int x1 = 10;
		       	int y1 = 220 - 10 * i;
			      int x2 = 20 + 10 * i;
			      int y2 = 30;
		        g.drawLine(x1, y1, x2, y2);
		        g.drawLine(x2, 230, 210, y1);
        }
    }
}
```
