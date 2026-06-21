package Maps;

import PaooGame.Exceptions.MapLoadException;
import PaooGame.Tiles.Tile;

import java.awt.Graphics;
import java.io.BufferedReader; /* citeste linie cu linie */
import java.io.InputStream;
import java.io.InputStreamReader; /* transforma in text */
import java.util.ArrayList; /* listă în care salvez fiecare linie citită din fișierul hărții */
import java.util.List;

/*
 * Clasa GameMap se ocupă cu încărcarea hărții jocului dintr-un fișier,
 * desenarea tile-urilor pe ecran și verificarea coliziunilor pe hartă.
 */
public class GameMap {

    /* Dimensiunea unui tile în pixeli */
    private static final int TILE_DRAW_SIZE = 50;

    private int[][] map;

    /* Constructor - primește calea fișierului și încarcă harta */
    public GameMap(String path) {
        loadMap(path);
    }

    /* Citește harta din fișier */
    private void loadMap(String path) throws MapLoadException {
        try {
            /* deschide fisierul din resources */
            InputStream is = getClass().getResourceAsStream(path);

            if (is == null) {
                throw new MapLoadException("Nu gasesc harta: " + path);
            }

            /* citeste fiecare linie, ignora daca sunt linii goale */
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<String> lines = new ArrayList<>(); /* lista unde salvez fiecare linie */
            String line;

            /* Citește fiecare linie din fișier */
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }

            int rows = lines.size(); /* nr randuri */

            /* pt coloane iau prima linie, o impart dupa spatii si numar cate valori sunt */
            int cols = lines.get(0).trim().split("\\s+").length;

            /* Creează matricea hărții */
            map = new int[rows][cols];

            /* transform fiecare linie din fișier în valori numerice și le salvez în matricea hărții */
            for (int y = 0; y < rows; y++) {
                /* impart fiecare linie in valori(fiecare val este un numar (tile)) */
                String[] values = lines.get(y).trim().split("\\s+");

                for (int x = 0; x < cols; x++) {
                    map[y][x] = Integer.parseInt(values[x]);
                }
            }

        } catch (Exception e) {
            throw new MapLoadException("Nu gasesc harta: " + path);
        }
    }

    /* Desenează harta pe ecran */
    public void draw(Graphics g, int camX, int camY, double zoom) {
        if (map == null) return;

        /* Parcurge fiecare tile din hartă */
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {

                int tile = map[y][x]; /* ia tipul tile-ului */

                /* Transformă coordonatele din tile → pixeli + cameră + zoom */
                int drawX = (int)((x * TILE_DRAW_SIZE - camX) * zoom);
                int drawY = (int)((y * TILE_DRAW_SIZE - camY) * zoom);

                /* Dimensiunea tile-ului (afectată de zoom) */
                int drawSize = (int)(TILE_DRAW_SIZE * zoom);


                if (tile == 4) {
                    Tile.caramidaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 3) {
                    Tile.parchetTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 0) {
                    Tile.parchetTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.bibliotecaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 1) {
                    Tile.parchetTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.cutieTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 2) {
                    Tile.parchetTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.plantaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 5) {
                    Tile.caramidaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.usaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 6) {
                    Tile.iarbaSimplaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.copacTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 7) {
                    Tile.iarbaSimplaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.fantanaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 8) {
                    Tile.floriTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 9) {
                    Tile.iarbaSimplaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.gardVerticalTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 10) {
                    Tile.iarbaSimplaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.gardOrizontalTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 11) {
                    Tile.tufis1Tile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 12) {
                    Tile.iarbaSimplaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 13) {
                    Tile.iarbaDecorTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 14) {
                    Tile.paveleTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 15) {
                    Tile.iarbaSimplaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.copac2Tile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 16) {
                    Tile.iarbaSimplaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.buturugaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 17) {
                    Tile.iarbaSimplaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.topoganTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 18) {
                    Tile.paveleTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.sinaTrenTile.Draw(g, drawX, drawY, drawSize, drawSize); /* fundal */
                    Tile.locomotivaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 19) {
                    Tile.soseaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 20) {
                    Tile.soseaTile.Draw(g, drawX, drawY, drawSize, drawSize); /* fundal */
                    Tile.masinaVerdeTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 21) {
                    Tile.soseaTile.Draw(g, drawX, drawY, drawSize, drawSize); /* fundal */
                    Tile.masinaRozTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 22) {
                    Tile.soseaTile.Draw(g, drawX, drawY, drawSize, drawSize); /* fundal */
                    Tile.masinaRosieTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 23) {
                    Tile.soseaTile.Draw(g, drawX, drawY, drawSize, drawSize); /* fundal */
                    Tile.masinaAlbastraTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 24) {
                    Tile.paveleTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.sinaTrenTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 25) {
                    Tile.trecerePietoniTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 26) {
                    Tile.trotuarTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 27) {
                    Tile.paveleTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.sinaTrenTile.Draw(g, drawX, drawY, drawSize, drawSize); /* fundal */
                    Tile.vagonMovTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 28) {
                    Tile.paveleTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.sinaTrenTile.Draw(g, drawX, drawY, drawSize, drawSize); /* fundal */
                    Tile.vagonAlbastruTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 29) {
                    Tile.paveleTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.sinaTrenTile.Draw(g, drawX, drawY, drawSize, drawSize); /* fundal */
                    Tile.vagonRosuTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 30) {
                    Tile.paveleTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.sinaTrenTile.Draw(g, drawX, drawY, drawSize, drawSize); /* fundal */
                    Tile.vagonPortocaliuTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }
                else if (tile == 31) {
                    Tile.iarbaSimplaTile.Draw(g, drawX, drawY, drawSize, drawSize);
                    Tile.semaforTile.Draw(g, drawX, drawY, drawSize, drawSize);
                }

            }
        }
    }

    /* Verifică dacă un punct este blocat (coliziune) */
    public boolean isSolidAt(int pixelX, int pixelY) {
        if (map == null) {
            return true; /* dacă nu există hartă → blocat */
        }

        /* pixeli -> tileuri */
        int tileX = pixelX / TILE_DRAW_SIZE;
        int tileY = pixelY / TILE_DRAW_SIZE;

        /* Dacă e în afara hărții → blocat */
        if (tileY < 0 || tileY >= map.length || tileX < 0 || tileX >= map[0].length) {
            return true;
        }

        int tile = map[tileY][tileX];

        /* Ce tile-uri sunt solide (nu poți trece prin ele) */
        return tile == 4 || tile == 0 || tile == 1 || tile == 2 || tile == 5 || tile == 6 || tile==7 || tile == 9 || tile == 10 || tile == 15 ||tile == 18 || tile == 20 || tile == 21 || tile == 22 || tile == 23 ||
                tile == 27 || tile == 28 || tile == 29 || tile == 30 || tile == 31 || tile==16;


    }

    /* Lățimea hărții (în tile-uri) */
    public int getWidth() {
        return map[0].length;
    }

    /* Înălțimea hărții (în tile-uri) */
    public int getHeight() {
        return map.length;


    }
    /* Metoda verifică dacă la o anumită poziție pe hartă (dată în pixeli) */
    /* există un tile de tip tufiș (bush) */
    public boolean isBushAt(int pixelX, int pixelY) {

        /* Dacă harta nu este încărcată (map este null), */
        if (map == null) {
            return false;
        }

        /* Convertim coordonatele din pixeli în coordonate de tip grid (tile-uri) */
        /* Împărțim la dimensiunea unui tile pentru a afla în ce celulă ne aflăm */
        int tileX = pixelX / TILE_DRAW_SIZE;
        int tileY = pixelY / TILE_DRAW_SIZE;

        /* Verificăm dacă poziția calculată este în interiorul hărții */
        if (tileY < 0 || tileY >= map.length || tileX < 0 || tileX >= map[0].length) {
            return false;
        }

        /* Luăm valoarea tile-ului din matrice */
        /* map[y][x] conține codul tipului de teren */
        int tile = map[tileY][tileX];

        /* Verificăm dacă tile-ul este un tufiș */
        return tile == 11;
    }
}
