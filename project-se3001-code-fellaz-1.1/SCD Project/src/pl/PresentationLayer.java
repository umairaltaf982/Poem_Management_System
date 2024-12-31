package pl;

import bll.BusinessLogic;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.*;
import com.formdev.flatlaf.themes.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.sql.SQLException;
import javax.swing.*;
import static javax.swing.WindowConstants.*;
import javax.swing.table.DefaultTableModel;
import raven.alerts.MessageAlerts;
import raven.drawer.Drawer;
import raven.popup.GlassPanePopup;

public class PresentationLayer extends JFrame implements ActionListener {

    private static PresentationLayer instance;

    private BusinessLogic bll;

    private JPanel panel1, panel2;
    private JButton menuButton, modeButton;

    // Poem Graphics
    private JButton plusButton, addPoem;
    private JLabel bookNameLabel, poemTitleLabel, misra1Label, misra2Label;
    private JTextField poemTitleField, misra1TextField, misra2TextField;

    private JButton viewPoemButton, addVersesButton, updatePoemButton, deletePoemButton;
    private JComboBox poemsComboBox;

    // Book Graphics
    private JButton bookCreateButton, bookUpdateButton, bookDeleteButton;
    private JLabel bookNameJLabel, authorNameLabel, dateLabel, deleteBookLabel;
    private JTextField bookNameField, authorNameField;
    private DefaultTableModel model;
    private JTable readBookTable;
    private JScrollPane scrollPane;

    private JButton viewBookButton;

    // Root Graphics
    private JTable rootsTable;
    private JScrollPane rootsScrollPane;
    private JTextField arabicRootField, newArabicRootField;
    private JLabel addRootLabel, deleteRootLabel, updateRootLabel, previousRootLabel;
    private JComboBox rootComboBox;
    private JButton saveButton, updateButton, deleteButton;

    private JTextArea versesTextArea;
    private JButton tokenizeButton, verifyButton, verifyAllButton, searchButton, assignRootButton;
    private JTable verifyRootsTable, rootsTable2, showDataTable, searchTable, tokenTable;
    private DefaultTableModel verifyRootTableModel, showDatatableModel, rootsTableModel2, searchRootModel, tokenTableModel;
    private JScrollPane rootsScrollPane2, verifyRootScrollPane, showDatascrollPane, searchScrollPane, tokenScrollPane;
    private int selectedSearchResultRow = -1;

    // Tag Graphic
    private DefaultTableModel assignTagTableModel, readTagTableModel;
    private JTable assignTagTable, readTagTable;
    private JScrollPane assignTagScrollPane, readTagScrollPane;
    private JButton assignTagButton;

    private JComboBox booksComboBox;

    private Color color;
    int misraHeight = 260, plusButtonHeight = 256;
    private ArrayList<String> misra1List, misra2List;
    private ArrayList<Integer> verseIDList;
    private JDateChooser dateChooser;

    public PresentationLayer(BusinessLogic businessLogic) {

        bll = businessLogic;

        GlassPanePopup.install(this);
        MainClass menu = new MainClass();
        Drawer.getInstance().setDrawerBuilder(menu);

        panel1 = new JPanel(null);
        panel1.setBounds(0, 0, 85, 720);
        panel1.setBackground(new Color(46, 47, 48));

        menuButton = new JButton();
        menuButton.setBounds(20, 10, 45, 45);
        menuButton.setIcon(new FlatSVGIcon("pl/icons/menu.svg", 25, 25));
        menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0;"
                + "innerFocusWidth:0;");

        modeButton = new JButton();
        modeButton.setBounds(20, 75, 45, 45);
        modeButton.setIcon(new FlatSVGIcon("pl/icons/dark.svg", 25, 25));
        modeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        modeButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0;"
                + "innerFocusWidth:0;");

        panel1.add(menuButton);
        panel1.add(modeButton);

        add(panel1);

        panel2 = new JPanel(null);
        panel2.setBounds(180, 0, 900, 720);

        color = new Color(0, 122, 255);

        //Poem CRUD Graphics
        bookNameLabel = new JLabel("Book Name:");
        bookNameLabel.setBounds(100, 32, 150, 36);
        bookNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));

        poemTitleLabel = new JLabel("Poem Title:");
        poemTitleLabel.setBounds(100, 112, 150, 36);
        poemTitleLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));

        poemTitleField = new JTextField();
        poemTitleField.setBounds(300, 116, 250, 30);

        addPoem = new JButton("Add Poem");
        addPoem.setBounds(662, 32, 120, 36);
        addPoem.setBackground(color);
        addPoem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        addPoem.setForeground(Color.WHITE);

        misra1Label = new JLabel("مصرع ١");
        misra1Label.setBounds(675, 190, 100, 36);
        misra1Label.setFont(new Font("Times New Roman", Font.BOLD, 28));

        misra2Label = new JLabel("مصرع ٢");
        misra2Label.setBounds(375, 190, 100, 36);
        misra2Label.setFont(new Font("Times New Roman", Font.BOLD, 28));

        misra1TextField = new JTextField();
        misra1TextField.setBounds(600, 260, 250, 30);

        misra2TextField = new JTextField();
        misra2TextField.setBounds(300, 260, 250, 30);

        plusButton = new JButton("+");
        plusButton.setBounds(100, 256, 120, 36);
        plusButton.setBackground(color);
        plusButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        plusButton.setForeground(Color.WHITE);

        viewPoemButton = new JButton("View Poem");
        viewPoemButton.setBounds(662, 32, 120, 36);
        viewPoemButton.setBackground(color);
        viewPoemButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        viewPoemButton.setForeground(Color.WHITE);

        addVersesButton = new JButton("Add Verses");
        addVersesButton.setBounds(662, 32, 120, 36);
        addVersesButton.setBackground(color);
        addVersesButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        addVersesButton.setForeground(Color.WHITE);

        updatePoemButton = new JButton("Update Poem");
        updatePoemButton.setBounds(662, 32, 132, 36);
        updatePoemButton.setBackground(color);
        updatePoemButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        updatePoemButton.setForeground(Color.WHITE);

        deletePoemButton = new JButton("Delete Poem");
        deletePoemButton.setBounds(662, 32, 128, 36);
        deletePoemButton.setBackground(color);
        deletePoemButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        deletePoemButton.setForeground(Color.WHITE);

        // Book CRUD Graphics
        bookNameJLabel = new JLabel("Book Name:");
        bookNameJLabel.setBounds(150, 100, 150, 36);
        bookNameJLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));

        bookNameField = new JTextField();
        bookNameField.setBounds(350, 104, 250, 30);

        authorNameLabel = new JLabel("Author Name:");
        authorNameLabel.setBounds(150, 180, 175, 36);
        authorNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));

        authorNameField = new JTextField();
        authorNameField.setBounds(350, 184, 250, 30);

        dateLabel = new JLabel("Author DOD:");
        dateLabel.setBounds(150, 260, 165, 36);
        dateLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(350, 264, 250, 30);

        bookCreateButton = new JButton("Create");
        bookCreateButton.setBounds(300, 365, 120, 36);
        bookCreateButton.setBackground(color);
        bookCreateButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        bookCreateButton.setForeground(Color.WHITE);

        bookUpdateButton = new JButton("Update");
        bookUpdateButton.setBounds(300, 365, 120, 36);
        bookUpdateButton.setBackground(color);
        bookUpdateButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        bookUpdateButton.setForeground(Color.WHITE);

        deleteBookLabel = new JLabel("Select Book:");
        deleteBookLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));

        bookDeleteButton = new JButton("Delete");
        bookDeleteButton.setBounds(350, 250, 120, 36);
        bookDeleteButton.setBackground(color);
        bookDeleteButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        bookDeleteButton.setForeground(Color.WHITE);

        viewBookButton = new JButton("View Book");
        viewBookButton.setBounds(662, 32, 120, 36);
        viewBookButton.setBackground(color);
        viewBookButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        viewBookButton.setForeground(Color.WHITE);

        // Root CRUD Graphics
        addRootLabel = new JLabel("يضيف");
        addRootLabel.setBounds(675, 110, 100, 36);
        addRootLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));

        deleteRootLabel = new JLabel("يمسح");
        deleteRootLabel.setBounds(675, 110, 100, 36);
        deleteRootLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));

        previousRootLabel = new JLabel("سابق");
        previousRootLabel.setBounds(675, 110, 100, 36);
        previousRootLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));

        updateRootLabel = new JLabel("تحديث");
        updateRootLabel.setBounds(375, 110, 100, 36);
        updateRootLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));

        rootsTable = new JTable();
        rootsScrollPane = new JScrollPane(rootsTable);
        rootsScrollPane.setBounds(100, 75, 700, 500);

        arabicRootField = new JTextField();
        arabicRootField.setBounds(600, 180, 250, 30);

        newArabicRootField = new JTextField();
        newArabicRootField.setBounds(300, 180, 250, 30);

        saveButton = new JButton("Save");
        saveButton.setBounds(100, 176, 120, 36);
        saveButton.setBackground(color);
        saveButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        saveButton.setForeground(Color.WHITE);

        updateButton = new JButton("Update");
        updateButton.setBounds(100, 176, 120, 36);
        updateButton.setBackground(color);
        updateButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        updateButton.setForeground(Color.WHITE);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(100, 176, 120, 36);
        deleteButton.setBackground(color);
        deleteButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        deleteButton.setForeground(Color.WHITE);

// Root iteration 2
        versesTextArea = new JTextArea();
        JScrollPane versesScrollPane = new JScrollPane(versesTextArea);

        tokenizeButton = new JButton("Tokenize");
        tokenizeButton.setBounds(662, 112, 120, 36);
        tokenizeButton.setBackground(color);
        tokenizeButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        tokenizeButton.setForeground(Color.WHITE);
        tokenizeButton.addActionListener(this);

        assignRootButton = new JButton("Assign Root");
        assignRootButton.setBounds(662, 112, 125, 36);
        assignRootButton.setBackground(color);
        assignRootButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        assignRootButton.setForeground(Color.WHITE);
        assignRootButton.addActionListener(this);

        verifyButton = new JButton("Verify");
        verifyButton.setBounds(275, 560, 120, 36);
        verifyButton.setBackground(color);
        verifyButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        verifyButton.setForeground(Color.WHITE);
        verifyButton.addActionListener(this);

        verifyAllButton = new JButton("Verify All");
        verifyAllButton.setBounds(475, 560, 120, 36);
        verifyAllButton.setBackground(color);
        verifyAllButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        verifyAllButton.setForeground(Color.WHITE);
        verifyAllButton.addActionListener(this);

        rootsTableModel2 = new DefaultTableModel();
        rootsTableModel2.addColumn("Word");
        rootsTableModel2.addColumn("Root");
        rootsTable2 = new JTable(rootsTableModel2);
        rootsScrollPane2 = new JScrollPane(rootsTable2);

        tokenTableModel = new DefaultTableModel();
        tokenTableModel.addColumn("Word");
        tokenTable = new JTable(tokenTableModel);
        tokenScrollPane = new JScrollPane(tokenTable);

        verifyRootTableModel = new DefaultTableModel();
        verifyRootTableModel.addColumn("Misra 1");
        verifyRootTableModel.addColumn("Misra 2");
        verifyRootTableModel.addColumn("Root");
        verifyRootTableModel.addColumn("Status");

        showDatatableModel = new DefaultTableModel();
        showDatatableModel.addColumn("Misra 1");
        showDatatableModel.addColumn("Misra 2");
        showDatatableModel.addColumn("Root");
        showDatatableModel.addColumn("Status");

        searchButton = new JButton("Search");
        searchButton.setBounds(662, 32, 120, 36);
        searchButton.setBackground(color);
        searchButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(this);

        searchRootModel = new DefaultTableModel();
        searchRootModel.addColumn("Misra 1");
        searchRootModel.addColumn("Misra 2");
        searchRootModel.addColumn("Root");

//Tag Iteration 2
        assignTagTableModel = new DefaultTableModel();
        assignTagTableModel.addColumn("ID");
        assignTagTableModel.addColumn("Tokens");

        assignTagButton = new JButton("Assign Tags");
        assignTagButton.setBounds(375, 560, 120, 36);
        assignTagButton.setBackground(color);
        assignTagButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        assignTagButton.setForeground(Color.WHITE);
        assignTagButton.addActionListener(this);

        readTagTableModel = new DefaultTableModel();
        readTagTableModel.addColumn("Tokens");
        readTagTableModel.addColumn("Tags");

        add(panel2);

        menuButton.addActionListener(this);
        modeButton.addActionListener(this);

        addPoem.addActionListener(this);
        plusButton.addActionListener(this);
        viewPoemButton.addActionListener(this);
        addVersesButton.addActionListener(this);
        updatePoemButton.addActionListener(this);
        deletePoemButton.addActionListener(this);

        bookCreateButton.addActionListener(this);
        bookUpdateButton.addActionListener(this);
        bookDeleteButton.addActionListener(this);
        viewBookButton.addActionListener(this);

        saveButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        setSize(1080, 720);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons/icon.png")));
        setTitle("Arabic Poem Management System");
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static PresentationLayer getInstance() {
        if (instance == null) {
            instance = new PresentationLayer(BusinessLogic.getInstance());
        }
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuButton) {
            Drawer.getInstance().showDrawer();
        }
        if (e.getSource() == modeButton) {
            if (!FlatLaf.isLafDark()) {
                FlatAnimatedLafChange.showSnapshot();
                modeButton.setIcon(new FlatSVGIcon("pl/icons/dark.svg", 25, 25));
                FlatMacDarkLaf.setup();
                menuButton.setBackground(new Color(90, 90, 90));
                modeButton.setBackground(new Color(90, 90, 90));
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
                panel1.setBackground(new Color(46, 47, 48));
                panel1.repaint();
            } else {
                FlatAnimatedLafChange.showSnapshot();
                FlatMacLightLaf.setup();
                menuButton.setIcon(new FlatSVGIcon("pl/icons/menuLight.svg", 25, 25));
                menuButton.setBackground(color);
                modeButton.setIcon(new FlatSVGIcon("pl/icons/light.svg", 25, 25));
                modeButton.setBackground(color);
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
                panel1.setBackground(new Color(235, 245, 255));
                panel1.repaint();
            }
        }
        if (e.getSource() == addPoem) {
            misra1List.add(misra1TextField.getText());
            misra2List.add(misra2TextField.getText());

            String bookName = (String) booksComboBox.getItemAt(booksComboBox.getSelectedIndex());
            String poemTitle = poemTitleField.getText();
            if (bll.addPoem(bookName, poemTitle, misra1List, misra2List)) {
                MessageAlerts.getInstance().showMessage("Successful!", "Poem Added Successfully", MessageAlerts.MessageType.SUCCESS);
            } else {
                MessageAlerts.getInstance().showMessage("Error!", "There is an error in adding poem", MessageAlerts.MessageType.ERROR);
            }
            poemTitleField.setText("");
            panel2.removeAll();
            panel2.repaint();
        }
        if (e.getSource() == plusButton) {
            panel2.remove(plusButton);

            misra1List.add(misra1TextField.getText());
            misra2List.add(misra2TextField.getText());

            misraHeight += 50;
            plusButtonHeight += 50;

            misra1TextField = new JTextField();
            misra1TextField.setBounds(600, misraHeight, 250, 30);

            misra2TextField = new JTextField();
            misra2TextField.setBounds(300, misraHeight, 250, 30);

            plusButton = new JButton("+");
            plusButton.setBounds(100, plusButtonHeight, 120, 36);
            plusButton.setBackground(color);
            plusButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            plusButton.setForeground(Color.WHITE);

            panel2.add(misra1TextField);
            panel2.add(misra2TextField);
            panel2.add(plusButton);

            panel2.repaint();
            plusButton.addActionListener(this);
        }
        if (e.getSource() == viewPoemButton) {
            model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Misra 1");
            model.addColumn("Misra 2");

            String poemTitle = (String) poemsComboBox.getItemAt(poemsComboBox.getSelectedIndex());

            readBookTable = new JTable(bll.readPoem(model, poemTitle));
            scrollPane = new JScrollPane(readBookTable);
            scrollPane.setBounds(100, 200, 700, 520);

            panel2.add(scrollPane);

            panel2.repaint();
        }
        if (e.getSource() == updatePoemButton) {
            String bookName = (String) booksComboBox.getItemAt(booksComboBox.getSelectedIndex());
            String prevPoemTitle = (String) poemsComboBox.getItemAt(poemsComboBox.getSelectedIndex());
            String poemTitle = poemTitleField.getText();
            ArrayList<String> misra1 = new ArrayList<>();
            ArrayList<String> misra2 = new ArrayList<>();
            for (int i = 0; i < model.getRowCount(); i++) {
                misra1.add((String) model.getValueAt(i, 1));
                misra2.add((String) model.getValueAt(i, 2));
            }
            if (bll.updatePoem(bookName, prevPoemTitle, poemTitle, misra1, misra2, verseIDList)) {
                MessageAlerts.getInstance().showMessage("Successful!", "Poem Updated Successfully", MessageAlerts.MessageType.SUCCESS);
            } else {
                MessageAlerts.getInstance().showMessage("Error!", "There is an error in updating poem", MessageAlerts.MessageType.ERROR);
            }
            poemTitleField.setText("");
            panel2.removeAll();
            panel2.repaint();
        }
        if (e.getSource() == deletePoemButton) {
            String bookName = (String) booksComboBox.getItemAt(booksComboBox.getSelectedIndex());
            String poemTitle = (String) poemsComboBox.getItemAt(poemsComboBox.getSelectedIndex());
            if (bll.deletePoem(bookName, poemTitle)) {
                MessageAlerts.getInstance().showMessage("Successful!", "Poem Deleted Successfully", MessageAlerts.MessageType.SUCCESS);
                panel2.removeAll();
            } else {
                MessageAlerts.getInstance().showMessage("Error!", "There is an error in deleting poem", MessageAlerts.MessageType.ERROR);
            }
            panel2.repaint();
        }
        if (e.getSource() == addVersesButton) {
            String poemTitle = (String) poemsComboBox.getItemAt(poemsComboBox.getSelectedIndex());

            misra1List.add(misra1TextField.getText());
            misra2List.add(misra2TextField.getText());

            if (bll.addVerses(poemTitle, misra1List, misra2List)) {
                MessageAlerts.getInstance().showMessage("Successful!", "Verses Added Successfully", MessageAlerts.MessageType.SUCCESS);
            } else {
                MessageAlerts.getInstance().showMessage("Error!", "There is an error in adding verses", MessageAlerts.MessageType.ERROR);
            }
            panel2.removeAll();
            panel2.repaint();

        }
        if (e.getSource() == assignRootButton) {
            bll.assignRootToVerse(model, rootsTableModel2);
            panel2.remove(scrollPane);
            rootsScrollPane2.setBounds(100, 200, 700, 520);
            panel2.add(rootsScrollPane2);
            MessageAlerts.getInstance().showMessage("Successful!", "Roots Assigned Successfully", MessageAlerts.MessageType.SUCCESS);
            panel2.repaint();
        }
        if (e.getSource() == tokenizeButton) {
            bll.tokenizeAllVerses(model, tokenTableModel);
            panel2.remove(scrollPane);
            tokenScrollPane.setBounds(100, 200, 700, 520);
            panel2.add(tokenScrollPane);
            MessageAlerts.getInstance().showMessage("Successful!", "Tokens Assigned Successfully", MessageAlerts.MessageType.SUCCESS);
            panel2.repaint();
        }
        if (e.getSource() == bookCreateButton) {
            String bookname = bookNameField.getText();
            String authorName = authorNameField.getText();
            Date tempDate = dateChooser.getDate();
            java.sql.Date authorDOD = new java.sql.Date(tempDate.getTime());
            if (bll.createBook(bookname, authorName, authorDOD)) {
                JOptionPane.showMessageDialog(null, "Book Created Successfully!");
                panel2.removeAll();
                bookNameField.setText("");
                authorNameField.setText("");
            }
            panel2.repaint();
        }
        if (e.getSource() == bookUpdateButton) {
            String prevBookName = (String) booksComboBox.getItemAt(booksComboBox.getSelectedIndex());
            String bookname = bookNameField.getText();
            String authorName = authorNameField.getText();
            Date tempDate = dateChooser.getDate();
            java.sql.Date authorDOD = new java.sql.Date(tempDate.getTime());
            if (bll.updateBook(prevBookName, bookname, authorName, authorDOD)) {
                JOptionPane.showMessageDialog(null, "Book Updated Successfully!");
                panel2.removeAll();
                bookNameField.setText("");
                authorNameField.setText("");
            }
            panel2.repaint();
        }
        if (e.getSource() == bookDeleteButton) {
            String bookName = (String) booksComboBox.getItemAt(booksComboBox.getSelectedIndex());
            if (bll.deleteBook(bookName)) {
                JOptionPane.showMessageDialog(null, "Book Deleted Successfully!");
                panel2.removeAll();
            }
            panel2.repaint();
        }
        if (e.getSource() == viewBookButton) {
            model = new DefaultTableModel();
            model.addColumn("Poems");

            String bookName = (String) booksComboBox.getItemAt(booksComboBox.getSelectedIndex());

            readBookTable = new JTable(bll.readSingleBook(model, bookName));
            scrollPane = new JScrollPane(readBookTable);
            scrollPane.setBounds(100, 200, 700, 520);

            panel2.add(scrollPane);

            panel2.repaint();
        }
        if (e.getSource() == saveButton) {
            String arabicRoot = arabicRootField.getText();
            if (arabicRoot != null && !arabicRoot.isEmpty()) {
                bll.addRoot(arabicRoot);
                JOptionPane.showMessageDialog(null, "Root Created Successfully!");
            }
            panel2.removeAll();
            panel2.repaint();
        }
        if (e.getSource() == updateButton) {
            String selectedRoot = (String) rootComboBox.getSelectedItem();
            String newRoot = newArabicRootField.getText();
            if (selectedRoot != null && !newRoot.isEmpty()) {
                bll.updateRoot(selectedRoot, newRoot);
                JOptionPane.showMessageDialog(null, "Root Updated Successfully!");
            }
            panel2.removeAll();
            panel2.repaint();
        }
        if (e.getSource() == deleteButton) {
            String selectedRoot = (String) rootComboBox.getSelectedItem();
            if (selectedRoot != null) {
                bll.deleteRoot(selectedRoot);
                JOptionPane.showMessageDialog(null, "Root Deleted Successfully!");
            }
            panel2.removeAll();
            panel2.repaint();
        }
        if (e.getSource() == verifyButton) {
            int selectedRow = verifyRootsTable.getSelectedRow();
            String oldRoot = (String) verifyRootTableModel.getValueAt(selectedRow, 2);
            String newRoot = JOptionPane.showInputDialog(this, "Enter the new root:", oldRoot);

            if (newRoot != null) {
                // Update the root in the roots table
                bll.verifyRoot(oldRoot, newRoot);

                // Update the root in the verification table
                verifyRootTableModel.setValueAt(newRoot, selectedRow, 2);
                verifyRootTableModel.removeRow(selectedRow);

            }
        }
        if (e.getSource() == verifyAllButton) {
            bll.verifyAllRoot();

            MessageAlerts.getInstance().showMessage("Successful!", "Roots Verified Successfully", MessageAlerts.MessageType.SUCCESS);

            panel2.removeAll();
        }
        if (e.getSource() == searchButton) {
            String rootToSearch = (String) rootComboBox.getItemAt(rootComboBox.getSelectedIndex());

            searchRootModel.setRowCount(0);
            searchRootModel = bll.searchRoot(searchRootModel, rootToSearch);

            searchTable = new JTable(searchRootModel);
            searchScrollPane = new JScrollPane(searchTable);
            searchScrollPane.setBounds(100, 120, 700, 500);

            panel2.add(searchScrollPane);

            panel2.repaint();
        }
        if (e.getSource() == assignTagButton) {
            bll.assignTags(assignTagTableModel);

            MessageAlerts.getInstance().showMessage("Successful!", "Tags Assigned Successfully", MessageAlerts.MessageType.SUCCESS);

            panel2.removeAll();
            panel2.repaint();
        }
    }

    public void menuActions(int menu, int subMenu) {
        if (menu == 0 && subMenu == 1) {
            misraHeight = 260;
            plusButtonHeight = 256;

            panel2.removeAll();

            booksComboBox = new JComboBox(bll.getBooksNames());
            booksComboBox.setBounds(300, 36, 250, 30);

            poemTitleLabel.setBounds(100, 112, 150, 36);
            poemTitleField.setText("");
            poemTitleField.setBounds(300, 116, 250, 30);

            misra1TextField.setText("");
            misra1TextField.setBounds(600, 260, 250, 30);

            misra2TextField.setText("");
            misra2TextField.setBounds(300, 260, 250, 30);

            plusButton.setBounds(100, 256, 120, 36);

            misra1List = new ArrayList<>();
            misra2List = new ArrayList<>();

            panel2.add(bookNameLabel);
            panel2.add(booksComboBox);
            panel2.add(poemTitleLabel);
            panel2.add(poemTitleField);
            panel2.add(addPoem);
            panel2.add(misra1Label);
            panel2.add(misra2Label);
            panel2.add(misra1TextField);
            panel2.add(misra2TextField);
            panel2.add(plusButton);

            panel2.repaint();
        }
        if (menu == 0 && subMenu == 2) {
            panel2.removeAll();
            ArrayList<String> lines = new ArrayList<>();
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(filePath)))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            try {
                bll.processPoemData(lines);
            } catch (SQLException e1) {
                System.err.println("Connection error: " + e1.getMessage());
            }
        }
        if (menu == 0 && subMenu == 3) {
            panel2.removeAll();

            booksComboBox = new JComboBox(bll.getBooksNames());
            booksComboBox.setBounds(300, 36, 250, 30);

            panel2.add(bookNameLabel);
            panel2.add(booksComboBox);

            panel2.repaint();

            booksComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String bookName = (String) booksComboBox.getItemAt(booksComboBox.getSelectedIndex());

                    poemsComboBox = new JComboBox(bll.getPoemTitles(bookName));
                    poemsComboBox.setBounds(300, 116, 250, 30);

                    panel2.add(poemTitleLabel);
                    panel2.add(poemsComboBox);

                    panel2.repaint();

                    poemsComboBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panel2.removeAll();

                            poemTitleLabel.setBounds(100, 32, 150, 36);
                            poemTitleField.setBounds(300, 36, 250, 30);

                            panel2.add(poemTitleLabel);
                            panel2.add(poemTitleField);
                            panel2.add(updatePoemButton);

                            model = new DefaultTableModel();
                            model.addColumn("ID");
                            model.addColumn("Misra 1");
                            model.addColumn("Misra 2");

                            String poemTitle = (String) poemsComboBox.getItemAt(poemsComboBox.getSelectedIndex());

                            readBookTable = new JTable(bll.readPoem(model, poemTitle));
                            scrollPane = new JScrollPane(readBookTable);
                            scrollPane.setBounds(100, 100, 700, 520);

                            panel2.add(scrollPane);

                            verseIDList = new ArrayList<>();
                            for (int i = 0; i < model.getRowCount(); i++) {
                                verseIDList.add(Integer.parseInt(model.getValueAt(i, 0).toString()));
                            }

                            panel2.repaint();
                        }
                    });
                }
            });
        }
        if (menu == 0 && subMenu == 4) {
            panel2.removeAll();

            booksComboBox = new JComboBox(bll.getBooksNames());
            booksComboBox.setBounds(300, 36, 250, 30);

            panel2.add(bookNameLabel);
            panel2.add(booksComboBox);

            booksComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String bookName = (String) booksComboBox.getItemAt(booksComboBox.getSelectedIndex());

                    poemsComboBox = new JComboBox(bll.getPoemTitles(bookName));
                    poemsComboBox.setBounds(300, 116, 250, 30);

                    panel2.add(poemTitleLabel);
                    panel2.add(poemsComboBox);
                    panel2.add(deletePoemButton);
                    panel2.repaint();
                }
            });

            panel2.repaint();
        }
        if (menu == 1) {
            panel2.removeAll();

            booksComboBox = new JComboBox(bll.getBooksNames());
            booksComboBox.setBounds(300, 36, 250, 30);

            panel2.add(bookNameLabel);
            panel2.add(booksComboBox);

            booksComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String bookName = (String) booksComboBox.getItemAt(booksComboBox.getSelectedIndex());

                    poemsComboBox = new JComboBox(bll.getPoemTitles(bookName));
                    poemsComboBox.setBounds(300, 116, 250, 30);

                    panel2.add(poemTitleLabel);
                    panel2.add(poemsComboBox);
                    panel2.add(viewPoemButton);
                    panel2.add(assignRootButton);
                    panel2.repaint();
                }
            });
            panel2.repaint();
        }
        if (menu == 2) {
            misraHeight = 260;
            plusButtonHeight = 256;

            panel2.removeAll();

            booksComboBox = new JComboBox(bll.getBooksNames());
            booksComboBox.setBounds(300, 36, 250, 30);

            panel2.add(bookNameLabel);
            panel2.add(booksComboBox);

            panel2.repaint();

            booksComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String bookName = (String) booksComboBox.getItemAt(booksComboBox.getSelectedIndex());

                    poemsComboBox = new JComboBox(bll.getPoemTitles(bookName));
                    poemsComboBox.setBounds(300, 116, 250, 30);

                    panel2.add(poemTitleLabel);
                    panel2.add(poemsComboBox);

                    panel2.repaint();

                    poemsComboBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            misra1TextField.setText("");
                            misra1TextField.setBounds(600, 260, 250, 30);

                            misra2TextField.setText("");
                            misra2TextField.setBounds(300, 260, 250, 30);

                            plusButton.setBounds(100, 256, 120, 36);

                            misra1List = new ArrayList<>();
                            misra2List = new ArrayList<>();

                            panel2.add(addVersesButton);
                            panel2.add(misra1Label);
                            panel2.add(misra2Label);
                            panel2.add(misra1TextField);
                            panel2.add(misra2TextField);
                            panel2.add(plusButton);

                            panel2.repaint();
                        }
                    });
                }
            });
        }
        if (menu == 3 && subMenu == 1) {
            panel2.removeAll();

            panel2.add(bookNameJLabel);
            panel2.add(bookNameField);
            panel2.add(authorNameLabel);
            panel2.add(authorNameField);
            panel2.add(dateLabel);
            panel2.add(dateChooser);
            panel2.add(bookCreateButton);

            panel2.revalidate();
            panel2.repaint();
        }
        if (menu == 3 && subMenu == 2) {
            panel2.removeAll();

            model = new DefaultTableModel();
            model.addColumn("Book Name");
            model.addColumn("Author Name");
            model.addColumn("Author DOD");

            readBookTable = new JTable(bll.readBook(model));
            scrollPane = new JScrollPane(readBookTable);
            scrollPane.setBounds(100, 75, 700, 500);

            panel2.add(scrollPane);

            panel2.repaint();
        }
        if (menu == 3 && subMenu == 3) {
            panel2.removeAll();

            booksComboBox = new JComboBox(bll.getBooksNames());
            booksComboBox.setBounds(350, 24, 250, 30);

            deleteBookLabel.setBounds(150, 24, 150, 36);

            panel2.add(deleteBookLabel);
            panel2.add(booksComboBox);
            panel2.add(bookNameJLabel);
            panel2.add(bookNameField);
            panel2.add(authorNameLabel);
            panel2.add(authorNameField);
            panel2.add(dateLabel);
            panel2.add(dateChooser);
            panel2.add(bookUpdateButton);

            panel2.revalidate();
            panel2.repaint();
        }
        if (menu == 3 && subMenu == 4) {
            panel2.removeAll();

            booksComboBox = new JComboBox(bll.getBooksNames());
            booksComboBox.setBounds(350, 150, 250, 30);

            deleteBookLabel.setBounds(150, 146, 150, 36);

            panel2.add(deleteBookLabel);
            panel2.add(booksComboBox);
            panel2.add(bookDeleteButton);

            panel2.repaint();
        }
        if (menu == 4) {
            panel2.removeAll();

            booksComboBox = new JComboBox(bll.getBooksNames());
            booksComboBox.setBounds(300, 36, 250, 30);

            panel2.add(bookNameLabel);
            panel2.add(booksComboBox);
            panel2.add(viewBookButton);

            panel2.repaint();
        }
        if (menu == 5 && subMenu == 1) {
            panel2.removeAll();

            panel2.add(addRootLabel);
            panel2.add(arabicRootField);
            panel2.add(saveButton);

            panel2.repaint();
        }
        if (menu == 5 && subMenu == 2) {
            panel2.removeAll();

            DefaultTableModel rootsTableModel = new DefaultTableModel();
            rootsTableModel.addColumn("Roots");

            String roots = bll.readRoots();
            String[] rootArray = roots.split("\n");

            for (String root : rootArray) {
                rootsTableModel.addRow(new Object[]{root});
            }
            rootsTable.setModel(rootsTableModel);

            panel2.add(rootsScrollPane);

            panel2.repaint();
        }
        if (menu == 5 && subMenu == 3) {
            panel2.removeAll();

            rootComboBox = new JComboBox();
            rootComboBox.setBounds(600, 180, 250, 30);
            populateRootComboBox();

            panel2.add(rootComboBox);
            panel2.add(previousRootLabel);
            panel2.add(updateRootLabel);
            panel2.add(newArabicRootField);
            panel2.add(updateButton);

            panel2.repaint();
        }
        if (menu == 5 && subMenu == 4) {
            panel2.removeAll();
            rootComboBox = new JComboBox();
            rootComboBox.setBounds(600, 180, 250, 30);
            populateRootComboBox();

            panel2.add(rootComboBox);
            panel2.add(deleteRootLabel);
            panel2.add(deleteButton);

            panel2.repaint();
        }
        if (menu == 6) {
            panel2.removeAll();

            verifyRootTableModel = bll.displayRoots(verifyRootTableModel);

            verifyRootsTable = new JTable(verifyRootTableModel);
            verifyRootScrollPane = new JScrollPane(verifyRootsTable);
            verifyRootScrollPane.setBounds(100, 25, 700, 500);

            panel2.add(verifyRootScrollPane);
            panel2.add(verifyButton);
            panel2.add(verifyAllButton);

            panel2.repaint();
        }
        if (menu == 7) {
            panel2.removeAll();

            showDatatableModel = bll.displayAllRoot(showDatatableModel);

            showDataTable = new JTable(showDatatableModel);
            showDatascrollPane = new JScrollPane(showDataTable);
            showDatascrollPane.setBounds(100, 25, 700, 500);

            panel2.add(showDatascrollPane);

            panel2.repaint();
        }
        if (menu == 8) {
            panel2.removeAll();

            rootComboBox = new JComboBox();
            rootComboBox.setBounds(300, 36, 250, 30);
            populateRootComboBox();

            panel2.add(rootComboBox);
            panel2.add(searchButton);

            panel2.repaint();
        }
        if (menu == 9) {
            panel2.removeAll();

            assignTagTableModel = bll.readTokens(assignTagTableModel);
            assignTagTable = new JTable(assignTagTableModel);
            assignTagScrollPane = new JScrollPane(assignTagTable);
            assignTagScrollPane.setBounds(100, 25, 700, 500);

            panel2.add(assignTagScrollPane);
            panel2.add(assignTagButton);

            panel2.repaint();
        }
        if (menu == 10) {
            panel2.removeAll();

            readTagTableModel = bll.readTags(readTagTableModel);
            readTagTable = new JTable(readTagTableModel);
            readTagScrollPane = new JScrollPane(readTagTable);
            readTagScrollPane.setBounds(100, 25, 700, 500);

            panel2.add(readTagScrollPane);

            panel2.repaint();
        }
        if (menu == 11) {
            panel2.removeAll();

            booksComboBox = new JComboBox(bll.getBooksNames());
            booksComboBox.setBounds(300, 36, 250, 30);

            panel2.add(bookNameLabel);
            panel2.add(booksComboBox);

            booksComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String bookName = (String) booksComboBox.getItemAt(booksComboBox.getSelectedIndex());

                    poemsComboBox = new JComboBox(bll.getPoemTitles(bookName));
                    poemsComboBox.setBounds(300, 116, 250, 30);

                    panel2.add(poemTitleLabel);
                    panel2.add(poemsComboBox);
                    panel2.add(viewPoemButton);
                    panel2.add(tokenizeButton);
                    panel2.repaint();
                }
            });
            panel2.repaint();
        }
    }

    private void populateRootComboBox() {
        rootComboBox.removeAllItems();
        String roots = bll.readRoots();
        String[] rootArray = roots.split("\n");
        for (String root : rootArray) {
            rootComboBox.addItem(root);
        }
    }
}
