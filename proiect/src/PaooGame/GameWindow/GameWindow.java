
package PaooGame.GameWindow;

import javax.swing.*;
import java.awt.*;

/*
 * Clasa GameWindow reprezinta fereastra principala a jocului.
 * Aceasta clasa se ocupa de crearea si configurarea ferestrei grafice in care
 * este afisat jocul, folosind un JFrame ca fereastra principala si un Canvas
 * ca suprafata pe care se deseneaza elementele jocului.
 *
 * In cadrul jocului, GameWindow are rolul de a retine titlul, latimea si
 * inaltimea ferestrei, de a construi efectiv fereastra si de a oferi acces
 * catre Canvas, astfel incat clasa Game sa poata desena harta, personajele,
 * meniurile si celelalte elemente vizuale.
 */
/*! \class GameWindow
    \brief Implementeaza notiunea de fereastra a jocului.

    Membrul wndFrame este un obiect de tip JFrame care va avea utilitatea unei
    ferestre grafice si totodata si cea a unui container (toate elementele
    grafice vor fi continute de fereastra).
 */
public class GameWindow
{
    private JFrame  wndFrame;       /*!< fereastra principala a jocului*/
    private String  wndTitle;       /*!< titlul ferestrei*/
    private int     wndWidth;       /*!< latimea ferestrei in pixeli*/
    private int     wndHeight;      /*!< inaltimea ferestrei in pixeli*/


    private Canvas  canvas;

    /*!< "panza/tablou" in care se poate desena*/


    /*
     * Constructorul clasei GameWindow initializeaza datele de baza ale ferestrei.
     * Parametrii primiti sunt salvati in variabilele membre pentru a putea fi
     * folositi mai tarziu la construirea ferestrei propriu-zise.
     *
     * In acest moment nu se creeaza inca fereastra pe ecran, ci doar se retin
     * titlul si dimensiunile dorite. Fereastra va fi creata efectiv atunci cand
     * este apelata metoda BuildGameWindow().
     */
    /*! \fn GameWindow(String title, int width, int height)
            \brief Constructorul cu parametri al clasei GameWindow

            Retine proprietatile ferestrei proprietatile (titlu, latime, inaltime)
            in variabilele membre deoarece vor fi necesare pe parcursul jocului.
            Crearea obiectului va trebui urmata de crearea ferestrei propriuzise
            prin apelul metodei BuildGameWindow()

            \param title Titlul ferestrei.
            \param width Latimea ferestrei in pixeli.
            \param height Inaltimea ferestrei in pixeli.
         */
    public GameWindow(String title, int width, int height){
        wndTitle    = title;    /*!< Retine titlul ferestrei.*/
        wndWidth    = width;    /*!< Retine latimea ferestrei.*/
        wndHeight   = height;   /*!< Retine inaltimea ferestrei.*/
        wndFrame    = null;     /*!< Fereastra nu este construita.*/
    }

    /*
     * Metoda construieste fereastra jocului si configureaza toate proprietatile
     * necesare pentru afisarea corecta a jocului.
     *
     * Se creeaza obiectul JFrame, se seteaza dimensiunea, titlul, pozitia pe
     * ecran, comportamentul la inchidere si faptul ca fereastra nu poate fi
     * redimensionata. Apoi se creeaza Canvas-ul, adica suprafata pe care jocul
     * va desena elementele grafice.
     *
     * Metoda verifica mai intai daca fereastra exista deja, pentru a evita
     * reconstruirea ei de mai multe ori.
     */
    /*! \fn private void BuildGameWindow()
        \brief Construieste/creaza fereastra si seteaza toate proprietatile
        necesare: dimensiuni, pozitionare in centrul ecranului, operatia de
        inchidere, invalideaza redimensionarea ferestrei, afiseaza fereastra.

     */
    public void BuildGameWindow()
    {
        /// Daca fereastra a mai fost construita intr-un apel anterior
        /// se renunta la apel
        if(wndFrame != null)
        {
            return;
        }
        /// Aloca memorie pentru obiectul de tip fereastra si seteaza denumirea
        /// ce apare in bara de titlu
        wndFrame = new JFrame(wndTitle);
        /// Seteaza dimensiunile ferestrei in pixeli
        wndFrame.setSize(wndWidth, wndHeight);
        /// Operatia de inchidere (fereastra sa poata fi inchisa atunci cand
        /// este apasat butonul x din dreapta sus al ferestrei). Totodata acest
        /// lucru garanteaza ca nu doar fereastra va fi inchisa ci intregul
        /// program
        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /// Avand in vedere ca dimensiunea ferestrei poate fi modificata
        /// si corespunzator continutul actualizat (aici ma refer la dalele
        /// randate) va recomand sa constrangeti deocamdata jucatorul
        /// sa se joace in fereastra stabilitata de voi. Puteti reveni asupra
        /// urmatorului apel ulterior.
        wndFrame.setResizable(false);
        /// Recomand ca fereastra sa apara in centrul ecranului. Pentru orice
        /// alte pozitie se va apela "wndFrame.setLocation(x, y)" etc.
        wndFrame.setLocationRelativeTo(null);
        /// Implicit o fereastra cand este creata nu este vizibila motiv pentru
        /// care trebuie setata aceasta proprietate
        wndFrame.setVisible(true);

        /// Creaza obiectul de tip canvas (panza) pe care se poate desena.
        canvas = new Canvas();
        /// In aceeasi maniera trebuiesc setate proprietatile pentru acest obiect
        /// canvas (panza): dimensiuni preferabile, minime, maxime etc.
        /// Urmotorul apel de functie seteaza dimensiunea "preferata"/implicita
        /// a obiectului de tip canvas.
        /// Functia primeste ca parametru un obiect de tip Dimension ca incapsuleaza
        /// doua proprietati: latime si inaltime. Cum acest obiect nu exista
        /// a fost creat unul si dat ca parametru.
        canvas.setPreferredSize(new Dimension(wndWidth, wndHeight));
        /// Avand in vedere ca elementele unei ferestre pot fi scalate atunci cand
        /// fereastra este redimensionata
        canvas.setMaximumSize(new Dimension(wndWidth, wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth, wndHeight));
        /// Avand in vedere ca obiectul de tip canvas, proaspat creat, nu este automat
        /// adaugat in fereastra trebuie apelata metoda add a obiectul wndFrame
        wndFrame.add(canvas);
        /// Urmatorul apel de functie are ca scop eventuala redimensionare a ferestrei
        /// ca tot ce contine sa poate fi afisat complet
        wndFrame.pack();
    }

    /*
     * Metoda returneaza latimea ferestrei jocului.
     * Aceasta valoare este folosita in alte clase pentru calcule de desenare,
     * pozitionare a elementelor pe ecran si actualizarea camerei.
     */
    /*! \fn public int GetWndWidth()
        \brief Returneaza latimea ferestrei.
     */
    public int GetWndWidth()
    {
        return wndWidth;
    }

    /*
     * Metoda returneaza inaltimea ferestrei jocului.
     * Inaltimea este necesara pentru desenarea corecta a imaginilor de fundal,
     * pentru centrare si pentru limitarea camerei in interiorul hartii.
     */
    /*! \fn public int GetWndWidth()
        \brief Returneaza inaltimea ferestrei.
     */
    public int GetWndHeight()
    {
        return wndHeight;
    }

    /*
     * Metoda ofera acces la obiectul Canvas al ferestrei.
     * Canvas-ul este suprafata grafica pe care se deseneaza jocul, iar clasa
     * Game il foloseste pentru BufferStrategy, input de tastatura si mouse.
     */
    /*! \fn public int GetCanvas()
        \brief Returneaza referinta catre canvas-ul din fereastra pe care se poate desena.
     */
    public Canvas GetCanvas() {
        return canvas;
    }

    /*
     * Metoda returneaza obiectul JFrame principal al jocului.
     * Este utila atunci cand alte componente au nevoie de fereastra parinte,
     * de exemplu pentru afisarea unor ferestre de dialog peste joc.
     */
    public JFrame GetWndFrame() {
        return wndFrame;
    }
}
