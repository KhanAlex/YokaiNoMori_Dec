import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;
import static java.lang.System.setOut;

/**
 * Created by bcontrer on 04/10/17.
 */
public class ControlMenu implements ActionListener {

    private Plateau plateau;
    private Fenetre fenetre;

    //* Constantes
    private final int TAILLE_X_GRANDE = 1500;
    private final int TAILLE_Y_GRANDE = 1500;
    private final int TAILLE_X_PETIT = 800;
    private final int TAILLE_Y_PETIT = 850;


    public ControlMenu(Plateau p, Fenetre f){
        this.plateau = p;
        this.fenetre = f;
        this.fenetre.setControlMenu(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Quitter")){
            System.out.println("Fin du game !");//6e
            exit(0);
        }
    }
}
