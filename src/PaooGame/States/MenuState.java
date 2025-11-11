package PaooGame.States;

import PaooGame.Database;
import PaooGame.GameSave;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;
import org.w3c.dom.css.RGBColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.List;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{
    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        InitHandlers();
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        //1280x960
        g.setColor(new Color(118,175,237));
        g.fillRect(0,0,refLink.GetWidth(),refLink.GetHeight());
        //avem getState si setState
        Image button_image ,button_image1=null;
        try{
            button_image = ImageIO.read(new File("res/menu/button_rectangle_depth_gradient.png"));
            button_image1 = button_image.getScaledInstance(300,60,0);
            ///scaleaza imaginea pt buton

        }catch(IOException e){
            System.out.print("Fisier negasit\n");
        }

        ///setare text deasupra
        g.setColor(Color.RED);
        g.setFont(new Font("Consolas",Font.BOLD,50));
        g.drawString("Calatoria pierduta",390,120);

        /// coordonatele initiale ale casutei
        int x_button_image=480;
        int y_button_image=250;

        /// setare font si culoare text butoane
        g.setFont(new Font("Calibri",Font.BOLD,30));
        g.setColor(Color.black);

        for(int i=1;i<=5;i++)
        {
            /// desenare butoane
            g.drawImage(button_image1,x_button_image,y_button_image,null);

            /// includere text
            String text = switch (i) {
                case 1 -> "New Game";
                case 2 -> "Load Game";
                case 3 -> "High Score";
                case 4 -> "About";
                case 5 -> "Exit";
                default -> null;
            };
            /// +50 si +35 sunt ajustari pe coordonate ca sa fie cat de cat centrate
            g.drawString(text,(x_button_image+300)/2+150,y_button_image+35);

            /// coordonata y_image se va mari cu 70(60 de la inalt. imaginii si 20 de spatiu)
            y_button_image += 80;
        }

    }

    @Override
    public void InitHandlers() {
        /// se va folosi canvas pt a gasi pozitia mouse-ului
        Canvas canvas = refLink.GetGame().GetGameWindow().GetCanvas();
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                /// x si y dau coordonatele locului unde s-a produs evenimentul
                int x = e.getX();
                int y = e.getY();

                /// coordonatele de plecare ale primului buton
                int x_button = 480;
                int y_button = 250;

                /// inaltime si lungime buton
                int w_button = 300;
                int h_button = 60;

                /// Stari ale meniului

                for(int i=1;i<=5;i++)
                {
                    if(x>=x_button && x<=x_button+w_button && y>=y_button && y<=y_button+h_button)
                    {
                        switch (i){
                            /// Starea New Game
                            case 1:
                                canvas.removeMouseListener(this);
                                createNewGameSave();
                                State playState = refLink.GetGame().GetPlayState();
                                ((PlayState)playState).applyGameSave();
                                State.SetState(playState);

                                break;
                            /// Starea Load Game
                            case 2:
                                canvas.removeMouseListener(this);
                                GameSave loadedSave = new Database().readTable();
                                if(loadedSave != null) {
                                    refLink.SetGameSave(loadedSave);
                                    State playState1 = refLink.GetGame().GetPlayState();
                                    ((PlayState)playState1).applyGameSave();
                                    State.SetState(playState1);
                                }else{
                                    createNewGameSave();
                                    State playState2 = refLink.GetGame().GetPlayState();
                                    ((PlayState)playState2).applyGameSave();
                                    State.SetState(playState2);
                                }

                                break;
                            /// Starea High Score
                            case 3:
                                State HighScore = refLink.GetGame().GetHighScoreState();
                                State.SetState(HighScore);
                                canvas.removeMouseListener(this);
                                break;
                            /// Starea About
                            case 4:
                                State aboutState = refLink.GetGame().GetAboutState();
                                State.SetState(aboutState);
                                canvas.removeMouseListener(this);
                                break;
                            /// Starea Exit
                            case 5:
                                System.exit(0);
                                break;

                        }
                    }
                    y_button+=80;
                }
                ///
            }
        });
    }
    public void createNewGameSave() {
        // Valori implicite pentru un joc nou
        int spawnX = 128;  // Exemplu - coordonatele de start din Level1
        int spawnY = 640;
        boolean[] initialWeapons = {true, false, false, false};
        boolean[] initialItems = {true, true, true, true,true, true, true, true,true,true};
        boolean[] initialNpc = {true, true, true, true,true, true, true, true,true,true};
        int score = 0;

        GameSave initialSave = new GameSave(
                "Jucător",
                spawnX,//64*185
                spawnY,
                100,  // Health inițial
                0,    // Prima armă
                initialWeapons,
                1,     // Nivelul 1
                initialItems,
                initialNpc,
                score
        );

        refLink.SetGameSave(initialSave);
        new Database().insertTable(initialSave);
    }
}

