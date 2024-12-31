//package org.multilangdict.pl;
//
//import com.formdev.flatlaf.*;
//import com.formdev.flatlaf.extras.*;
//import com.formdev.flatlaf.themes.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.*;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//
//import org.multilangdict.bll.IBLLFacade;
//import raven.drawer.Drawer;
//import raven.popup.GlassPanePopup;
//
//public class TESTUI extends JFrame implements ActionListener {
//
//    private final IBLLFacade bllFacade;
//
//    private JPanel panel1, panel2;
//    private JButton menuButton, modeButton;
//
//    // Poem Graphics
//    private JButton submitAddWord, addWord;
//    private JLabel addWordLabel, moreDetailsLabel, persianMeaningLabel, urduMeaningLabel, posLabel, rootLabel;
//    private JTextField addWordTextField, persianMeaningTextField, urduMeaningTextField, posTextField, rootTextField;
//
//    private JButton viewPoemButton, addVersesButton, updatePoemButton, deletePoemButton;
//
//    // Book Graphics
//    private JButton bookCreateButton, bookUpdateButton, bookDeleteButton;
//    private JLabel bookNameJLabel, authorNameLabel, dateLabel, deleteBookLabel;
//    private JTextField bookNameField, authorNameField;
//    private DefaultTableModel model;
//    private JTable readBookTable;
//    private JScrollPane scrollPane;
//
//    private JButton viewBookButton;
//
//    // Root Graphics
//    private JTable rootsTable;
//    private JScrollPane rootsScrollPane;
//    private JTextField arabicRootField, newArabicRootField;
//    private JLabel addRootLabel, deleteRootLabel, updateRootLabel, previousRootLabel;
//    private JComboBox rootComboBox;
//    private JButton saveButton, updateButton, deleteButton;
//
//    private JTextArea versesTextArea;
//    private JButton tokenizeButton, verifyButton, verifyAllButton, searchButton, assignRootButton;
//    private JTable verifyRootsTable, rootsTable2, showDataTable, searchTable, tokenTable;
//    private DefaultTableModel verifyRootTableModel, showDatatableModel, rootsTableModel2, searchRootModel, tokenTableModel;
//    private JScrollPane rootsScrollPane2, verifyRootScrollPane, showDatascrollPane, searchScrollPane, tokenScrollPane;
//    private int selectedSearchResultRow = -1;
//
//    // Tag Graphic
//    private DefaultTableModel assignTagTableModel, readTagTableModel;
//    private JTable assignTagTable, readTagTable;
//    private JScrollPane assignTagScrollPane, readTagScrollPane;
//    private JButton assignTagButton;
//
//    private JComboBox booksComboBox;
//
//    private Color color;
//    int misraHeight = 260, plusButtonHeight = 256;
//    private ArrayList<String> misra1List, misra2List;
//    private ArrayList<Integer> verseIDList;
//
//    public TESTUI(IBLLFacade bllFacade) {
//        this.bllFacade = bllFacade;
//
//        GlassPanePopup.install(this);
//        MainClass menu = new MainClass(this); // Pass TESTUI instance to MainClass
//        Drawer.getInstance().setDrawerBuilder(menu);
//
//        panel1 = new JPanel(null);
//        panel1.setBounds(0, 0, 85, 720);
//        panel1.setBackground(new Color(46, 47, 48));
//
//        menuButton = new JButton();
//        menuButton.setBounds(20, 10, 45, 45);
//        menuButton.setIcon(new FlatSVGIcon("icons/menu.svg", 25, 25));
//        menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
//                + "arc:999;"
//                + "focusWidth:0;"
//                + "borderWidth:0;"
//                + "innerFocusWidth:0;");
//
//        modeButton = new JButton();
//        modeButton.setBounds(20, 75, 45, 45);
//        modeButton.setIcon(new FlatSVGIcon("icons/dark.svg", 25, 25));
//        modeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        modeButton.putClientProperty(FlatClientProperties.STYLE, ""
//                + "arc:999;"
//                + "focusWidth:0;"
//                + "borderWidth:0;"
//                + "innerFocusWidth:0;");
//
//        panel1.add(menuButton);
//        panel1.add(modeButton);
//
//        add(panel1);
//
//        panel2 = new JPanel(null);
//        panel2.setBounds(180, 0, 900, 720);
//
//        color = new Color(0, 122, 255);
//
//        //Poem CRUD Graphics
//        addWordLabel = new JLabel("Word:");
//        addWordLabel.setBounds(100, 32, 150, 36);
//        addWordLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
//
//        moreDetailsLabel = new JLabel("Add More Details");
//        moreDetailsLabel.setBounds(100, 112, 150, 36);
//        moreDetailsLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
//
//
//        addWord = new JButton("Add Word");
//        addWord.setBounds(662, 32, 120, 36);
//        addWord.setBackground(color);
//        addWord.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        addWord.setForeground(Color.WHITE);
//
//        urduMeaningLabel = new JLabel("Urdu Meaning");
//        urduMeaningLabel.setBounds(100, 300, 100, 36);
//        urduMeaningLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
//
//        urduMeaningTextField = new JTextField();
//        urduMeaningTextField.setBounds(500, 500, 250, 30);
//
//        persianMeaningLabel = new JLabel("Persian Meaning:");
//        persianMeaningLabel.setBounds(100, 400, 100, 36);
//        persianMeaningLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
//
//        persianMeaningTextField = new JTextField();
//        persianMeaningTextField.setBounds(300, 116, 250, 30);
//
//
//        posTextField = new JTextField();
//        posTextField.setBounds(300, 260, 250, 30);
//
//        submitAddWord = new JButton("Submit Details");
//        submitAddWord.setBounds(100, 256, 120, 36);
//        submitAddWord.setBackground(color);
//        submitAddWord.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        submitAddWord.setForeground(Color.WHITE);
//
//        viewPoemButton = new JButton("View Poem");
//        viewPoemButton.setBounds(662, 32, 120, 36);
//        viewPoemButton.setBackground(color);
//        viewPoemButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        viewPoemButton.setForeground(Color.WHITE);
//
//        addVersesButton = new JButton("Add Verses");
//        addVersesButton.setBounds(662, 32, 120, 36);
//        addVersesButton.setBackground(color);
//        addVersesButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        addVersesButton.setForeground(Color.WHITE);
//
//        updatePoemButton = new JButton("Update Poem");
//        updatePoemButton.setBounds(662, 32, 132, 36);
//        updatePoemButton.setBackground(color);
//        updatePoemButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        updatePoemButton.setForeground(Color.WHITE);
//
//        deletePoemButton = new JButton("Delete Poem");
//        deletePoemButton.setBounds(662, 32, 128, 36);
//        deletePoemButton.setBackground(color);
//        deletePoemButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        deletePoemButton.setForeground(Color.WHITE);
//
//        // Book CRUD Graphics
//        bookNameJLabel = new JLabel("Book Name:");
//        bookNameJLabel.setBounds(150, 100, 150, 36);
//        bookNameJLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
//
//        bookNameField = new JTextField();
//        bookNameField.setBounds(350, 104, 250, 30);
//
//        authorNameLabel = new JLabel("Author Name:");
//        authorNameLabel.setBounds(150, 180, 175, 36);
//        authorNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
//
//        authorNameField = new JTextField();
//        authorNameField.setBounds(350, 184, 250, 30);
//
//        dateLabel = new JLabel("Author DOD:");
//        dateLabel.setBounds(150, 260, 165, 36);
//        dateLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
//
//
//        bookCreateButton = new JButton("Create");
//        bookCreateButton.setBounds(300, 365, 120, 36);
//        bookCreateButton.setBackground(color);
//        bookCreateButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        bookCreateButton.setForeground(Color.WHITE);
//
//        bookUpdateButton = new JButton("Update");
//        bookUpdateButton.setBounds(300, 365, 120, 36);
//        bookUpdateButton.setBackground(color);
//        bookUpdateButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        bookUpdateButton.setForeground(Color.WHITE);
//
//        deleteBookLabel = new JLabel("Select Book:");
//        deleteBookLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
//
//        bookDeleteButton = new JButton("Delete");
//        bookDeleteButton.setBounds(350, 250, 120, 36);
//        bookDeleteButton.setBackground(color);
//        bookDeleteButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        bookDeleteButton.setForeground(Color.WHITE);
//
//        viewBookButton = new JButton("View Book");
//        viewBookButton.setBounds(662, 32, 120, 36);
//        viewBookButton.setBackground(color);
//        viewBookButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        viewBookButton.setForeground(Color.WHITE);
//
//        // Root CRUD Graphics
//        addRootLabel = new JLabel("يضيف");
//        addRootLabel.setBounds(675, 110, 100, 36);
//        addRootLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
//
//        deleteRootLabel = new JLabel("يمسح");
//        deleteRootLabel.setBounds(675, 110, 100, 36);
//        deleteRootLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
//
//        previousRootLabel = new JLabel("سابق");
//        previousRootLabel.setBounds(675, 110, 100, 36);
//        previousRootLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
//
//        updateRootLabel = new JLabel("تحديث");
//        updateRootLabel.setBounds(375, 110, 100, 36);
//        updateRootLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
//
//        rootsTable = new JTable();
//        rootsScrollPane = new JScrollPane(rootsTable);
//        rootsScrollPane.setBounds(100, 75, 700, 500);
//
//        arabicRootField = new JTextField();
//        arabicRootField.setBounds(600, 180, 250, 30);
//
//        newArabicRootField = new JTextField();
//        newArabicRootField.setBounds(300, 180, 250, 30);
//
//        saveButton = new JButton("Save");
//        saveButton.setBounds(100, 176, 120, 36);
//        saveButton.setBackground(color);
//        saveButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        saveButton.setForeground(Color.WHITE);
//
//        updateButton = new JButton("Update");
//        updateButton.setBounds(100, 176, 120, 36);
//        updateButton.setBackground(color);
//        updateButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        updateButton.setForeground(Color.WHITE);
//
//        deleteButton = new JButton("Delete");
//        deleteButton.setBounds(100, 176, 120, 36);
//        deleteButton.setBackground(color);
//        deleteButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        deleteButton.setForeground(Color.WHITE);
//
//// Root iteration 2
//        versesTextArea = new JTextArea();
//        JScrollPane versesScrollPane = new JScrollPane(versesTextArea);
//
//        tokenizeButton = new JButton("Tokenize");
//        tokenizeButton.setBounds(662, 112, 120, 36);
//        tokenizeButton.setBackground(color);
//        tokenizeButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        tokenizeButton.setForeground(Color.WHITE);
//        tokenizeButton.addActionListener(this);
//
//        assignRootButton = new JButton("Assign Root");
//        assignRootButton.setBounds(662, 112, 125, 36);
//        assignRootButton.setBackground(color);
//        assignRootButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        assignRootButton.setForeground(Color.WHITE);
//        assignRootButton.addActionListener(this);
//
//        verifyButton = new JButton("Verify");
//        verifyButton.setBounds(275, 560, 120, 36);
//        verifyButton.setBackground(color);
//        verifyButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        verifyButton.setForeground(Color.WHITE);
//        verifyButton.addActionListener(this);
//
//        verifyAllButton = new JButton("Verify All");
//        verifyAllButton.setBounds(475, 560, 120, 36);
//        verifyAllButton.setBackground(color);
//        verifyAllButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        verifyAllButton.setForeground(Color.WHITE);
//        verifyAllButton.addActionListener(this);
//
//        rootsTableModel2 = new DefaultTableModel();
//        rootsTableModel2.addColumn("Word");
//        rootsTableModel2.addColumn("Root");
//        rootsTable2 = new JTable(rootsTableModel2);
//        rootsScrollPane2 = new JScrollPane(rootsTable2);
//
//        tokenTableModel = new DefaultTableModel();
//        tokenTableModel.addColumn("Word");
//        tokenTable = new JTable(tokenTableModel);
//        tokenScrollPane = new JScrollPane(tokenTable);
//
//        verifyRootTableModel = new DefaultTableModel();
//        verifyRootTableModel.addColumn("Misra 1");
//        verifyRootTableModel.addColumn("Misra 2");
//        verifyRootTableModel.addColumn("Root");
//        verifyRootTableModel.addColumn("Status");
//
//        showDatatableModel = new DefaultTableModel();
//        showDatatableModel.addColumn("Misra 1");
//        showDatatableModel.addColumn("Misra 2");
//        showDatatableModel.addColumn("Root");
//        showDatatableModel.addColumn("Status");
//
//        searchButton = new JButton("Search");
//        searchButton.setBounds(662, 32, 120, 36);
//        searchButton.setBackground(color);
//        searchButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        searchButton.setForeground(Color.WHITE);
//        searchButton.addActionListener(this);
//
//        searchRootModel = new DefaultTableModel();
//        searchRootModel.addColumn("Misra 1");
//        searchRootModel.addColumn("Misra 2");
//        searchRootModel.addColumn("Root");
//
////Tag Iteration 2
//        assignTagTableModel = new DefaultTableModel();
//        assignTagTableModel.addColumn("ID");
//        assignTagTableModel.addColumn("Tokens");
//
//        assignTagButton = new JButton("Assign Tags");
//        assignTagButton.setBounds(375, 560, 120, 36);
//        assignTagButton.setBackground(color);
//        assignTagButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//        assignTagButton.setForeground(Color.WHITE);
//        assignTagButton.addActionListener(this);
//
//        readTagTableModel = new DefaultTableModel();
//        readTagTableModel.addColumn("Tokens");
//        readTagTableModel.addColumn("Tags");
//
//        add(panel2);
//
//        menuButton.addActionListener(this);
//        modeButton.addActionListener(this);
//
//        addWord.addActionListener(this);
//        submitAddWord.addActionListener(this);
//        viewPoemButton.addActionListener(this);
//        addVersesButton.addActionListener(this);
//        updatePoemButton.addActionListener(this);
//        deletePoemButton.addActionListener(this);
//
//        bookCreateButton.addActionListener(this);
//        bookUpdateButton.addActionListener(this);
//        bookDeleteButton.addActionListener(this);
//        viewBookButton.addActionListener(this);
//
//        saveButton.addActionListener(this);
//        updateButton.addActionListener(this);
//        deleteButton.addActionListener(this);
//
//        setSize(1080, 720);
//        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons/icon.png")));
//        setTitle("Arabic Poem Management System");
//        setLocationRelativeTo(null);
//        setLayout(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == menuButton) {
//            Drawer.getInstance().showDrawer();
//        }
//        if (e.getSource() == modeButton) {
//            if (!FlatLaf.isLafDark()) {
//                FlatAnimatedLafChange.showSnapshot();
//                modeButton.setIcon(new FlatSVGIcon("icons/dark.svg", 25, 25));
//                FlatMacDarkLaf.setup();
//                menuButton.setBackground(new Color(90, 90, 90));
//                modeButton.setBackground(new Color(90, 90, 90));
//                FlatLaf.updateUI();
//                FlatAnimatedLafChange.hideSnapshotWithAnimation();
//                panel1.setBackground(new Color(46, 47, 48));
//                panel1.repaint();
//            } else {
//                FlatAnimatedLafChange.showSnapshot();
//                FlatMacLightLaf.setup();
//                menuButton.setIcon(new FlatSVGIcon("icons/menuLight.svg", 25, 25));
//                menuButton.setBackground(color);
//                modeButton.setIcon(new FlatSVGIcon("icons/light.svg", 25, 25));
//                modeButton.setBackground(color);
//                FlatLaf.updateUI();
//                FlatAnimatedLafChange.hideSnapshotWithAnimation();
//                panel1.setBackground(new Color(235, 245, 255));
//                panel1.repaint();
//            }
//        }
//        if (e.getSource() == addWord) {
//            System.out.println("Add Poem Button Clicked");
//        }
//        if (e.getSource() == submitAddWord)
//        {
//            System.out.println("Plus Button Clicked");
//        }
//        if (e.getSource() == viewPoemButton)
//        {
//            System.out.println("View Poem Button Clicked");
//        }
//        if (e.getSource() == updatePoemButton)
//        {
//            System.out.println("Update Poem Button Clicked");
//        }
//        if (e.getSource() == deletePoemButton)
//        {
//            System.out.println("Delete Poem Button Clicked");
//        }
//        if (e.getSource() == addVersesButton)
//        {
//            System.out.println("Add Verses Button Clicked");
//        }
//        if (e.getSource() == assignRootButton)
//        {
//            System.out.println("Assign Root Button Clicked");
//        }
//        if (e.getSource() == tokenizeButton)
//        {
//            System.out.println("Tokenize Button Clicked");
//        }
//        if (e.getSource() == bookCreateButton)
//        {
//            System.out.println("Book Create Button Clicked");
//        }
//        if (e.getSource() == bookUpdateButton)
//        {
//            System.out.println("Book Update Button Clicked");
//        }
//        if (e.getSource() == bookDeleteButton)
//        {
//            System.out.println("Book Delete Button Clicked");
//        }
//        if (e.getSource() == viewBookButton)
//        {
//            System.out.println("View Book Button Clicked");
//        }
//        if (e.getSource() == saveButton)
//        {
//            System.out.println("Save Button Clicked");
//        }
//        if (e.getSource() == updateButton)
//        {
//            System.out.println("Update Button Clicked");
//        }
//        if (e.getSource() == deleteButton)
//        {
//            System.out.println("Delete Button Clicked");
//        }
//        if (e.getSource() == verifyButton)
//        {
//            System.out.println("Verify Button Clicked");
//        }
//        if (e.getSource() == verifyAllButton)
//        {
//            System.out.println("Verify All Button Clicked");
//        }
//        if (e.getSource() == searchButton)
//        {
//            System.out.println("Search Button Clicked");
//        }
//        if (e.getSource() == assignTagButton)
//        {
//            System.out.println("Assign Tag Button Clicked");
//        }
//    }
//
//    public void menuActions(int menu, int subMenu) {
//        if (menu == 0 && subMenu == 1) {
//            misraHeight = 260;
//            plusButtonHeight = 256;
//
//            panel2.removeAll();
////sample things for ComboBox
//            booksComboBox = new JComboBox(new Object[]{"Book 1", "Book 2", "Book 3"});
//            booksComboBox.setBounds(300, 36, 250, 30);
//
//            moreDetailsLabel.setBounds(100, 112, 150, 36);
//            persianMeaningTextField.setText("");
//            persianMeaningTextField.setBounds(300, 116, 250, 30);
//
//            urduMeaningTextField.setText("");
//            urduMeaningTextField.setBounds(600, 260, 250, 30);
//
//            posTextField.setText("");
//            posTextField.setBounds(300, 260, 250, 30);
//
//            submitAddWord.setBounds(100, 256, 120, 36);
//
//            misra1List = new ArrayList<>();
//            misra2List = new ArrayList<>();
//
//            panel2.add(addWordLabel);
//            panel2.add(booksComboBox);
//            panel2.add(moreDetailsLabel);
//            panel2.add(persianMeaningTextField);
//            panel2.add(addWord);
//            panel2.add(persianMeaningLabel);
//            panel2.add(urduMeaningLabel);
//            panel2.add(urduMeaningTextField);
//            panel2.add(posTextField);
//            panel2.add(submitAddWord);
//
//            panel2.repaint();
//        }
//        if (menu == 0 && subMenu == 2) {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 0 && subMenu == 3)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("Add Verses Button Clicked"));
//        }
//        if (menu == 0 && subMenu == 4)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("Assign Root Button Clicked"));
//        }
//        if (menu == 1)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 2)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 3 && subMenu == 1)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 3 && subMenu == 2)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 3 && subMenu == 3)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 3 && subMenu == 4)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 4)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 5 && subMenu == 1)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 5 && subMenu == 2)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 5 && subMenu == 3)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 5 && subMenu == 4)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 6)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 7)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 8)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 9)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 10)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//        if (menu == 11)
//        {
//            panel2.removeAll();
//            //sample label to show that it is clicked
//            panel2.add(new JLabel("View Poem Button Clicked"));
//        }
//    }
//
//    private void populateRootComboBox()
//    {
//        //sample data to poplate
//        rootComboBox = new JComboBox(new Object[]{"Root 1", "Root 2", "Root 3"});
//        rootComboBox.setBounds(300, 36, 250, 30);
//
//    }
//}
