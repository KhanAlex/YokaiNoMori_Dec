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
    private PlateauGraphic pG;
    private ControlMouse currentControl;
    private CimetiereController cim1;
    private CimetiereController cim2;
    private CimetiereGraphic cG;
    private CimetiereGraphic cG2;



    public ControlMenu(Plateau p, Fenetre f, ControlMouse cm, CimetiereController c1, CimetiereController c2){
        this.plateau = p;
        this.fenetre = f;
        pG = (PlateauGraphic) fenetre.getImagePlateau();
        this.fenetre.setControlMenu(this);
        currentControl = cm;
        cim1 = c1;
        cim2 = c2;
        cG = fenetre.getCim1();
        cG2 = fenetre.getCim2();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Nouvelle partie")){
            System.out.println("New game");//6e
            plateau = new Plateau();
            ControlMouse tmp = new ControlMouse(plateau,fenetre);
            fenetre.newGame(currentControl, cim1, cim2);
            cim1 = new CimetiereController(plateau, fenetre);
            cim2 = new CimetiereController(plateau, fenetre,2);
            currentControl = tmp;
            pG.afficheQuiJoue(0);
            pG.tableauToSprite(plateau.getTab());
            pG.repaint();
            cG.actualiserCimetiere(plateau.getCimetiereJoueur1());
            cG2.actualiserCimetiere(plateau.getCimetiereJoueur2());
            cG.repaint();
            cG2.repaint();


        }
        if (e.getActionCommand().equals("Quitter")){
            System.out.println("Fin du game !");//6e
            exit(0);
        }
    }
}
