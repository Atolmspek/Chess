/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Martin
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Tablero extends javax.swing.JFrame {

    private JPanel panel;
    private JButton listaBotones[][] = new JButton[8][8];
    private Image[][] imagenPiezas = new Image[2][6];
    private static final String COLS = "ABCDEFGH";
    public static final int QUEEN = 0, KING = 1,
            ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
    public static final int[] STARTING_ROW = {
        ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK
    };
    public static final int BLACK = 0, WHITE = 1;

    public Tablero() throws IOException {
        initComponents();
       crearImagenes();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    //   SwingUtilities.invokeLater(this);
                    new Tablero().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    //Listener de las acciones
    private void FActionPerformed(java.awt.event.ActionEvent evt) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (listaBotones[i][j] == evt.getSource()) {

                    JOptionPane.showMessageDialog(this, "Fila " + (i + 1) + " Columna " + COLS.charAt(j));
                    break;
                }
            }
        }
    }
    
    private void crearImagenes() throws IOException {
        BufferedImage imagen;
        imagen = ImageIO.read(new File("C:\\Users\\Martin\\Documents\\NetBeansProjects\\mavenproject1\\MiAjedrez\\src\\resources\\piezas.png"));
        for (int ii = 0; ii < 2; ii++) {
                for (int jj = 0; jj < 6; jj++) {
                    imagenPiezas[ii][jj] = imagen.getSubimage(
                            jj * 64, ii * 64, 64, 64);
              
                }
            }
         for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            listaBotones[ii][0].setIcon(new ImageIcon(
                    imagenPiezas[BLACK][STARTING_ROW[ii]]));
        }
        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            listaBotones[ii][1].setIcon(new ImageIcon(
                    imagenPiezas[BLACK][PAWN]));
        }
        // set up the white pieces
        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            listaBotones[ii][6].setIcon(new ImageIcon(
                    imagenPiezas[WHITE][PAWN]));
        }
        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            listaBotones[ii][7].setIcon(new ImageIcon(
                    imagenPiezas[WHITE][STARTING_ROW[ii]]));
        }
    }

    public void initComponents() throws IOException {

        panel = new JPanel((new GridLayout(0, 9))) {
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int) d.getWidth(), (int) d.getHeight());
                } else if (c != null
                        && c.getWidth() > d.getWidth()
                        && c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w > h ? h : w);
                return new Dimension(s, s);
            }
        };

        Color ochre = new Color(185, 147, 90);
        panel.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(panel);
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        

        panel.setBorder(new CompoundBorder(
                new EmptyBorder(8, 8, 8, 8),
                new LineBorder(Color.BLACK)
        ));

//Creamos un "plano" de cómo va a ser cada botón y al final del for lo añadimos.      
        for (int fila = 0; fila < listaBotones.length; fila++) {
            for (int columna = 0; columna < listaBotones[fila].length; columna++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);

                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);

                if ((fila + columna) % 2 != 0) {
                    // En el tablero de ajedrez, en las blancas la suma fila+columna es siempre un número par, y las negras impar
                    b.setBackground(Color.BLACK); // color negro degradado

                } else {
                    b.setBackground(Color.WHITE); // color blanco
                }
                b.addActionListener(new java.awt.event.ActionListener() { // añadimos un listener anónimo a cada botón
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        FActionPerformed(evt);
                    }
                });
                listaBotones[fila][columna] = b;
            }
        }

        panel.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            panel.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                            SwingConstants.CENTER));
        }

        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        panel.add(new JLabel("" + (9 - (ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        panel.add(listaBotones[jj][ii]);
                }
            }

            // Detalles de la configuración. Originalmente generado por una plantilla de diseño.
            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
 this.setSize(800, 800); // Si no establecemos el tamaño, no se va a ver bien ya que el tamaño de la cuadrícula y de las casillas es relativo, se define según el tamaño total de la ventana.
        this.setTitle("Tablero Ajedrez");
        this.setLocationRelativeTo(null); // Centramos la ventana en el escritorio
        this.setMinimumSize(this.getSize());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        }
    }
}
