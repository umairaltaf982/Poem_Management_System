package dal;

import java.util.*;

import javax.swing.table.DefaultTableModel;

public interface DataAccessFacade {

    void addRoot(String root);
    String readRoots();
    void updateRoot(String oldRoot, String newRoot);
    void deleteRoot(String root);
    public void addRootToDatabase(int verseID, String root);
    public void addWordToDatabase(int verseID, String word);
    public DefaultTableModel displayAllRoot(DefaultTableModel showDatatableModel);
    public DefaultTableModel displayRoots(DefaultTableModel verifyRootTableModel);
    public void verifyRoot(String oldRoot, String newRoot);
    public void verifyAllRoot();
    public DefaultTableModel searchRoot(DefaultTableModel searchRootModel, String rootToSearch);

    public String[] getBooksNames();
    public String[] getPoemTitles(String bookName);
    public boolean addPoem(String bookName, String poemTitle, ArrayList<String> misra1, ArrayList<String> misra2);
    public HashMap<Integer, String> checkPoem(String bookName);
    public void insert(int id, String bookName, String title, String misra1, String misra2);
    public DefaultTableModel readPoem(DefaultTableModel model, String poemTitle);
    public boolean updatePoem(String bookName, String prevPoemTitle, String poemTitle, ArrayList<String> misra1, ArrayList<String> misra2, ArrayList<Integer> verseIDList);
    public boolean deletePoem(String bookName, String poemTitle);
    public boolean addVerses(String poemTitle, ArrayList<String> misra1, ArrayList<String> misra2);

    public boolean createBook(String bookname, String authorName, java.sql.Date authorDOD);
    public DefaultTableModel readBook(DefaultTableModel model);
    public boolean updateBook(String prevBookName, String bookname, String authorName, java.sql.Date authorDOD);
    public boolean deleteBook(String bookName);
    public DefaultTableModel readSingleBook(DefaultTableModel model, String bookName);
    
    public DefaultTableModel readTokens(DefaultTableModel assignTagTableModel);
    public void addTagToDatabase(int id, String tag);
    public DefaultTableModel readTags(DefaultTableModel readTagTableModel);
}