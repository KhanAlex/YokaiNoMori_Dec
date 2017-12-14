import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CimetiereGraphic extends JPanel {



    private ImageIcon imgPlateauLoaded;
    private Image imgPlateau;

    private ImageIcon imgKodamaLoaded;
    private ImageIcon imgSuperKodamaLoaded;
    private ImageIcon imgKirinLoaded;
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
    private ImageIcon imgSelect;



    private final int LARGEUR_PIECE = 90;
    private final int HAUTEUR_PIECE = 90;

    private List<Sprite> listePiece;
    private Sprite pieceSelec;




    public CimetiereGraphic(Image img, Fenetre fenetre){




        imgKodamaLoaded = new ImageIcon(new ImageIcon("kodama.png").getImage().getScaledInstance(LARGEUR_PIECE,HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgSuperKodamaLoaded = new ImageIcon(new ImageIcon("kodamasamurai.png").getImage().getScaledInstance(LARGEUR_PIECE,HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgKirinLoaded = new ImageIcon(new ImageIcon("kirin.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgOniLoaded = new ImageIcon(new ImageIcon("oni.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgKoropokkuruLoaded = new ImageIcon(new ImageIcon("koropokkuru.png").getImage().getScaledInstance(LARGEUR_PIECE, HAUTEUR_PIECE, Image.SCALE_DEFAULT));
        imgSelect = new ImageIcon(new ImageIcon("border.png").getImage().getScaledInstance(135, 135, Image.SCALE_DEFAULT));



        imgPlateau = img;

        listePiece = new ArrayList<Sprite>();
        pieceSelec = null;


    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        //* optimisation 2d
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(imgPlateau, 0,0,null);

        for (Sprite s:listePiece){
            g2.drawImage(s.getImage(), s.getX()*100+30,s.getY()*100,null);
        }
        if (pieceSelec != null){
            g2.drawImage(pieceSelec.getImage(), pieceSelec.getX()*100+30,pieceSelec.getY()*100,null);
            g2.drawImage(imgSelect.getImage(), pieceSelec.getX()*100+13,(pieceSelec.getY()*100)-18,null);

            pieceSelec = null;

        }




    }

    public void actualiserCimetiere(List<Integer> listePieceMorte){
        listePiece.clear();
        int x = 0;
        int y = 0;
        for (int piece : listePieceMorte){
            if (x >= 5){
                y++;
                x = 0;
            }

            if (piece < 0)
                piece *=-1;
            ImageIcon imagePiece = null;

            switch(piece){
                //* J1
                case 1: imagePiece = imgKodamaLoaded; break;
                case 2: imagePiece = imgSuperKodamaLoaded; break;
                case 3: imagePiece = imgOniLoaded; break;
                case 4: imagePiece = imgSuperOniLoaded; break;
                case 5: imagePiece = imgKirinLoaded; break;
                case 6: imagePiece = imgKoropokkuruLoaded; break;

            }
            Sprite newSprite = new Sprite(x,y,imagePiece.getImage());
            listePiece.add(newSprite);
            x++;
        }
    }//*fe
    public void metEnValeur(int x, int y, int indiceListe){
        if (indiceListe >= listePiece.size()){
            System.out.println("out ouf bound");
        }else{

            Image last = listePiece.get(indiceListe).getImage();
            ImageIcon img = new ImageIcon(new ImageIcon(last).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            pieceSelec = new Sprite(x,y,img.getImage());

        }
    }

}
