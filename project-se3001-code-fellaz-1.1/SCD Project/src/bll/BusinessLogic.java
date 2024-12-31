package bll;

import java.sql.SQLException;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import dal.*;
import net.oujda_nlp_team.AlKhalil2Analyzer;
import net.oujda_nlp_team.entity.Result;

public class BusinessLogic {

    private static BusinessLogic instance;

    DataAccessFacade dal;

    public BusinessLogic(DataAccessFacade dataAccess) {
        dal = dataAccess;
    }

    public static BusinessLogic getInstance() {
        if (instance == null) {
            instance = new BusinessLogic(DataAccess.getInstance());
        }
        return instance;
    }

    public String[] getBooksNames() {
        return dal.getBooksNames();
    }

    public String[] getPoemTitles(String bookName) {
        return dal.getPoemTitles(bookName);
    }

    public boolean addPoem(String bookName, String poemTitle, ArrayList<String> misra1, ArrayList<String> misra2) {
        return dal.addPoem(bookName, poemTitle, misra1, misra2);
    }

    public void processPoemData(ArrayList<String> lines) throws SQLException {
        String bookName = "";
        ArrayList<String> title = new ArrayList<>();
        boolean isVerse = false;
        String misra1 = "";
        String misra2 = "";

        int id = -1;

        for (String line : lines) {
            if (line.contains("الكتاب :")) {
                bookName = line.replace("الكتاب : ", "").trim();
            } else if (line.contains("[") && line.contains("]")) {
                title.add(line.substring(line.indexOf("[") + 1, line.indexOf("]")));
                id++;
            } else if (line.contains("(") && line.contains("...")) {
                if (isVerse) {
                    isVerse = false;
                    misra1 = misra1.replace("(", "").trim();
                    misra2 = misra2.replace(")", "").trim();
                    misra1 = "";
                    misra2 = "";
                }

                String[] parts = line.split("\\.\\.\\.");
                if (parts.length == 2) {
                    misra1 = parts[0].trim();
                    misra2 = parts[1].trim();
                    isVerse = true;
                }
            }
            if (isVerse) {
                misra1 = misra1.replace("(", "").trim();
                misra2 = misra2.replace(")", "").trim();
            }
            HashMap<Integer, String> map = dal.checkPoem(bookName);
            if (id >= 0) {
                for (Map.Entry<Integer, String> m : map.entrySet()) {
                    dal.insert(m.getKey(), m.getValue(), title.get(id), misra1, misra2);
                }
            }
        }
    }

    public DefaultTableModel readPoem(DefaultTableModel model, String poemTitle) {
        return dal.readPoem(model, poemTitle);
    }

    public boolean updatePoem(String bookName, String prevPoemTitle, String poemTitle, ArrayList<String> misra1, ArrayList<String> misra2, ArrayList<Integer> verseIDList) {
        return dal.updatePoem(bookName, prevPoemTitle, poemTitle, misra1, misra2, verseIDList);
    }

    public boolean deletePoem(String bookName, String poemTitle) {
        return dal.deletePoem(bookName, poemTitle);
    }
    
    public boolean addVerses(String poemTitle, ArrayList<String> misra1, ArrayList<String> misra2) {
        return dal.addVerses(poemTitle, misra1, misra2);
    }

    public void tokenizeAllVerses(DefaultTableModel versesTableModel, DefaultTableModel rootsTableModel) {
        rootsTableModel.setRowCount(0);

        for (int i = 0; i < versesTableModel.getRowCount(); i++) {
            String verse1 = (String) versesTableModel.getValueAt(i, 1);
            int verseID1 = Integer.parseInt(versesTableModel.getValueAt(i, 0).toString()); // Get the actual verse ID
            if (verse1 != null && !verse1.trim().isEmpty()) {
                tokenize(rootsTableModel, verse1, verseID1); // Pass the actual verse ID
            }
        }

        for (int i = 0; i < versesTableModel.getRowCount(); i++) {
            String verse2 = (String) versesTableModel.getValueAt(i, 2);
            int verseID2 = Integer.parseInt(versesTableModel.getValueAt(i, 0).toString());
            if (verse2 != null && !verse2.trim().isEmpty()) {
                tokenize(rootsTableModel, verse2, verseID2);
            }
        }
    }

    public void assignRootToVerse(DefaultTableModel versesTableModel, DefaultTableModel rootsTableModel) {
        rootsTableModel.setRowCount(0);

        for (int i = 0; i < versesTableModel.getRowCount(); i++) {
            String verse1 = (String) versesTableModel.getValueAt(i, 1);
            int verseID1 = Integer.parseInt(versesTableModel.getValueAt(i, 0).toString()); // Get the actual verse ID
            if (verse1 != null && !verse1.trim().isEmpty()) {
                tokenizeVerse(rootsTableModel, verse1, verseID1); // Pass the actual verse ID
            }
        }

        for (int i = 0; i < versesTableModel.getRowCount(); i++) {
            String verse2 = (String) versesTableModel.getValueAt(i, 2);
            int verseID2 = Integer.parseInt(versesTableModel.getValueAt(i, 0).toString());
            if (verse2 != null && !verse2.trim().isEmpty()) {
                tokenizeVerse(rootsTableModel, verse2, verseID2);
            }
        }
    }

    private void tokenizeVerse(DefaultTableModel rootsTableModel, String verse, int verseID) {
        String[] words = verse.split("\\s+");
        for (String word : words) {
            String root = getRoot(word);
            addRootToTable(rootsTableModel, word, root);
            addRootToDatabase(verseID, root);
        }
    }

    private void tokenize(DefaultTableModel rootsTableModel, String verse, int verseID) {
        String[] words = verse.split("\\s+");
        for (String word : words) {
            String root = getRoot(word);
            addWordToDatabase(verseID, word);
            addWordToTable(rootsTableModel, word);
        }
    }

    private void addRootToTable(DefaultTableModel rootsTableModel, String word, String root) {
        rootsTableModel.addRow(new Object[]{word, root});
    }

    private void addWordToTable(DefaultTableModel rootsTableModel, String word) {
        rootsTableModel.addRow(new Object[]{word});
    }

    private void addRootToDatabase(int verseID, String root) {
        dal.addRootToDatabase(verseID, root);
    }

    public void addWordToDatabase(int verseID, String word) {
        dal.addWordToDatabase(verseID, word);
    }

    private String getRoot(String word) {
        try {
            AlKhalil2Analyzer analyzer = AlKhalil2Analyzer.getInstance();
            if (analyzer != null) {
                return analyzer.processToken(word).getAllRootString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public DefaultTableModel displayAllRoot(DefaultTableModel showDatatableModel) {
        return dal.displayAllRoot(showDatatableModel);
    }

    public DefaultTableModel displayRoots(DefaultTableModel verifyRootTableModel) {
        return dal.displayRoots(verifyRootTableModel);
    }

    public void verifyRoot(String oldRoot, String newRoot) {
        dal.verifyRoot(oldRoot, newRoot);
    }

    public void verifyAllRoot() {
        dal.verifyAllRoot();
    }

    public DefaultTableModel searchRoot(DefaultTableModel searchRootModel, String rootToSearch) {
        return dal.searchRoot(searchRootModel, rootToSearch);
    }

    public boolean createBook(String bookname, String authorName, java.sql.Date authorDOD) {
        return dal.createBook(bookname, authorName, authorDOD);
    }

    public DefaultTableModel readBook(DefaultTableModel model) {
        return dal.readBook(model);
    }

    public boolean updateBook(String prevBookName, String bookname, String authorName, java.sql.Date authorDOD) {
        return dal.updateBook(prevBookName, bookname, authorName, authorDOD);
    }

    public boolean deleteBook(String bookName) {
        return dal.deleteBook(bookName);
    }

    public DefaultTableModel readSingleBook(DefaultTableModel model, String bookName) {
        return dal.readSingleBook(model, bookName);
    }

    public void addRoot(String root) {
        dal.addRoot(root);
    }

    public String readRoots() {
        return dal.readRoots();
    }

    public void updateRoot(String oldRoot, String newRoot) {
        dal.updateRoot(oldRoot, newRoot);
    }

    public void deleteRoot(String root) {
        dal.deleteRoot(root);
    }

    //tag functions
    public DefaultTableModel readTokens(DefaultTableModel assignTagTableModel) {
        return dal.readTokens(assignTagTableModel);
    }

    public void assignTags(DefaultTableModel assignTagTableModel) {
        for (int i = 0; i < assignTagTableModel.getRowCount(); i++) {
            String token = (String) assignTagTableModel.getValueAt(i, 1);
            int tokenid = Integer.parseInt(assignTagTableModel.getValueAt(i, 0).toString());
            List<Result> str = net.oujda_nlp_team.AlKhalil2Analyzer.getInstance().processToken(token).getAllResults();
            if (str.size() != 0) {
                String[] splitWords = str.get(0).getPartOfSpeech().split("\\|");
                for (String tag : splitWords) {
                    dal.addTagToDatabase(tokenid, tag);
                }
            }
        }
    }

    public DefaultTableModel readTags(DefaultTableModel readTagTableModel) {
        return dal.readTags(readTagTableModel);
    }
}
