import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * Created by ayoganat on 04/10/17.
 */
public class ControlMouse implements MouseListener {

    private Plateau plateau;
    private Fenetre fenetre;
    //* Liste de cases
    private int bordureX;
    private int largeurCase;
    private int bordureY;
    private int hauteurCase;
    private PlateauGraphic plateauGraph;


    public ControlMouse(Plateau p, Fenetre f){
        this.plateau = p;
        this.fenetre = f;
        fenetre.setControlClick(this);
        plateauGraph = (PlateauGraphic) fenetre.getImagePlateau();
        bordureX = plateauGraph.getBordureX();
        largeurCase = plateauGraph.getLargeurCase();
        bordureY = plateauGraph.getBordureY();
        hauteurCase = plateauGraph.getHauteurCase();

        plateauGraph.tableauToSprite(plateau.getTab());

        plateauGraph.repaint();
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
       int[] tabCoor = {((mouseEvent.getX()-(bordureX/2))/largeurCase), ((mouseEvent.getY()-(bordureY/2))/hauteurCase)};
       int[][] movesPossibles = plateau.getMovePossible(tabCoor);

       if((plateau.getTab()[tabCoor[0]][tabCoor[1]]<0 && plateau.getJoueur() == 0) || (plateau.getTab()[tabCoor[0]][tabCoor[1]]>0 && plateau.getJoueur() == 1)){
           plateau.setMovesPossibleTour(movesPossibles);
           plateau.setPieceEnCours(plateau.getTab()[tabCoor[0]][tabCoor[1]]);
           plateau.setPieceEnCoursX(tabCoor[0]);
           plateau.setPieceEnCoursY(tabCoor[1]);
           plateauGraph.afficheCasePossible(plateau.getMovePossible(tabCoor));
           plateauGraph.repaint();
       }

        if((plateau.getTab()[tabCoor[0]][tabCoor[1]]>=0 && plateau.getJoueur() == 0)) {
           if(plateau.getMovesPossibleTour()[tabCoor[0]][tabCoor[1]]==1) {
               if (plateau.getTab()[tabCoor[0]][tabCoor[1]] == 0) {
                   plateau.bougerPiece(new int[] {plateau.getPieceEnCoursX(),plateau.getPieceEnCoursY()}, new int[]{tabCoor[0],tabCoor[1]});
                   plateau.setPieceEnCours(0);
                   plateau.setMovesPossibleTour(new int[5][6]);
                   plateau.setPieceEnCours(0);
                   plateau.setPieceEnCoursX(0);
                   plateau.setPieceEnCoursY(0);
                   plateauGraph.tableauToSprite(plateau.getTab());
                   boolean test = plateau.estEchec();
                   System.out.println(test);
                   if(test)
                       plateauGraph.afficheCaseEchec(plateau.getRoi());
                   else
                       plateauGraph.setNullCaseEchec();
                   plateauGraph.repaint();
                   plateau.setJoueur(1);
               } else if (plateau.getTab()[tabCoor[0]][tabCoor[1]] > 0) {
                   plateau.placePieceDeCimetiere(plateau.getTab()[tabCoor[0]][tabCoor[1]]);
                   plateau.bougerPiece(new int[] {plateau.getPieceEnCoursX(),plateau.getPieceEnCoursY()}, new int[]{tabCoor[0],tabCoor[1]});
                   plateau.setMovesPossibleTour(new int[5][6]);
                   plateau.setPieceEnCours(0);
                   plateau.setPieceEnCoursX(0);
                   plateau.setPieceEnCoursY(0);
                   plateauGraph.tableauToSprite(plateau.getTab());
                   boolean test = plateau.estEchec();
                   System.out.println(test);
                   if(test) {
                       plateauGraph.afficheCaseEchec(plateau.getRoi());
                   }
                   else
                       plateauGraph.setNullCaseEchec();
                   plateauGraph.repaint();
                   plateau.setJoueur(1);
               }
           }
        }


        if((plateau.getTab()[tabCoor[0]][tabCoor[1]]<=0 && plateau.getJoueur() == 1)) {
            if(plateau.getMovesPossibleTour()[tabCoor[0]][tabCoor[1]]==1) {
                if (plateau.getTab()[tabCoor[0]][tabCoor[1]] == 0) {
                    System.out.println("test1");

                    plateau.bougerPiece(new int[] {plateau.getPieceEnCoursX(),plateau.getPieceEnCoursY()}, new int[]{tabCoor[0],tabCoor[1]});
                    if(plateau.promotionBoolean(plateau.getPieceEnCours(), tabCoor[1])){
                       /* if(fenetre.promotionMessage()){
                            promotion
                            */
                    }
                    plateau.setPieceEnCours(0);
                    plateau.setMovesPossibleTour(new int[5][6]);
                    plateau.setPieceEnCoursX(0);
                    plateau.setPieceEnCoursY(0);
                    plateauGraph.tableauToSprite(plateau.getTab());
                    boolean test = plateau.estEchec();
                    System.out.println(test);
                    if(test)
                        plateauGraph.afficheCaseEchec(plateau.getRoi());
                    else
                        plateauGraph.setNullCaseEchec();
                    plateauGraph.repaint();
                    plateau.setJoueur(0);
                } else if (plateau.getTab()[tabCoor[0]][tabCoor[1]] < 0) {
                    System.out.println("test2");

                    plateau.bougerPiece(new int[] {plateau.getPieceEnCoursX(),plateau.getPieceEnCoursY()}, new int[]{tabCoor[0],tabCoor[1]});
                    plateau.placePieceDeCimetiere(plateau.getTab()[tabCoor[0]][tabCoor[1]]);
                    plateau.setMovesPossibleTour(new int[5][6]);
                    plateau.setPieceEnCours(0);
                    plateau.setPieceEnCoursX(0);
                    plateau.setPieceEnCoursY(0);
                    plateauGraph.tableauToSprite(plateau.getTab());
                    boolean test = plateau.estEchec();
                    System.out.println(test);
                    if(test) {
                        System.out.println("x: " + plateau.getRoi()[0] + " y:" + plateau.getRoi()[1]);
                        plateauGraph.afficheCaseEchec(plateau.getRoi());
                    }
                    else
                        plateauGraph.setNullCaseEchec();
                    plateauGraph.repaint();
                    plateau.setJoueur(0);
                }

            }
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

    public void initCasesPetitPlateau(){

    }


}
