package gridgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * @author g.lumsden
 */
public class LoadingScreen extends javax.swing.JFrame {

    private final int FORM_WIDTH  = 520; 
    private final int FORM_HEIGHT = 171; 
    private int count; 
    private Grid grid; 
    
    public Timer timer; 
    
    public LoadingScreen() {
        initComponents();
        setupForm(); 
        setupTimers(); 
        grid = new Grid(); 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        lblText.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        lblText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblText.setText("Loading");
        lblText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(lblText);
        lblText.setBounds(0, 0, 520, 170);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblText;
    // End of variables declaration//GEN-END:variables

    private void setupForm() {
        this.setSize(FORM_WIDTH, FORM_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void setupTimers() {
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (grid.done) clear();
                else {
                    if (count == 0) {
                        lblText.setText("Loading");
                        count++; 
                    } else if (count == 1) {
                        lblText.setText("Loading.");
                        count++; 
                    } else if (count == 2) {
                        lblText.setText("Loading..");
                        count++; 
                    } else if (count == 3) {
                        lblText.setText("Loading...");
                        count = 0; 
                    }
                }
            }
        });
        timer.start();
    }
    
    private void clear() {
        timer.stop();
        this.dispose();
        grid.setVisible(true);
    }
    
}
