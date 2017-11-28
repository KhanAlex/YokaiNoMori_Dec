/**
 * Created by bcontrer on 04/10/17.
 */
public class Controleur {

    public Controleur(Plateau p) {
        Fenetre f = new Fenetre(p);
        new ControlMouse(p,f);
        new ContreolMenu(p,f);
        new CimetiereController(p,f);
        new CimetiereController(p,f, 2);//*f
        f.setVisible(true);
    }
}
