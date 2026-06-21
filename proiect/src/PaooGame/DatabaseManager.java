package PaooGame;

import PaooGame.Exceptions.DatabaseException;

import java.sql.*;

/*
 * Clasa DatabaseManager gestionează conexiunea cu baza de date SQLite a jocului.
 * Ea creează tabela de salvări, salvează progresul jucătorului, încarcă datele salvate și generează textul pentru leaderboard.
 */
public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:mitzi_game.db";

    /*
     * Creează și returnează o conexiune la baza de date SQLite.
     * Încarcă driverul SQLite înainte de a apela DriverManager.
     */
    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(DB_URL);
    }

    /*
     * Creează tabela necesară pentru salvările jocului dacă aceasta nu există.
     * Încearcă și adăugarea coloanelor lipsă pentru compatibilitate cu versiuni mai vechi ale bazei de date.
     */
    public static void createTables() throws DatabaseException {
        String sql =
                "CREATE TABLE IF NOT EXISTS saves (" +
                        "player_name TEXT PRIMARY KEY, " +
                        "level INTEGER, " +
                        "score INTEGER, " +
                        "lives INTEGER," +
                        "collected_fish TEXT," +
                        "checkpoint_x INTEGER DEFAULT -1, " +
                        "checkpoint_y INTEGER DEFAULT -1 " +
                        ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            try {
                stmt.execute("ALTER TABLE saves ADD COLUMN collected_fish TEXT DEFAULT '';");
                try {
                    stmt.execute("ALTER TABLE saves ADD COLUMN checkpoint_x INTEGER DEFAULT -1;");
                } catch (SQLException ignored) {}

                try {
                    stmt.execute("ALTER TABLE saves ADD COLUMN checkpoint_y INTEGER DEFAULT -1;");
                } catch (SQLException ignored) {}
            } catch (SQLException ignored) {

            }
            System.out.println("Tabela saves este gata.");

        } catch (SQLException e) {
            throw new DatabaseException("Eroare la baza de date.", e);
        }
    }

    /*
     * Salvează sau actualizează progresul unui jucător.
     * Datele includ numele, nivelul, scorul, viețile, peștii colectați și poziția checkpoint-ului.
     */
    public static void saveOrUpdateGame(String playerName, int level, int score, int lives, String collectedFish, int checkpointX, int checkpointY) {
        String sql = "INSERT OR REPLACE INTO saves(player_name, level, score, lives, collected_fish,checkpoint_x, checkpoint_y) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, playerName);
            pstmt.setInt(2, level);
            pstmt.setInt(3, score);
            pstmt.setInt(4, lives);
            pstmt.setString(5, collectedFish);
            pstmt.setInt(6, checkpointX);
            pstmt.setInt(7, checkpointY);

            pstmt.executeUpdate();
            System.out.println("Progres salvat pentru " + playerName);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Încarcă progresul salvat pentru un anumit jucător.
     * Dacă există o salvare, construiește și returnează un obiect SaveData cu informațiile găsite.
     */
    public static SaveData loadGame(String playerName) {
        String sql = "SELECT level, score, lives, collected_fish,checkpoint_x, checkpoint_y FROM saves WHERE player_name = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, playerName);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new SaveData(
                        rs.getInt("level"),
                        rs.getInt("score"),
                        rs.getInt("lives"),
                        rs.getString("collected_fish"),
                        rs.getInt("checkpoint_x"),
                        rs.getInt("checkpoint_y")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
     * Construiește textul pentru leaderboard.
     * Selectează primele cinci salvări ordonate după scor descrescător și le formatează pentru afișare.
     */
    public static String getLeaderboardText() {
        String sql = "SELECT player_name, level, score, lives FROM saves ORDER BY score DESC LIMIT 5";
        StringBuilder text = new StringBuilder();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            int place = 1;

            while (rs.next()) {
                text.append(place)
                        .append(". ")
                        .append(rs.getString("player_name"))
                        .append("  |  Score: ")
                        .append(rs.getInt("score"))
                        .append("  |  Level: ")
                        .append(rs.getInt("level"))
                        .append("  |  Lives: ")
                        .append(rs.getInt("lives"))
                        .append("\n");

                place++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (text.length() == 0) {
            text.append("Nu exista scoruri salvate.");
        }

        return text.toString();
    }
}