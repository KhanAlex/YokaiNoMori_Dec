import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by hannibal on 11/27/17.
 */
public class CimetiereController implements MouseListener{

    private Plateau plateau;
    private Fenetre fenetre;
    private int X;
    private int Y;
    private int indice;
    private CimetiereGraphic cimetiere;
    private int joueur;

    public CimetiereController(Plateau plateau, Fenetre fenetre) {
        this.plateau = plateau;
        this.fenetre = fenetre;
        fenetre.setCimetiereController(this);
        X = 0;
        Y = 0;
        indice = 0;
        cimetiere = fenetre.getCim1();
        joueur = 0;

    }
    public CimetiereController(Plateau plateau, Fenetre fenetre, int u) {
        this.plateau = plateau;
        this.fenetre = fenetre;
        fenetre.setCimetiereController2(this);
        X = 0;
        Y = 0;
        indice = 0;
        cimetiere = fenetre.getCim2();
        joueur = 1;

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (plateau.getJoueur()%2 == joueur%2){
            X = (mouseEvent.getX()-25)/100;
            Y = (mouseEvent.getY()+5)/100;
            indice = (X + (5*Y));
            System.out.println("indice" + indice);
            plateau.setPieceCimetiere(indice);
            if (plateau.getJoueur()%2 == 0){
                if (indice < plateau.getCimetiereJoueur1().size()) {
                    ((PlateauGraphic) fenetre.getImagePlateau()).afficheCasePossible(plateau.placePieceDeCimetiere(plateau.getCimetiereJoueur1().get(indice)));
                }
            }else{
                if (indice < plateau.getCimetiereJoueur2().size()) {
                    ((PlateauGraphic) fenetre.getImagePlateau()).afficheCasePossible(plateau.placePieceDeCimetiere(plateau.getCimetiereJoueur2().get(indice)));
                }

            }

            plateau.cimetiereJoue=true;
            fenetre.getImagePlateau().repaint();

            cimetiere.metEnValeur(X,Y,indice);
            cimetiere.repaint();
        }else{
            System.out.println("Ce n'est pas votre cimetiere");
        }


    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
