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
    private CimetiereGraphic cimetiere1;
    private CimetiereGraphic cimetiere2;

    public boolean cimetiereJoue;


    public ControlMouse(Plateau p, Fenetre f){
        this.plateau = p;
        this.fenetre = f;
        fenetre.setControlClick(this);
        plateauGraph = (PlateauGraphic) fenetre.getImagePlateau();
        bordureX = plateauGraph.getBordureX();
        largeurCase = plateauGraph.getLargeurCase();
        bordureY = plateauGraph.getBordureY();
        hauteurCase = plateauGraph.getHauteurCase();

        cimetiere1 = fenetre.getCim1();
        cimetiere2 = fenetre.getCim2();

        plateauGraph.tableauToSprite(plateau.getTab());
        plateauGraph.afficheQuiJoue(plateau.getJoueur());

        plateauGraph.repaint();
        cimetiereJoue=false;
    }




    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

       int[] tabCoor = {((mouseEvent.getX()-(bordureX/2))/largeurCase), ((mouseEvent.getY()-(bordureY/2))/hauteurCase)};
       int[][] movesPossibles = plateau.getMovePossible(tabCoor,plateau.getTab());

       if((plateau.getTab()[tabCoor[0]][tabCoor[1]]<0 && plateau.getJoueur() == 0) || (plateau.getTab()[tabCoor[0]][tabCoor[1]]>0 && plateau.getJoueur() == 1)){
           plateau.setMovesPossibleTour(movesPossibles);
           plateau.setPieceEnCours(plateau.getTab()[tabCoor[0]][tabCoor[1]]);
           plateau.setPieceEnCoursX(tabCoor[0]);
           plateau.setPieceEnCoursY(tabCoor[1]);
           plateauGraph.metEnValeurPiece(tabCoor);
           plateauGraph.afficheCasePossible(plateau.getMovePossible(tabCoor,plateau.getTab()));
           plateauGraph.repaint();
           plateau.cimetiereJoue=false;
       }

        if((plateau.getTab()[tabCoor[0]][tabCoor[1]]>=0 && plateau.getJoueur() == 0 && !plateau.cimetiereJoue)) {
           if(plateau.getMovesPossibleTour()[tabCoor[0]][tabCoor[1]]==1) {
               if (plateau.getTab()[tabCoor[0]][tabCoor[1]] == 0) {
                   plateau.bougerPiece(new int[] {plateau.getPieceEnCoursX(),plateau.getPieceEnCoursY()}, new int[]{tabCoor[0],tabCoor[1]});
                   if(plateau.promotionBoolean(plateau.getTab()[tabCoor[0]][tabCoor[1]],tabCoor[1]))
                        if(plateauGraph.askPromotion(plateau.getTab()[tabCoor[0]][tabCoor[1]]))
                            plateau.promotion(tabCoor);
                   plateau.setPieceEnCours(0);
                   plateau.setMovesPossibleTour(new int[5][6]);
                   plateau.setPieceEnCours(0);
                   plateau.setPieceEnCoursX(0);
                   plateau.setPieceEnCoursY(0);
                   plateauGraph.tableauToSprite(plateau.getTab());
                   boolean test = plateau.estEchec();
                   System.out.println(test);

                   plateauGraph.repaint();
                   plateau.setJoueur(1);
               } else if (plateau.getTab()[tabCoor[0]][tabCoor[1]] > 0) {
                   plateau.bougerPiece(new int[] {plateau.getPieceEnCoursX(),plateau.getPieceEnCoursY()}, new int[]{tabCoor[0],tabCoor[1]});
                   if(plateau.promotionBoolean(plateau.getTab()[tabCoor[0]][tabCoor[1]],tabCoor[1]))
                       if(plateauGraph.askPromotion(plateau.getTab()[tabCoor[0]][tabCoor[1]]))
                           plateau.promotion(tabCoor);
                   cimetiere1.actualiserCimetiere(plateau.getCimetiereJoueur1());
                   cimetiere1.repaint();
                   plateau.setMovesPossibleTour(new int[5][6]);
                   plateau.setPieceEnCours(0);
                   plateau.setPieceEnCoursX(0);
                   plateau.setPieceEnCoursY(0);
                   plateauGraph.tableauToSprite(plateau.getTab());
                   boolean test = plateau.estEchec();
                   System.out.println(test);

                   plateauGraph.repaint();
                   plateau.setJoueur(1);
               }
           }
        }


        if((plateau.getTab()[tabCoor[0]][tabCoor[1]]<=0 && plateau.getJoueur() == 1 && !plateau.cimetiereJoue)) {
            if(plateau.getMovesPossibleTour()[tabCoor[0]][tabCoor[1]]==1) {
                if (plateau.getTab()[tabCoor[0]][tabCoor[1]] == 0) {
                    plateau.bougerPiece(new int[] {plateau.getPieceEnCoursX(),plateau.getPieceEnCoursY()}, new int[]{tabCoor[0],tabCoor[1]});
                    if(plateau.promotionBoolean(plateau.getTab()[tabCoor[0]][tabCoor[1]],tabCoor[1]))
                        if(plateauGraph.askPromotion(plateau.getTab()[tabCoor[0]][tabCoor[1]]))
                            plateau.promotion(tabCoor);
                    plateau.setPieceEnCours(0);
                    plateau.setMovesPossibleTour(new int[5][6]);
                    plateau.setPieceEnCoursX(0);
                    plateau.setPieceEnCoursY(0);
                    plateauGraph.tableauToSprite(plateau.getTab());
                    boolean test = plateau.estEchec();
                    System.out.println(test);

                    plateauGraph.repaint();
                    plateau.setJoueur(0);
                } else if (plateau.getTab()[tabCoor[0]][tabCoor[1]] < 0) {
                    plateau.bougerPiece(new int[] {plateau.getPieceEnCoursX(),plateau.getPieceEnCoursY()}, new int[]{tabCoor[0],tabCoor[1]});
                    if(plateau.promotionBoolean(plateau.getTab()[tabCoor[0]][tabCoor[1]],tabCoor[1]))
                        if(plateauGraph.askPromotion(plateau.getTab()[tabCoor[0]][tabCoor[1]]))
                            plateau.promotion(tabCoor);
                    cimetiere2.actualiserCimetiere(plateau.getCimetiereJoueur2());
                    cimetiere2.repaint();
                    plateau.setMovesPossibleTour(new int[5][6]);
                    plateau.setPieceEnCours(0);
                    plateau.setPieceEnCoursX(0);
                    plateau.setPieceEnCoursY(0);
                    plateauGraph.tableauToSprite(plateau.getTab());
                    if(plateau.estEchec()) {
                        System.out.println("echec");
                    }
                    plateauGraph.afficheQuiJoue(1);
                    plateauGraph.repaint();
                    plateau.setJoueur(0);
                }

            }
        }

        if((plateau.getTab()[tabCoor[0]][tabCoor[1]]==0 && plateau.getJoueur() == 1 && plateau.getPieceCimetiere() != -1)) {
           int kodamaSurLigne = 0;
           for(int i=0; i<6; i++){
               if(plateau.getTab()[tabCoor[0]][i]==1){
                   kodamaSurLigne++;
               }
           }
           if(kodamaSurLigne==0){
               plateau.getTab()[tabCoor[0]][tabCoor[1]] = plateau.getCimetiereJoueur2().get(plateau.getPieceCimetiere());
               plateau.getCimetiereJoueur2().remove(plateau.getPieceCimetiere());
               cimetiere2.actualiserCimetiere(plateau.getCimetiereJoueur2());
               cimetiere2.repaint();
               plateauGraph.tableauToSprite(plateau.getTab());
               plateauGraph.afficheQuiJoue(1);
               plateauGraph.repaint();
               plateau.setPieceCimetiere(-1);
               plateau.setJoueur(0);
           }
        }

        if((plateau.getTab()[tabCoor[0]][tabCoor[1]]==0 && plateau.getJoueur() == 0 && plateau.getPieceCimetiere()  != -1)) {
            int kodamaSurLigne = 0;
            for(int i=0; i<6; i++){
                if(plateau.getTab()[tabCoor[0]][i]==-1){
                    kodamaSurLigne++;
                }
            }
            if(kodamaSurLigne==0){
                plateau.getTab()[tabCoor[0]][tabCoor[1]] = plateau.getCimetiereJoueur1().get(plateau.getPieceCimetiere());
                plateau.getCimetiereJoueur1().remove(plateau.getPieceCimetiere());
                cimetiere1.actualiserCimetiere(plateau.getCimetiereJoueur1());
                cimetiere1.repaint();
                plateauGraph.tableauToSprite(plateau.getTab());
                plateauGraph.repaint();
                plateau.setPieceCimetiere(-1);
                plateau.setJoueur(1);
            }
        }

        if(plateau.getGagnant() > -1) {
           switch (plateau.getGagnant()){
               case 0:
                   plateauGraph.showWinner(1);
                   break;
               case 1:
                   plateauGraph.showWinner(2);
                   break;
           }
          fenetre.removeControlClick(this);
        }
        plateauGraph.afficheQuiJoue(plateau.getJoueur());


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
