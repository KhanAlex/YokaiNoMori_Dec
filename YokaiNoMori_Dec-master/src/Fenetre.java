import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;

/**
 * Created by bcontrer on 04/10/17.
 */
public class Fenetre extends JFrame {

    //* Composants graphiques
    //* Panel principal
    private JPanel fenetre, imagePlateau, container1, container2;
    private CimetiereGraphic cim1, cim2;
    //* Menu
    private JMenuBar menuBar;
    private JMenu menuPrincipal, menuAutre;
    private JMenuItem quitItem, resizeItem, newgameItem;
    //* Autres
    private Plateau plateau;
    private boolean petiteFenetre;
    private int xFenetre;
    private int yFenetre;
    //* cimetière
    private JFrame cimetiere1, cimetiere2;



    public Fenetre(Plateau plateau){
        this.petiteFenetre = true;
        this.xFenetre = 553;
        this.yFenetre = 850;
        this.plateau = plateau;
        //* Création fenêtre
        creerFenetre();
        initMenu();
        addToWindow();
        pack();

     //   setSize(this.xFenetre, this.yFenetre);
        setTitle("Yokaï no mori");
        initCimetiere();
        initCimetiere2();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        setLocation(550,0);
        cimetiere1.setLocation(0,500);
        cimetiere2.setLocation(1100,50);
    }

    //* Méthodes pour créer la fenêtre
    public void creerFenetre() {
        //* Création de tout les composants graphiques de la fenetre ( sauf Menu )

        //* Création de l'image de fond ( plateau )
        ImageIcon imageIcon =new ImageIcon( "fond2.png");
        ImageIcon imgNull =new ImageIcon( "fond3.png"); //* taille mini fenetre

        this.imagePlateau = new JPanel();
        this.imagePlateau = new PlateauGraphic(resizeWindow(imageIcon, xFenetre, yFenetre).getImage(), plateau, this);
        JLabel image = new JLabel(resizeWindow(imgNull, xFenetre, yFenetre));
       this.imagePlateau.add(image);

    }
    private int popUp(String titre, String message, ImageIcon image, int type){
        JOptionPane popUp = new JOptionPane();
        popUp.setFocusable(true);
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
            case 1: imagePiece = ((PlateauGraphic)getImagePlateau()).getImgSuperKodamaLoaded(); break;
            case 3: imagePiece = ((PlateauGraphic)getImagePlateau()).getImgSuperOniLoaded(); break;

        }//*jkdde
        int reponse = popUp("Promotion", "Voulez vous faire évoluer la pièce ?", imagePiece, 1);
        if (reponse != 0)
            return false;
        return true;
    }
    public void showWinner(int numVainqueur){
        popUp("Victoire", ("Le joueur"+String.valueOf(numVainqueur)+" a gagné ! "), ((PlateauGraphic)getImagePlateau()).getImageVictoire(), 0);
    }

    public void initMenu() {
        //* Création des componsants du menu

        //* Barre de menu *\\
        this.menuBar = new JMenuBar();

        //*  menu *\\
        this.menuPrincipal = new JMenu("Option");
        this.menuAutre = new JMenu("Aide");

        //* sous menu *\\
        this.newgameItem = new JMenuItem("Nouvelle partie");
        this.resizeItem = new JMenuItem("Changer la taille");
        this.quitItem = new JMenuItem("Quitter");

        //*  Ajout des items *\\

        this.menuPrincipal.add(this.newgameItem);
        this.menuPrincipal.add(this.resizeItem);
        this.menuPrincipal.addSeparator();
        this.menuPrincipal.add(this.quitItem);

        this.menuBar.add(this.menuPrincipal);
        this.menuBar.add(this.menuAutre);

        setJMenuBar(this.menuBar);

    }
    public void initCimetiere(){
        /* Ouvrir nouvelle fenêtre */
        //* cimetière joueur 1
        ImageIcon imgNull =new ImageIcon( "fond3.png"); //* taille mini fenetre
        ImageIcon imgFond2 = resizeWindow(new ImageIcon("cimetiere.jpg"),570,520);
        JLabel image = new JLabel(resizeWindow(imgNull, 550, 500));
        //* cimetière joueur 2

        //* cimetiere 1
        cimetiere1 = new JFrame("Cimetière joueur 1");
        cimetiere1.setSize(500, 500);
        cim1 = new CimetiereGraphic(imgFond2.getImage(),this);
        cimetiere1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        cimetiere1.setVisible(true);
        cim1.add(image);
        container1 = new JPanel();
        container1.add(cim1);
        cimetiere1.setContentPane(container1);
        cimetiere1.setResizable(false);




    }

    public void removeControlClick(ControlMouse controlMouse){
        this.imagePlateau.removeMouseListener(controlMouse);
        this.cim1.removeMouseListener(controlMouse);
        this.cim2.removeMouseListener(controlMouse);
    }

    public void initCimetiere2(){
        ImageIcon imgFond = resizeWindow(new ImageIcon("cimetiere.jpg"),570,520);
        ImageIcon imgNull =new ImageIcon( "fond3.png"); //* taille mini fenetre

        JLabel image = new JLabel(resizeWindow(imgNull, 550, 500));

        cimetiere2 = new JFrame("Cimetière joueur 2");
        cim2 = new CimetiereGraphic(imgFond.getImage(),this);
        cimetiere2.setSize(550, 500);
        cimetiere2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        cimetiere2.setVisible(true);
        cim2.add(image);
        container2 = new JPanel();
        container2.add(cim2);
        cimetiere2.setContentPane(container2);
        cimetiere2.setResizable(false);
    }
    public void addToWindow() {
        //* Ajouts des composants graphique dans la fenêtre principales
        this.fenetre = new JPanel();
        this.fenetre.add(this.imagePlateau);
        setContentPane(this.fenetre);
    }

public ImageIcon resizeWindow(ImageIcon imageIcon, int width, int height){
    //   this.xFenetre = ((int) (1550)/2)-247;
    //   this.yFenetre = ((int) 880)-50; //* 247

    Image img = imageIcon.getImage();
    Image imgResize = img.getScaledInstance((int)(width),(int)(height),Image.SCALE_DEFAULT);
    imageIcon=new ImageIcon(imgResize);

    return imageIcon;
}


    //* Méthodes pour initialiser les controleurs
    public void  setControlMenu(ControlMenu cm){
        //* initialiser le controlMenu
        this.quitItem.addActionListener(cm);//*e
        newgameItem.addActionListener(cm);
    }
    public void setCimetiereController(CimetiereController cc){
        cim1.addMouseListener(cc);
    }
    public void setCimetiereController2(CimetiereController cc){
        cim2.addMouseListener(cc);
    }

    public void setControlClick(ControlMouse controlMouse) {
        //* initialiser le ControlClick
        this.imagePlateau.addMouseListener(controlMouse);
    }
    public void newGame(ControlMouse last, CimetiereController cim1, CimetiereController cim2){
        imagePlateau.removeMouseListener(last);
        this.cim1.removeMouseListener(cim1);
        this.cim2.removeMouseListener(cim2);
    }
    public void update(){
        imagePlateau.repaint();
    }

    //* ...

    //* GETTERS && SETTERS


    public boolean isPetiteFenetre() {
        return petiteFenetre;
    }

    public void setPetiteFenetre(boolean petiteFenetre) {
        this.petiteFenetre = petiteFenetre;
    }

    public int getxFenetre() {
        return xFenetre;
    }

    public void setxFenetre(int xFenetre) {
        this.xFenetre = xFenetre;
    }

    public int getyFenetre() {
        return yFenetre;
    }

    public void setyFenetre(int yFenetre) {
        this.yFenetre = yFenetre;
    }

    public JPanel getImagePlateau() {
        return imagePlateau;
    }

    public void setImagePlateau(JPanel imagePlateau) {
        this.imagePlateau = imagePlateau;
    }

    public CimetiereGraphic getCim1() {
        return cim1;
    }

    public void setCim1(CimetiereGraphic cim1) {
        this.cim1 = cim1;
    }

    public CimetiereGraphic getCim2() {
        return cim2;
    }

    public void setCim2(CimetiereGraphic cim2) {
        this.cim2 = cim2;
    }

    public JFrame getCimetiere1() {
        return cimetiere1;
    }

    public void setCimetiere1(JFrame cimetiere1) {
        this.cimetiere1 = cimetiere1;
    }

    public JFrame getCimetiere2() {
        return cimetiere2;
    }

    public void setCimetiere2(JFrame cimetiere2) {
        this.cimetiere2 = cimetiere2;
    }
}
