import java.awt.*;
import java.awt.event.*;

 class SketchPad extends Frame implements ActionListener, WindowListener, MouseListener, MouseMotionListener {
    private int startX, startY, endX, endY;
    private Color currentColor = Color.BLACK;
    private String currentShape = "Line";
    private boolean fillShape = false;
    
    // Array to store available colors with their names
    private Color[] availableColors = {Color.BLACK, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    private String[] colorNames = {"Black", "Red", "Blue", "Green", "Yellow","PINK","ORANGE"};
    
    public SketchPad() {
        setTitle("SketchPad");
        setSize(600, 400);
        setBackground(Color.WHITE);
        addWindowListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        // Creating menu bar
        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);
        
        // Creating menus
        Menu shapeMenu = new Menu("Shapes");
        menuBar.add(shapeMenu);
        
        Menu colorMenu = new Menu("Colors");
        menuBar.add(colorMenu);
        
        // Adding shapes to shape menu
        String[] shapes = {"Line", "Rectangle", "Circle"};
        for (String shape : shapes) {
            MenuItem shapeItem = new MenuItem(shape);
            shapeItem.addActionListener(this);
            shapeMenu.add(shapeItem);
        }
        
        // Adding colors to color menu
        for (int i = 0; i < availableColors.length; i++) {
            MenuItem colorItem = new MenuItem(colorNames[i]);
            colorItem.addActionListener(this);
            colorMenu.add(colorItem);
        }
        
        // Adding fill option
        Menu optionsMenu = new Menu("Options");
        menuBar.add(optionsMenu);
        CheckboxMenuItem fillItem = new CheckboxMenuItem("Fill Shape");
        fillItem.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                fillShape = !fillShape;
            }
        });
        optionsMenu.add(fillItem);
    }
    
    public void paint(Graphics g) {
        g.setColor(currentColor);
        if (currentShape.equals("Line")) {
            g.drawLine(startX, startY, endX, endY);
        } else if (currentShape.equals("Rectangle")) {
            int width = Math.abs(endX - startX);
            int height = Math.abs(endY - startY);
            int x = Math.min(startX, endX);
            int y = Math.min(startY, endY);
            if (fillShape) {
                g.fillRect(x, y, width, height);
            } else {
                g.drawRect(x, y, width, height);
            }
        } else if (currentShape.equals("Circle")) {
            int width = Math.abs(endX - startX);
            int height = Math.abs(endY - startY);
            int x = Math.min(startX, endX);
            int y = Math.min(startY, endY);
            if (fillShape) {
                g.fillOval(x, y, width, height);
            } else {
                g.drawOval(x, y, width, height);
            }
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Line") || command.equals("Rectangle") || command.equals("Circle")) {
            currentShape = command;
        } else {
            for (int i = 0; i < colorNames.length; i++) {
                if (colorNames[i].equals(command)) {
                    currentColor = availableColors[i];
                    break;
                }
            }
        }
    }
    
    public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
    }
    
    // Other window listener methods
    public void windowOpened(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    
    // Mouse listener methods
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
    }
    public void mouseReleased(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
        repaint();
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    // Mouse motion listener methods
    public void mouseDragged(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
        repaint();
    }
    public void mouseMoved(MouseEvent e) {}
    
    public static void main(String[] args) {
        SketchPad sketchPad = new SketchPad();
        sketchPad.setVisible(true);
    }
}
