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

    public CimetiereController(Plateau plateau, Fenetre fenetre) {
        this.plateau = plateau;
        this.fenetre = fenetre;
        fenetre.setCimetiereController(this);
        X = 0;
        Y = 0;
        indice = 0;
        cimetiere = fenetre.getCim2();

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        X = (mouseEvent.getX()-25)/100;
        Y = (mouseEvent.getY()+5)/100;
        indice = (X + (5*Y));
        System.out.println("indice" + indice);
        plateau.setPieceCimetiere(indice);
        cimetiere.metEnValeur(X,Y,indice);
        cimetiere.repaint();

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
