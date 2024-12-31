package dal;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import org.apache.logging.log4j.*;

public class DataAccess implements DataAccessFacade {

    private static DataAccess instance;

    public static Logger logger = LogManager.getLogger(DataAccess.class.getName());

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;

    String query;

    public DataAccess() {
        try {
            Properties p = new Properties();
            p.load(new FileInputStream("config/JDBC_Setup.properties"));
            connection = DriverManager.getConnection(p.getProperty("jdbcUrl"), p.getProperty("username"), p.getProperty("password"));
            if (connection != null) {
                System.out.println("Connected to the Database!");
                statement = connection.createStatement();
            }
        } catch (SQLException | IOException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "There is an error connecting to Database");
        }
    }

    public static DataAccess getInstance() {
        if (instance == null) {
            instance = new DataAccess();
        }
        return instance;
    }

    // this function get books names from database
    @Override
    public String[] getBooksNames() {
        String[] booksNames = null;
        try {
            query = "SELECT Book_Name FROM book";
            resultSet = statement.executeQuery(query);
            int count = 0;
            while (resultSet.next()) {
                count++;
            }
            booksNames = new String[count];
            resultSet = statement.executeQuery(query);
            int i = 0;
            while (resultSet.next()) {
                booksNames[i] = resultSet.getString("Book_Name");
                i++;
            }
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
        }
        return booksNames;
    }

    @Override
    public String[] getPoemTitles(String bookName) {
        String[] poemTitles = null;
        try {
            query = "SELECT Book_ID FROM book WHERE Book_Name = '" + bookName + "'";
            resultSet = statement.executeQuery(query);
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("Book_ID");
            }
            query = "SELECT DISTINCT Poem_Title FROM poem WHERE Book_ID = '" + id + "'";
            resultSet = statement.executeQuery(query);
            int count = 0;
            while (resultSet.next()) {
                count++;
            }
            poemTitles = new String[count];
            resultSet = statement.executeQuery(query);
            int i = 0;
            while (resultSet.next()) {
                poemTitles[i] = resultSet.getString("Poem_Title");
                i++;
            }
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
        }
        return poemTitles;
    }

    // this function adds poem to data base
    @Override
    public boolean addPoem(String bookName, String poemTitle, ArrayList<String> misra1, ArrayList<String> misra2) {
        try {
            query = "SELECT Book_ID FROM book WHERE Book_Name = '" + bookName + "'";
            resultSet = statement.executeQuery(query);
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("Book_ID");
            }
            query = "INSERT INTO poem(Book_ID, Poem_Title) VALUES(" + id + ", ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, poemTitle);
            preparedStatement.executeUpdate();
            query = "SELECT Poem_ID FROM poem WHERE Poem_Title = '" + poemTitle + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getInt("Poem_ID");
            }
            for (int i = 0; i < misra1.size(); i++) {
                query = "INSERT INTO verses(Poem_ID, Misra_1, Misra_2) VALUES(" + id + ", ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, misra1.get(i));
                preparedStatement.setString(2, misra2.get(i));
                preparedStatement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
            return false;
        }
    }
    // this function insert poem from text file to database

    @Override
    public void insert(int id, String bookName, String title, String misra1, String misra2) {
        try {
            query = "INSERT INTO Poem VALUES(" + id + ", '" + bookName + "', '" + title + "', '" + misra1 + "', '" + misra2 + "')";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
        }
    }
    // this function check existing poems in database

    @Override
    public HashMap<Integer, String> checkPoem(String bookName) {
        HashMap<Integer, String> temp = new HashMap<>();
        try {
            query = "SELECT * FROM book";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (bookName.equals(resultSet.getString("Book_Name"))) {
                    temp.put(resultSet.getInt("ID"), bookName);
                    break;
                }
            }
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
        }
        return temp;
    }

    @Override
    public DefaultTableModel readPoem(DefaultTableModel model, String poemTitle) {
        try {
            query = "SELECT Poem_ID FROM poem WHERE Poem_Title = '" + poemTitle + "'";
            resultSet = statement.executeQuery(query);
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("Poem_ID");
            }
            query = "SELECT Verse_ID, Misra_1, Misra_2 FROM verses WHERE Poem_ID = '" + id + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                model.addRow(new Object[]{resultSet.getString("Verse_ID"), resultSet.getString("Misra_1"), resultSet.getString("Misra_2")});
            }
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
        }
        return model;
    }

    @Override
    public boolean updatePoem(String bookName, String prevPoemTitle, String poemTitle, ArrayList<String> misra1, ArrayList<String> misra2, ArrayList<Integer> verseIDList) {
        try {
            query = "SELECT Book_ID FROM book WHERE Book_Name = '" + bookName + "'";
            resultSet = statement.executeQuery(query);
            int book_id = 0;
            while (resultSet.next()) {
                book_id = resultSet.getInt("Book_ID");
            }
            query = "SELECT Poem_ID FROM poem WHERE Poem_Title = '" + prevPoemTitle + "'";
            resultSet = statement.executeQuery(query);
            int poem_id = 0;
            while (resultSet.next()) {
                poem_id = resultSet.getInt("Poem_ID");
            }
            query = "UPDATE poem SET Poem_Title = '" + poemTitle + "' WHERE Book_ID = " + book_id + " AND Poem_ID = " + poem_id;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();

            for (int i = 0; i < verseIDList.size(); i++) {
                query = "UPDATE verses SET Misra_1 = ?, Misra_2 = ? WHERE Poem_ID = " + poem_id + " AND Verse_ID = " + verseIDList.get(i);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, misra1.get(i));
                preparedStatement.setString(2, misra2.get(i));
                preparedStatement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePoem(String bookName, String poemTitle) {
        try {
            query = "DELETE FROM poem WHERE Poem_Title = '" + poemTitle + "'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addVerses(String poemTitle, ArrayList<String> misra1, ArrayList<String> misra2) {
        try {
            query = "SELECT Poem_ID FROM poem WHERE Poem_Title = '" + poemTitle + "'";
            resultSet = statement.executeQuery(query);
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("Poem_ID");
            }
            for (int i = 0; i < misra1.size(); i++) {
                query = "INSERT INTO verses(Poem_ID, Misra_1, Misra_2) VALUES(" + id + ", ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, misra1.get(i));
                preparedStatement.setString(2, misra2.get(i));
                preparedStatement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
            return false;
        }
    }

    // this function add new book to database
    @Override
    public boolean createBook(String bookname, String authorName, java.sql.Date authorDOD) {
        try {
            query = "INSERT INTO book(Book_Name, Author, Author_DOD) VALUES(?, ?, '" + authorDOD + "')";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookname);
            preparedStatement.setString(2, authorName);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
            return false;
        }
    }
    // this function read all books from database

    @Override
    public DefaultTableModel readBook(DefaultTableModel model) {
        try {
            query = "SELECT * FROM book";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                model.addRow(new Object[]{resultSet.getString("Book_Name"), resultSet.getString("Author"), resultSet.getString("Author_DOD")});
            }
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
        }
        return model;
    }
    // this function update book in database

    @Override
    public boolean updateBook(String prevBookName, String bookname, String authorName, java.sql.Date authorDOD) {
        try {
            query = "UPDATE book SET Book_Name = ?, Author = ?, Author_DOD = '" + authorDOD + "' WHERE Book_Name = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookname);
            preparedStatement.setString(2, authorName);
            preparedStatement.setString(3, prevBookName);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBook(String bookName) {
        try {
            query = "DELETE FROM book WHERE Book_Name = '" + bookName + "'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public DefaultTableModel readSingleBook(DefaultTableModel model, String bookName) {
        try {
            query = "SELECT Book_ID FROM book WHERE Book_Name = '" + bookName + "'";
            resultSet = statement.executeQuery(query);
            int book_id = 0;
            while (resultSet.next()) {
                book_id = resultSet.getInt("Book_ID");
            }
            query = "SELECT DISTINCT Poem_Title FROM poem WHERE Book_ID = " + book_id;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                model.addRow(new Object[]{resultSet.getString("Poem_Title")});
            }
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
        }
        return model;
    }

    // this function manually add root in database
    @Override
    public void addRoot(String root) {
        try {
            query = "INSERT INTO roots (root) VALUES ('" + root + "')";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Connection error: " + e.getMessage());
        }
    }

    // this function read roots from database
    @Override
    public String readRoots() {
        StringBuilder result = new StringBuilder();
        try {
            resultSet = statement.executeQuery("SELECT * FROM roots");
            while (resultSet.next()) {
                String root = resultSet.getString("root");
                result.append(root).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Connection error: " + e.getMessage());
        }
        return result.toString();
    }

    // this function update existing roots in database
    @Override
    public void updateRoot(String oldRoot, String newRoot) {
        try {
            query = "UPDATE roots SET root = '" + newRoot + "' WHERE root = '" + oldRoot + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Connection error: " + e.getMessage());
        }
    }

    // this function delete root from database
    @Override
    public void deleteRoot(String root) {
        try {
            query = "DELETE FROM roots WHERE root = '" + root + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Connection error: " + e.getMessage());
        }
    }

    @Override
    public void addRootToDatabase(int verseID, String root) {
        // Modify the code to store the root in the "roots" table
        String insertRootQuery = "INSERT INTO roots (Verse_ID, Root, Status) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertRootQuery)) {
            preparedStatement.setInt(1, verseID);
            preparedStatement.setString(2, root);
            preparedStatement.setString(3, "unverified"); // Set the status to "unverified"

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error("Connection error: " + ex.getMessage());
        }
    }

    @Override
    public void addWordToDatabase(int verseID, String word) {
        // Modify the code to store the word in the "words" table

        String insertWordQuery = "INSERT INTO tokens (Verse_ID, Token) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertWordQuery)) {
            preparedStatement.setInt(1, verseID);
            preparedStatement.setString(2, word);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error("Connection error: " + ex.getMessage());
        }
    }

    @Override
    public DefaultTableModel displayAllRoot(DefaultTableModel showDatatableModel) {
        // Clear existing data in the verification table
        showDatatableModel.setRowCount(0);

        String selectQuery = "SELECT v.Misra_1,v.Misra_2, r.Root, r.Status "
                + "FROM verses v "
                + "JOIN roots r ON v.Verse_ID = r.Verse_ID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String misra1 = resultSet.getString("Misra_1");
                String misra2 = resultSet.getString("Misra_2");
                String root = resultSet.getString("Root");
                String status = resultSet.getString("Status");

                // Add data to the verification table
                showDatatableModel.addRow(new Object[]{misra1, misra2, root, status});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error("Connection error: " + ex.getMessage());
        }
        return showDatatableModel;
    }

    @Override
    public DefaultTableModel displayRoots(DefaultTableModel verifyRootTableModel) {
        // Clear existing data in the verification table
        verifyRootTableModel.setRowCount(0);

        String selectQuery = "SELECT v.Misra_1,v.Misra_2, r.Root, r.Status "
                + "FROM verses v "
                + "JOIN roots r ON v.Verse_ID = r.Verse_ID "
                + "WHERE r.Status = 'unverified'";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String misra1 = resultSet.getString("Misra_1");
                String misra2 = resultSet.getString("Misra_2");
                String root = resultSet.getString("Root");
                String status = resultSet.getString("Status");

                // Add data to the verification table
                verifyRootTableModel.addRow(new Object[]{misra1, misra2, root, status});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error("Connection error: " + ex.getMessage());
        }
        return verifyRootTableModel;
    }

    @Override
    public void verifyRoot(String oldRoot, String newRoot) {
        String updateRootQuery = "UPDATE roots SET root = ?, Status = 'verified' WHERE root = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateRootQuery)) {
            // Set parameters for the update query
            preparedStatement.setString(1, newRoot);
            preparedStatement.setString(2, oldRoot);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error("Connection error: " + ex.getMessage());
        }
    }

    @Override
    public void verifyAllRoot() {
        String updateRootQuery = "UPDATE roots SET Status = 'verified'";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateRootQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error("Connection error: " + ex.getMessage());
        }
    }

    @Override
    public DefaultTableModel searchRoot(DefaultTableModel searchRootModel, String rootToSearch) {
        try {
            String query = "SELECT v.Verse_ID, v.Misra_1, v.Misra_2, r.Root "
                    + "FROM verses v "
                    + "JOIN roots r ON v.Verse_ID = r.Verse_ID "
                    + "WHERE r.Root = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, rootToSearch);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Object[] row = {
                            resultSet.getString("Misra_1"),
                            resultSet.getString("Misra_2"),
                            resultSet.getString("Root")
                        };
                        searchRootModel.addRow(row);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error("Connection error: " + ex.getMessage());
        }
        return searchRootModel;
    }

    // Tag Functions
    @Override
    public DefaultTableModel readTokens(DefaultTableModel assignTagTableModel) {
        String query = "SELECT Token_ID, Token FROM tokens";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                assignTagTableModel.addRow(new Object[]{resultSet.getString("Token_ID"), resultSet.getString("Token")});
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            logger.error("Connection error: " + e.getMessage());
        }
        return assignTagTableModel;
    }

    @Override
    public void addTagToDatabase(int id, String tag) {
        String query = "INSERT INTO tags (Token_ID, Tag) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, tag);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error("Connection error: " + ex.getMessage());
        }
    }

    @Override
    public DefaultTableModel readTags(DefaultTableModel readTagTableModel) {

        readTagTableModel.setRowCount(0);

        String query = "SELECT tok.Token, t.Tag "
                + "FROM tokens tok "
                + "JOIN tags t ON tok.Token_ID = t.Token_ID";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                readTagTableModel.addRow(new Object[]{resultSet.getString("Token"), resultSet.getString("Tag")});
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error("Connection error: " + ex.getMessage());
        }
        return readTagTableModel;
    }
}
