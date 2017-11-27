import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlateauGraphic extends JPanel {


    private ImageIcon imgPlateauLoaded;
    private Image imgPlateau;

    private ImageIcon imgKodamaLoaded;
    private ImageIcon imgKodamaLoaded2;
    private ImageIcon imgKirinLoaded;
    private ImageIcon imgKirinLoaded2;
    private ImageIcon imgOniLoaded;
    private ImageIcon imgOniLoaded2;
    private ImageIcon imgKoropokkuruLoaded;
    private ImageIcon imgKoropokkuruLoaded2;
    private ImageIcon imgBordure;
    private ImageIcon imgBordure2;

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
    private Sprite caseEchec;

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
        imgKodamaLoaded2 = new ImageIcon(new ImageIcon("kodama2.png").getImage().getScaledInstance(LARGEUR_PIECE,HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgKirinLoaded = new ImageIcon(new ImageIcon("kirin.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgKirinLoaded2 = new ImageIcon(new ImageIcon("kirin2.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgOniLoaded = new ImageIcon(new ImageIcon("oni.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgOniLoaded2 = new ImageIcon(new ImageIcon("oni2.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgKoropokkuruLoaded = new ImageIcon(new ImageIcon("koropokkuru.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgKoropokkuruLoaded2 = new ImageIcon(new ImageIcon("koropokkuru2.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgBordure = new ImageIcon(new ImageIcon("bordure.png").getImage().getScaledInstance(largeurCase, hauteurCase, Image.SCALE_DEFAULT));
        imgBordure2 = new ImageIcon(new ImageIcon("bordure2.png").getImage().getScaledInstance(largeurCase, hauteurCase, Image.SCALE_DEFAULT));


        imgPlateau = img;

        caseEchec=null;

    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        //* optimisation 2d
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(imgPlateau, 0,0,null);
         //   initPlateau(g2);
            for (Sprite s:listeSprite){
                g2.drawImage(s.getImage(), largeurCase*s.getX()+(int)(bordureX/1.2), hauteurCase*s.getY()+(int)(bordureY/1.7), null);
            }
            for (Sprite s:listeCasePossible){
                g2.drawImage(s.getImage(), largeurCase*s.getX()+(int)(bordureX/2), hauteurCase*s.getY()+(int)(bordureY/2.1), null);

            }
            if(caseEchec!=null) {
                g2.drawImage(caseEchec.getImage(), largeurCase * caseEchec.getX() + (int) (bordureX / 2), hauteurCase * caseEchec.getY() + (int) (bordureY / 2.1), null);
                System.out.println("azaezaezaezaeza");
            }
            listeCasePossible.clear();
        System.out.println(listeSprite.size());


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
                            case 2: imagePiece = imgKodamaLoaded2; break;
                            case 3: imagePiece = imgOniLoaded2; break;
                            case 4: imagePiece = imgOniLoaded2; break;
                            case 5: imagePiece = imgKirinLoaded2; break;
                            case 6: imagePiece = imgKoropokkuruLoaded2; break;
                                //* J2
                            case -1: imagePiece = imgKodamaLoaded; break;
                            case -2: imagePiece = imgKodamaLoaded; break;
                            case -3: imagePiece = imgOniLoaded; break;
                            case -4: imagePiece = imgOniLoaded; break;
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
        listeCasePossible.clear();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++){
                if (tab[i][j] == 1){
                    listeCasePossible.add(new Sprite(i,j,imgBordure.getImage()));
                }
            }
        }
    }

    public void afficheCaseEchec(int[] numCase){
        caseEchec = null;
        caseEchec=new Sprite(numCase[0],numCase[1],imgBordure2.getImage());
    }

    public void setNullCaseEchec(){
        caseEchec=null;
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
