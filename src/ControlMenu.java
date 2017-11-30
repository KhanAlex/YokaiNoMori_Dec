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



    public ControlMenu(Plateau p, Fenetre f){
        this.plateau = p;
        this.fenetre = f;
        this.fenetre.setControlMenu(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Nouvelle partie")){
            System.out.println("New game");//6e
            plateau = new Plateau();
            fenetre.setPlateau(plateau);
            fenetre.newFenetre();
        }
        if (e.getActionCommand().equals("Quitter")){
            System.out.println("Fin du game !");//6e
            exit(0);
        }
    }
}
