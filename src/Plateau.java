import java.util.ArrayList;
import java.util.List;

/**
 * Created by bcontrer on 04/10/17.
 */


public class Plateau {
    private int tour;
    private int[][] movesPossibleTour;

    public int getPieceEnCours() {
        return pieceEnCours;
    }

    public void setPieceEnCours(int pieceEnCours) {
        this.pieceEnCours = pieceEnCours;
    }

    private int pieceEnCours;

    public int getPieceEnCoursX() {
        return pieceEnCoursX;
    }

    public void setPieceEnCoursX(int pieceEnCoursX) {
        this.pieceEnCoursX = pieceEnCoursX;
    }

    public int getPieceEnCoursY() {
        return pieceEnCoursY;
    }

    public void setPieceEnCoursY(int pieceEnCoursY) {
        this.pieceEnCoursY = pieceEnCoursY;
    }

    private int pieceEnCoursX;
    private int pieceEnCoursY;

    public int getTour() {
        return tour;
    }

    public int[][] getMovesPossibleTour() {
        return movesPossibleTour;
    }

    public void setMovesPossibleTour(int[][] movesPossibleTour) {
        this.movesPossibleTour = movesPossibleTour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }

    public int getJoueur() {
        return joueur;
    }

    public void setJoueur(int joueur) {
        this.joueur = joueur;
    }

    public void setTab(int[][] tab) {
        this.tab = tab;
    }

    public ArrayList<Integer> getCimetiere() {
        return cimetiereJoueur1;
    }

    public void setCimetiere(ArrayList<Integer> cimetiere) {
        this.cimetiereJoueur1 = cimetiere;
    }

    private int joueur;
    private int[][] tab;
    private ArrayList<Integer> cimetiereJoueur1;
    private ArrayList<Integer> cimetiereJoueur2;


    //1 == pion
    //2 == super pion
    //3 == ogre
    //4 == super ogre
    //5 == dragon
    //6 == roi
    //- == adversaire

    public Plateau(){
        tab = new int[5][6];
        movesPossibleTour = new int[5][6];
        for(int i=0; i<5; i++){
            for(int y=0; y<6; y++){
                tab[i][y]=0;
                movesPossibleTour[i][y]=-1;
            }
        }

        /* Pieces joueur 1 */
        tab[0][0] = 3; tab[1][0] = 5; tab[2][0] = 6; tab[3][0] = 5; tab[4][0] = 3;
        tab[1][2] = 1; tab[2][2] = 1; tab[3][2] = 1;

        /* Pieces joueur 2*/
        tab[1][3] = -1; tab[2][3] = -1; tab[3][3] = -1;
        tab[0][5] = -3; tab[1][5] = -5; tab[2][5] = -6; tab[3][5] = -5; tab[4][5] = -3;

        cimetiereJoueur1 = new ArrayList<Integer>();
        cimetiereJoueur2 = new ArrayList<Integer>();

        joueur = 0;

    }

    public void bougerPiece(int[] numPiece, int[] numCase){
        if( tab[numCase[0]][numCase[1]] != 0){
            mangePiece(tab[numCase[0]][numCase[1]]);
        }
        tab[numCase[0]][numCase[1]] = tab[numPiece[0]][numPiece[1]];
        tab[numPiece[0]][numPiece[1]]=0;
        estEchec();
        tour++;
    }


    public boolean estEchec(){
        int[][] casePossibleRoi = new int[5][6];
        int[][] cassePossibleAssayant;
        int xRoi = -1;
        int yRoi = -1;
        System.out.println(joueur);

        if(joueur==0){
            for(int x=0;x<5;x++){
                for(int y=0;y<6;y++){
                    if (tab[x][y] == 6) {
                        casePossibleRoi= getMovePossible(new int[]{x,y});
                        xRoi = x;
                        yRoi = y;
                        System.out.println(x+" "+y);

                    }
                }
            }

            for(int x=0;x<5;x++){
                for(int y=0;y<6;y++){
                    if(casePossibleRoi[x][y]==1 && tab[x][y] < 0){
                        cassePossibleAssayant = getMovePossible(new int[] {x,y});
                        if(cassePossibleAssayant[xRoi][yRoi]==1){
                            System.out.println("echec1");
                            return true;
                        }
                    }
                }
            }
        }
        if(joueur==1){
            for(int x=0;x<5;x++){
                for(int y=0;y<6;y++){
                    if (tab[x][y]== -6) {
                        casePossibleRoi= getMovePossible(new int[]{x,y});
                        xRoi = x;
                        yRoi = y;
                    }
                }
            }

            for(int x=0;x<5;x++){
                for(int y=0;y<6;y++){
                    if(casePossibleRoi[x][y]==1 && tab[x][y] > 0){
                        cassePossibleAssayant = getMovePossible(new int[] {x,y});
                        if(cassePossibleAssayant[xRoi][yRoi]==1){
                            System.out.println("echec2");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean estEchecEtMath(){
        return true;
    }


    public void mangePiece(int nbEstMange)
    {
        if(joueur==0)
            cimetiereJoueur1.add(nbEstMange);
        else
            cimetiereJoueur2.add(nbEstMange);
    }

    public boolean promotionBoolean(int piece, int coorY){
        if (piece !=1 && piece !=-1 && piece !=3 && piece !=-3 )
            return false;
        if(joueur == 0 && coorY < 4 || joueur == 1 && coorY >1 )
            return false;
        return true;
    }

    public void promotion(int[] numCase){
        if (tab[numCase[0]][numCase[1]] == 1)
            tab[numCase[0]][numCase[1]] = 2;
        if (tab[numCase[0]][numCase[1]] == -1)
            tab[numCase[0]][numCase[1]] = -2;
        if (tab[numCase[0]][numCase[1]] == 3)
            tab[numCase[0]][numCase[1]] = 4;
        if (tab[numCase[0]][numCase[1]] == -3)
            tab[numCase[0]][numCase[1]] = -4;
    }

    //1 == pion
    //2 == super pion
    //3 == ogre
    //4 == super ogre
    //5 == dragon
    //6 == roi
    //- == adversaire

    public int[][] getMovePiece(int numPiece){
        int[][] possitionPossible=new int[3][3];
        for(int i=0;i<3;i++){
            possitionPossible[0][i]=0;
            possitionPossible[1][i]=0;
            possitionPossible[2][i]=0;
        }
        if(numPiece==1){
            possitionPossible[1][2]=1;
            return possitionPossible;
        }
        if(numPiece==-1){
            possitionPossible[1][0]=1;
            return possitionPossible;
        }
        if(numPiece==2 || numPiece==4 || numPiece==5){
            possitionPossible[0][2]=1;
            possitionPossible[1][2]=1;
            possitionPossible[2][2]=1;
            possitionPossible[0][1]=1;
            possitionPossible[2][1]=1;
            possitionPossible[1][0]=1;
            return possitionPossible;
        }
        if(numPiece==-2 || numPiece==-4 || numPiece==-5){
            possitionPossible[0][0]=1;
            possitionPossible[1][0]=1;
            possitionPossible[2][0]=1;
            possitionPossible[0][1]=1;
            possitionPossible[2][1]=1;
            possitionPossible[1][2]=1;
            return possitionPossible;
        }
        if(numPiece==3){
            possitionPossible[0][2]=1;
            possitionPossible[1][2]=1;
            possitionPossible[2][2]=1;
            possitionPossible[0][0]=1;
            possitionPossible[0][2]=1;
            return possitionPossible;
        }
        if(numPiece==-3){
            possitionPossible[0][0]=1;
            possitionPossible[1][0]=1;
            possitionPossible[2][0]=1;
            possitionPossible[2][0]=1;
            possitionPossible[2][2]=1;
            return possitionPossible;
        }
        if(numPiece==6 || numPiece==-6){
            possitionPossible[0][0]=1;
            possitionPossible[1][0]=1;
            possitionPossible[2][0]=1;
            possitionPossible[0][1]=1;
            possitionPossible[1][1]=1;
            possitionPossible[2][1]=1;
            possitionPossible[0][2]=1;
            possitionPossible[1][2]=1;
            possitionPossible[2][2]=1;
            return possitionPossible;
        }
        return possitionPossible;
    }

    public int[][] getMovePossible(int[] numPiece){
        int[][] mouvementPossible;
        int[][] possitionPossible=new int[5][6];

        mouvementPossible=getMovePiece( tab[ numPiece[0] ][ numPiece[1] ] );

        for (int x=0;x<3;x++){
            for (int y=0;y<3;y++){
                if(mouvementPossible[x][y]==1){
                    if( tab[ numPiece[0] ][ numPiece[1] ] > 0){
                        if(numPiece[0]+x-1>=0 && numPiece[0]+x-1<5 && numPiece[1]+y-1>=0 && numPiece[1]+y-1<6) {
                            if (tab[numPiece[0] + x - 1][numPiece[1] + y - 1] <= 0) {
                                possitionPossible[numPiece[0] + x - 1][numPiece[1] + y - 1] = 1;
                            }
                        }
                    }
                    else{
                        if(numPiece[0]+x-1>=0 && numPiece[0]+x-1<5 && numPiece[1]+y-1>=0 && numPiece[1]+y-1<6) {
                            if (tab[numPiece[0] + x - 1][numPiece[1] + y - 1] >= 0) {
                                possitionPossible[numPiece[0] + x - 1][numPiece[1] + y - 1] = 1;
                            }
                        }
                    }
                }
            }
        }

        return possitionPossible;
    }

    public int[][] placePieceDeCimetiere(int numPiece){
        int[][] tabPlaceOK=new int[5][6];
        for (int x=0;x<5;x++){
            for(int y=0;y<6;y++){
                if(tab[x][y]==0){
                    tabPlaceOK[x][y]=1;
                }
                else
                    tabPlaceOK[x][y]=0;
            }
        }
        if(numPiece==1){
            for (int x=0;x<5;x++){
                for(int y=0;y<6;y++){
                    if(tab[x][y]==1){
                        tabPlaceOK[x][0]=0;
                        tabPlaceOK[x][1]=0;
                        tabPlaceOK[x][2]=0;
                        tabPlaceOK[x][3]=0;
                        tabPlaceOK[x][4]=0;
                        tabPlaceOK[x][5]=0;
                    }
                }
            }
        }
        if(numPiece==-1 ){
            for (int x=0;x<5;x++){
                for(int y=0;y<6;y++){
                    if(tab[x][y]==-1){
                        tabPlaceOK[x][0]=0;
                        tabPlaceOK[x][1]=0;
                        tabPlaceOK[x][2]=0;
                        tabPlaceOK[x][3]=0;
                        tabPlaceOK[x][4]=0;
                        tabPlaceOK[x][5]=0;
                    }
                }
            }
        }

        return tabPlaceOK;
    }

    public void mettreDepuisCimetiere(int nbPiece,int valPiece,int[] coord){
        tab[coord[0]][coord[1]]=valPiece;
        cimetiereJoueur1.remove(nbPiece);
        tour++;
    }

    public int[] getRoi(){
        int[][] casePossibleRoi = new int[5][6];
        int[][] cassePossibleAssayant;
        int xRoi = -1;
        int yRoi = -1;

        if(joueur==0){
            for(int x=0;x<5;x++){
                for(int y=0;y<6;y++){
                    if (tab[x][y] == 6) {
                        casePossibleRoi= getMovePossible(new int[]{x,y});
                        xRoi = x;
                        yRoi = y;


                    }
                }
            }

            for(int x=0;x<5;x++){
                for(int y=0;y<6;y++){
                    if(casePossibleRoi[x][y]==1 && tab[x][y] < 0){
                        cassePossibleAssayant = getMovePossible(new int[] {x,y});
                        if(cassePossibleAssayant[xRoi][yRoi]==1){
                            return new int[]{xRoi,yRoi};
                        }
                    }
                }
            }
        }
        if(joueur==1){
            for(int x=0;x<5;x++){
                for(int y=0;y<6;y++){
                    if (tab[x][y]== -6) {
                        casePossibleRoi= getMovePossible(new int[]{x,y});
                        xRoi = x;
                        yRoi = y;
                    }
                }
            }

            for(int x=0;x<5;x++){
                for(int y=0;y<6;y++){
                    if(casePossibleRoi[x][y]==1 && tab[x][y] > 0){
                        cassePossibleAssayant = getMovePossible(new int[] {x,y});
                        if(cassePossibleAssayant[xRoi][yRoi]==1){
                            return new int[]{xRoi,yRoi};
                        }
                    }
                }
            }
        }
        return new int[]{-1,-1};
    }


    public int[][] getTab() {
        return tab;
    }
}
