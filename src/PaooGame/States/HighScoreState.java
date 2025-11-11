package PaooGame.States;

import PaooGame.Database;
import PaooGame.RefLinks;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class HighScoreState extends State
{
    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public HighScoreState(RefLinks refLink)
    {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
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
        g.setFont(new Font("Consolas",Font.BOLD,40));
        g.drawString("High Scores",500,120);

        g.setColor(Color.black);
        String[] scores = (new Database()).getHighScores().split("\n"); // Split in array
        int yPos = 200; // pozitie start Y

        for (String score : scores) {
            g.drawString(score, 500, yPos);
            yPos += 40; // Creste Y pentru fiecare linie
        }

        int x_button_image=480;
        int y_button_image=550;

        g.setFont(new Font("Consolas",Font.BOLD,30));
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
