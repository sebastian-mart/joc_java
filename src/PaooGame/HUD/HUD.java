package PaooGame.HUD;

import PaooGame.Items.Hero;
import PaooGame.Items.Character;

import java.awt.*;
/**
 * Clasa {@code}HealthBar este responsabilă pentru afișarea grafică a stării de viață a eroului pe ecran.
 * Aceasta afișează o bară colorată care reflectă procentul de viață al jucătorului, precum și textul "HP".
 */
public class HUD {
    private Hero hero; /*!< Referință la obiectul erou pentru a obține starea curentă a vieții. */
    private int width = 250;       /*!< Lățimea barei de viață în pixeli. */
    private int height = 25;       /*!< Înălțimea barei de viață în pixeli. */
    private int xPosition = 64*15+50;    /*!< Poziția X a barei de viață pe ecran. */
    private int yPosition = 20;    /*!< Poziția Y a barei de viață pe ecran. */

    /**
     * Constructorul clasei {@code}HealthBar.
     *
     * @param hero Referință la eroul căruia îi aparține bara de viață.
     */
    public HUD(Hero hero) {
        this.hero = hero; //salveata referinta eroului
    }

    /**
     * Desenează bara de viață pe ecran, în funcție de starea curentă a vieții eroului.
     *
     * @param g Contextul grafic în care se va realiza desenarea.
     */
    public void Draw(Graphics g) {
        // Calculăm procentul de viață rămas
        int lifePercentage = (int) ((float) hero.GetHealth() / (float) Hero.DEFAULT_LIFE * 100);

        // Desenăm conturul barei de viață (umplutură)
        g.setColor(new Color(0, 0, 0, 180)); // Umbra neagră
        g.fillRoundRect(xPosition - 3, yPosition - 3, width + 6, height + 6, 10, 10); // Adăugăm contur

        // Desenăm background-ul barei de viață
        g.setColor(new Color(50, 50, 50)); // Gri închis pentru fundal
        g.fillRoundRect(xPosition, yPosition, width, height, 10, 10);

        // Calculăm culoarea barei în funcție de viață (trecere de la verde la galben și apoi roșu)
        Color lifeColor = getLifeColor(lifePercentage);

        // Desenăm bara de viață efectivă
        g.setColor(lifeColor);
        g.fillRoundRect(xPosition, yPosition, (int) (width * (lifePercentage / 100.0)), height, 10, 10);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 24));
        g.drawString("Score: " + hero.getScore(), 64*16, 75);

    }

    /**
     * Returnează culoarea pe care trebuie să o aibă bara de viață în funcție de procentajul vieții.
     *
     * @param lifePercentage Procentul curent de viață (0-100).
     * @return Culoarea asociată nivelului de viață.
     */
    private Color getLifeColor(int lifePercentage) {
        if (lifePercentage > 60) {
            return new Color(46, 204, 113); // Verde
        } else if (lifePercentage > 30) {
            return new Color(241, 196, 15); // Galben
        } else {
            return new Color(231, 76, 60); // Roșu
        }
    }
}