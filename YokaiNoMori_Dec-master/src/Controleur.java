/**
 * Created by bcontrer on 04/10/17.
 */
public class Controleur {

    public Controleur(Plateau p) {
        Fenetre f = new Fenetre(p);
        ControlMouse cm = new ControlMouse(p,f);
        CimetiereController cim1 = new CimetiereController(p,f);
        CimetiereController cim2 = new CimetiereController(p,f, 2);//*f
        new ControlMenu(p,f,cm, cim1,cim2);
        f.setVisible(true);
    }
}
