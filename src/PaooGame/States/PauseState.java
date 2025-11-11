package PaooGame.States;

import PaooGame.Database;
import PaooGame.RefLinks;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class PauseState extends State{
    private boolean saved=false;
    public PauseState(RefLinks refLink){
        super(refLink);
        InitHandlers();
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {
        refLink.GetGame().GetPlayState().Draw(g);

        int width=400,height=400;

        /// va fi punctul din mijlocul ecranului de unde se scade 200
        /// ,200 fiind jumatate de lungime a background-ului acestui meniu
        int coordX = refLink.GetWidth()/2-200;
        int coordY = refLink.GetHeight()/2-200;

        g.setColor(new Color(0,0,0,150));
        g.fillRect(0,0, refLink.GetWidth(), refLink.GetHeight());

        /// setare si desenare background
        g.setColor(new Color(58,249,217));
        g.fillRect(coordX,coordY,width,height);



        /// incarcare imagine buton
        Image button_image1,button_image=null;
        try{
            button_image1 = ImageIO.read(new File("res/menu/button_rectangle_depth_gradient.png"));
            button_image = button_image1.getScaledInstance(300,60,0);

            ///scaleaza imaginea pt buton

        }catch(IOException e){
            System.out.print("Fisier de buton de meniu negasit\n");
        }

        /// setare text titlu
        g.setColor(Color.YELLOW); //de schimbat fontul
        g.setFont(new Font("Arial",Font.BOLD,50));
        g.drawString("Paused",547,340);

        /// implementare butoane
        int button_coordX = 493;
        int button_coordY = 390;

        /// setare font si text butoane
        g.setFont(new Font("Calibri",Font.BOLD,30));
        g.setColor(Color.black);

        for(int i=1;i<=3;i++) {
            g.drawImage(button_image, button_coordX, button_coordY, null);

            String text = switch (i) {
                case 1 -> "Resume";
                case 2 -> "Save";
                case 3 -> "Exit";
                default -> null;
            };

            g.drawString(text,(button_coordX+300)/2+200,button_coordY+35);

            button_coordY += 80;
        }
    }

    @Override
    public void InitHandlers() {
        /// se va folosi canvas pt a gasi pozitia mouse-ului
        Canvas canvas = refLink.GetGame().GetGameWindow().GetCanvas();
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                /// coordonatele locului unde a fost apasat
                int x = e.getX();
                int y = e.getY();

                /// coordonatele de plecare ale primului buton
                int button_coordX = 493;
                int button_coordY = 390;

                /// inaltime si lungime buton
                int w_button = 300;
                int h_button = 60;

                for(int i=1;i<=3;i++) {
                    if (x >= button_coordX && x <= button_coordX + w_button && y >= button_coordY && y <= button_coordY + h_button) {
                        switch (i) {
                            /// Cazul Resume
                            case 1:
                                saved=false;
                                State playState = refLink.GetGame().GetPlayState();
                                State.SetState(playState);
                                canvas.removeMouseListener(this);
                                break;
                                /// Cazul Save
                            case 2:
                                if(!saved) {
                                    ((PlayState) refLink.GetGame().GetPlayState()).createGameSave();
                                    new Database().insertTable(refLink.GetGameSave());
                                    saved =true;
                                }
                                break;

                            /// Cazul Exit
                            case 3:
                                saved=false;
                                State menuState = refLink.GetGame().GetMenuState();
                                State.SetState(menuState);
                                canvas.removeMouseListener(this);


                        }
                    }
                    button_coordY += 80;
                }
            }
        });
    }
}

