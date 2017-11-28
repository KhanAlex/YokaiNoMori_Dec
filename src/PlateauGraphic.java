import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlateauGraphic extends JPanel {


    private ImageIcon imgPlateauLoaded;
    private Image imgPlateau;

    private ImageIcon imgKodamaLoaded;
    private ImageIcon imgKodamaLoaded2;
    private ImageIcon imgSuperKodamaLoaded;
    private ImageIcon imgSuperKodamaLoaded2;
    private ImageIcon imgKirinLoaded;
    private ImageIcon imgKirinLoaded2;
    private ImageIcon imgOniLoaded;
    private ImageIcon imgSuperOniLoaded;
    private ImageIcon imgOniLoaded2;
    private ImageIcon imgSuperOniLoaded2;
    private ImageIcon imgKoropokkuruLoaded;
    private ImageIcon imgKoropokkuruLoaded2;
    private ImageIcon imgBordure;
    private ImageIcon imgBordure2;
    private ImageIcon imgBordure3;
    private ImageIcon imagePromotion;
    private ImageIcon imageVictoire;
    private ImageIcon imageArrow;
    private ImageIcon imageArrow2;

    private String path;
    private Plateau plateau;
    private Fenetre fenetre;

    private int bordureX;
    private int largeurCase;
    private int bordureY;
    private int hauteurCase;
    private final int LARGEUR_PIECE = 80;
    private final int HAUTEUR_PIECE = 80;

    private List<Sprite> listeSprite;
    private List<Sprite> listeCasePossible;
    private Sprite currentJoueur;
    private Sprite currentPiece;

    private boolean debutPartie;


    public PlateauGraphic(Image img, Plateau plateau, Fenetre fenetre){

        this.plateau = plateau;
        this.fenetre = fenetre;
        debutPartie  =true;
        listeSprite = new ArrayList<Sprite>();
        listeCasePossible = new ArrayList<Sprite>();


        bordureX = (int)((7.7*(fenetre.getxFenetre()))/100); //* 7.7
        largeurCase = (fenetre.getxFenetre()-(bordureX)) / 5;
        bordureY = (int)((21.1*(fenetre.getyFenetre()))/100);
        hauteurCase = (fenetre.getyFenetre()-(bordureY)) / 6;


        imgKodamaLoaded = new ImageIcon(new ImageIcon("kodama.png").getImage().getScaledInstance(LARGEUR_PIECE,HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgSuperKodamaLoaded = new ImageIcon(new ImageIcon("kodamasamurai.png").getImage().getScaledInstance(LARGEUR_PIECE,HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgSuperKodamaLoaded2 = new ImageIcon(new ImageIcon("kodamasamurai.png").getImage().getScaledInstance(LARGEUR_PIECE,HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgKodamaLoaded2 = new ImageIcon(new ImageIcon("kodama2.png").getImage().getScaledInstance(LARGEUR_PIECE,HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgKirinLoaded = new ImageIcon(new ImageIcon("kirin.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgKirinLoaded2 = new ImageIcon(new ImageIcon("kirin2.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgOniLoaded = new ImageIcon(new ImageIcon("oni.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgSuperOniLoaded = new ImageIcon(new ImageIcon("superoni.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgSuperOniLoaded2 = new ImageIcon(new ImageIcon("superoni.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgOniLoaded2 = new ImageIcon(new ImageIcon("oni2.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgKoropokkuruLoaded = new ImageIcon(new ImageIcon("koropokkuru.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgKoropokkuruLoaded2 = new ImageIcon(new ImageIcon("koropokkuru2.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgBordure = new ImageIcon(new ImageIcon("bordure.png").getImage().getScaledInstance(largeurCase, hauteurCase, Image.SCALE_DEFAULT));
        imgBordure2 = new ImageIcon(new ImageIcon("bordure2.png").getImage().getScaledInstance(largeurCase, hauteurCase, Image.SCALE_DEFAULT));
        imgBordure3 = new ImageIcon(new ImageIcon("bordure3.png").getImage().getScaledInstance(largeurCase, hauteurCase, Image.SCALE_DEFAULT));
        imagePromotion = new ImageIcon(new ImageIcon("promo.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        imageVictoire = new ImageIcon(new ImageIcon("victoire.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        imageArrow = new ImageIcon(new ImageIcon("arrow.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        imageArrow2 = new ImageIcon(new ImageIcon("arrow2.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));



        imgPlateau = img;
        currentJoueur = null;
        currentPiece = null;


    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        //* optimisation 2d
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(imgPlateau, 0,0,null);
        //* Liste des pièces à afficher
            for (Sprite s:listeSprite){
                g2.drawImage(s.getImage(), largeurCase*s.getX()+(int)(bordureX/1.2), hauteurCase*s.getY()+(int)(bordureY/1.7), null);
            }
        //* Liste des déplacement possible
            for (Sprite s:listeCasePossible){
                g2.drawImage(s.getImage(), largeurCase*s.getX()+(int)(bordureX/2.00), hauteurCase*s.getY()+(int)(bordureY/2), null);

            }
            listeCasePossible.clear();
        //*
        if (currentJoueur != null)
            g2.drawImage(currentJoueur.getImage(), currentJoueur.getX(),currentJoueur.getY(), null);
        if (currentPiece != null){
            g2.drawImage(currentPiece.getImage(), largeurCase*currentPiece.getX()+(int)(bordureX-15), hauteurCase*currentPiece.getY()+(int)(bordureY/1.8), null);
            currentPiece = null;
        }




    }

    public void tableauToSprite(int[][] tab){
        //* Destruction des anciens Sprite
        listeSprite.clear();
            for (int i = 0; i < tab.length; i++){
                for (int j = 0; j < tab[i].length; j++){
                    int piece = tab[i][j];
                    if (piece != 0){ //* si on a une pièce sur la case [i][j]
                        ImageIcon imagePiece = imgKodamaLoaded; //* default value

                        switch(piece){
                                //* J1
                            case 1: imagePiece = imgKodamaLoaded2; break;
                            case 2: imagePiece = imgSuperKodamaLoaded2; break;
                            case 3: imagePiece = imgOniLoaded2; break;
                            case 4: imagePiece = imgSuperOniLoaded2; break;
                            case 5: imagePiece = imgKirinLoaded2; break;
                            case 6: imagePiece = imgKoropokkuruLoaded2; break;
                                //* J2
                            case -1: imagePiece = imgKodamaLoaded; break;
                            case -2: imagePiece = imgSuperKodamaLoaded; break;
                            case -3: imagePiece = imgOniLoaded; break;
                            case -4: imagePiece = imgSuperOniLoaded; break;
                            case -5: imagePiece = imgKirinLoaded; break;
                            case -6: imagePiece = imgKoropokkuruLoaded; break;
                        }
                        //* Création du nouveau sprite
                        Sprite spritePiece = new Sprite(i,j,imagePiece.getImage());
                        //* Ajout du Sprite dans la liste de Sprite à afficher
                        listeSprite.add(spritePiece);

                    }
                }
            }
    }
    public void afficheCasePossible(int[][] tab){
        //askPromotion(3);
        //showWinner(1);
        listeCasePossible.clear();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++){
                if (tab[i][j] == 1){
                    if (isEnemy(i,j))
                        listeCasePossible.add(new Sprite(i,j,imgBordure3.getImage()));
                    else
                        listeCasePossible.add(new Sprite(i,j,imgBordure.getImage()));
                }
            }
        }
    }

    private boolean isEnemy(int x, int y){
        for (Sprite s:listeSprite){
            if (s.getX() == x && s.getY() == y)
                return true;
        }
        return false;
    }
    private int popUp(String titre, String message, ImageIcon image, int type){
        JOptionPane popUp = new JOptionPane();
        int option = -10;
        if (type == 0){
            popUp.showMessageDialog(null, message, titre, JOptionPane.INFORMATION_MESSAGE, image);
        }else if(type == 1){
             option = popUp.showConfirmDialog(null, message, titre, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image);
        }
        return option;
    }
    public boolean askPromotion(int piece){
        ImageIcon imagePiece = null;
        if (piece < 0)
            piece*=-1;
        switch(piece){
            //* J1
            case 1: imagePiece = imgSuperKodamaLoaded; break;
            case 3: imagePiece = imgSuperOniLoaded; break;

        }//*jkdde
        int reponse = popUp("Promotion", "Voulez vous faire évoluer la pièce ?", imagePiece, 1);
        if (reponse != 0)
            return false;
        return true;
    }
    public void showWinner(int numVainqueur){
        popUp("Victoire", ("Le joueur"+String.valueOf(numVainqueur)+" a gagné ! "), imageVictoire, 0);
    }
    public void afficheQuiJoue(int tour){
        if (tour%2 == 0){
            currentJoueur = new Sprite(85,20,imageArrow2.getImage());
        }else{
            currentJoueur = new Sprite(415,770,imageArrow.getImage());

        }
    }
    public void metEnValeurPiece(int[] tab){
        Sprite piece = chercheSprite(tab[0],tab[1]);
        if (piece != null){
            Image last = piece.getImage();
            ImageIcon img = new ImageIcon(new ImageIcon(last).getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
            currentPiece = new Sprite(piece.getX(),piece.getY(), img.getImage());
           // listeSprite.remove(piece);
        }
    }
    private Sprite chercheSprite(int x, int y){
        for (Sprite s:listeSprite){
            if (s.getX() == x && s.getY() == y)
                return s;
        }
        return null;
    }



    public int getBordureX() {
        return bordureX;
    }

    public void setBordureX(int bordureX) {
        this.bordureX = bordureX;
    }

    public int getLargeurCase() {
        return largeurCase;
    }

    public void setLargeurCase(int largeurCase) {
        this.largeurCase = largeurCase;
    }

    public int getBordureY() {
        return bordureY;
    }

    public void setBordureY(int bordureY) {
        this.bordureY = bordureY;
    }

    public int getHauteurCase() {
        return hauteurCase;
    }

    public void setHauteurCase(int hauteurCase) {
        this.hauteurCase = hauteurCase;
    }
}
