package swingtest;

import java.io.*;
import java.awt.*;
import javax.swing.*; // javax - x stands for eXtension
import java.net.*;
import java.awt.geom.*;

public class First {
    public static void main(String ... args)
    throws UnsupportedEncodingException
    {
        PrintStream ps = new PrintStream(System.out, true, "Cp866");
        ps.println("The program is running\n\n");

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (var f : fonts){
            ps.println(f);
        }

        EventQueue.invokeLater(()->{
            var frame = new SimpleFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // initially a program isn't set to terminate itself when the frame is closed
            frame.setVisible(true); // all frames are invisible by default
            frame.getContentPane().setBackground(Color.BLACK);
        });
    }
}


class SimpleFrame extends JFrame{
    private static final int DEFAULT_WIDTH = 1280;
    private static final int DEFAULT_HEIGHT = 720;

    public SimpleFrame(){
        //setBackground(Color.CYAN);

        //setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setTitle("ZA WARUDO");

        URL iconURL = getClass().getResource("j.png");
        // iconURL is null when not found
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());

        setResizable(true);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setBounds(screenWidth / 6, screenHeight / 6, screenWidth / 2, screenHeight / 2);

        add(new DrawComponent());
        pack();
    }
}

class DrawComponent extends JComponent{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    public static final int MESSAGE_X = 75;
    public static final int MESSAGE_Y = 100;

    // this method is invoked automatically when it is needed
    @Override public void paintComponent(Graphics g){
        //g.drawOval(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT); // not a good way to draw a figure
        Font textFont = new Font("Kingthings Petrock", Font.BOLD, 24);
        g.setColor(new Color(234, 232, 190));
        g.setFont(textFont);
        g.drawString("It was me, DIO!", MESSAGE_X, MESSAGE_Y);

        Graphics2D g2 = (Graphics2D) g;

        double ellipseWidth = 100;
        double ellipseHeight = 100;
        Point2D.Double center = new Point2D.Double(300.0, 300.0);

        Ellipse2D.Double innerEll = new Ellipse2D.Double(center.getX() - ellipseWidth / 2, center.getY() - ellipseHeight / 2, ellipseWidth, ellipseHeight);
        Rectangle2D.Double rect = new Rectangle2D.Double(center.getX() - ellipseWidth / 2, center.getY() - ellipseHeight / 2, ellipseWidth, ellipseHeight);
        innerEll.setFrame(rect); // bounds the rectangle to the ellipse for easier access
        Ellipse2D.Double outerEll = new Ellipse2D.Double(center.getX() - ellipseWidth, center.getY() - ellipseHeight, ellipseWidth * 2, ellipseHeight * 2);
        Line2D.Double diagLine = new Line2D.Double(innerEll.getFrame().getX(), innerEll.getFrame().getY(), innerEll.getFrame().getX() + innerEll.getFrame().getWidth(), innerEll.getFrame().getY() + innerEll.getFrame().getHeight());

        g2.setPaint(Color.RED);
        g2.fill(innerEll);
        g2.setPaint(Color.BLUE);
        g2.draw(rect);
        g2.draw(outerEll);
        g2.draw(diagLine);
    }

    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}