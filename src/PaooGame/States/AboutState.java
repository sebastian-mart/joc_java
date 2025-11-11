package PaooGame.States;

import PaooGame.RefLinks;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;



/*! \class public class AboutState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class AboutState extends State
{
    /*! \fn public AboutState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public AboutState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniu about.
     */
    @Override
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniu about.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.setColor(new Color(118,175,237));
        g.fillRect(0,0,refLink.GetWidth(),refLink.GetWidth());

        Image button_image,button_image1=null;
        try{
            button_image = ImageIO.read(new File("res/menu/button_rectangle_depth_gradient.png"));
            button_image1 = button_image.getScaledInstance(300,60,0);
            ///scaleaza imaginea pt buton

        }catch(IOException e){
            System.out.print("Fisier negasit\n");
        }

        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial",Font.BOLD,40));
        g.drawString("About Game",500,120);

        g.setColor(Color.black);
        g.drawString("Developers:",200,200);

        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("Mirt Sebastian",250,250);
        g.drawString("Mihalcut Marian-Madalin",250,300);

        int x_button_image=480;
        int y_button_image=550;

        g.setFont(new Font("Calibri",Font.BOLD,30));
        g.setColor(Color.black);

        g.drawImage(button_image1,x_button_image,y_button_image,null);
        g.drawString("Back to menu",(x_button_image+300)/2+150,y_button_image+35);


    }

    @Override
    public void InitHandlers() {
        Canvas canvas = refLink.GetGame().GetGameWindow().GetCanvas();
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                int x_button = 480;
                int y_button = 550;

                int w_button = 300;
                int h_button = 60;

                if(x>=x_button && x<=x_button+w_button && y>=y_button && y<=y_button+h_button){
                    State menuState = refLink.GetGame().GetMenuState();
                    State.SetState(menuState);
                    canvas.removeMouseListener(this);
                }
            }
        });
    }
}
