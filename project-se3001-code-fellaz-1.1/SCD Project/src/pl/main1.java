//package org.multilangdict.pl;
//
//import com.formdev.flatlaf.themes.*;
//import javax.swing.*;
//
//import org.multilangdict.bll.BLLFacade;
//import org.multilangdict.bll.IBLLFacade;
//import org.multilangdict.dal.*;
//import org.multilangdict.pl.TESTUI;
//import raven.drawer.component.SimpleDrawerBuilder;
//import raven.drawer.component.footer.SimpleFooterData;
//import raven.drawer.component.header.SimpleHeaderData;
//import raven.drawer.component.menu.MenuAction;
//import raven.drawer.component.menu.*;
//
//public class MainClass extends SimpleDrawerBuilder {
//    private final TESTUI testUI;
//
//    public MainClass(TESTUI testUI) {
//        this.testUI = testUI;
//    }
//
//    @Override
//    public SimpleHeaderData getSimpleHeaderData() {
//        return new SimpleHeaderData()
//                .setTitle("BAL")
//                .setDescription("Bayt Al-Lughah");
//    }
//
//    @Override
//    public SimpleMenuOption getSimpleMenuOption() {
//        String[][] menu = {
//            {"~Dictionary~"},
//            {"Dictionary CRUD", "Add Word", "Delete Word", "Update Word", "Import Dictionary"},
//            {"View Single Poem"},
//            {"Add New Verses To Existing Poem"},
//            {"~Book~"},
//            {"Book CRUD", "Create Book", "Read Book", "Update Book", "Delete Book"},
//            {"View Single Book"},
//            {"~Root~"},
//            {"Root CRUD", "Add Root", "Read Root", "Update Root", "Delete Root"},
//            {"Verify and Update Root"},
//            {"View All Roots"},
//            {"View Single Root"},
//            {"~Tag~"},
//            {"Assign Tags"},
//            {"View Tags"},
//            {"~Tokenization~"},
//            {"Assign Tokens"}
//        };
//        String[] icons = {
//                "dictionary.svg", "dictionary.svg", "dictionary.svg", "dictionary.svg", "dictionary.svg", "dictionary.svg", "dictionary.svg", "dictionary.svg", "dictionary.svg", "dictionary.svg", "dictionary.svg", "dictionary.svg"
//        };
//        return new SimpleMenuOption()
//                .setMenus(menu)
//                .setIcons(icons)
//                .setBaseIconPath("icons/")
//                .setIconScale(0.45f)
//                .addMenuEvent(new MenuEvent() {
//                    @Override
//                    public void selected(MenuAction action, int menu, int subMenu) {
//                        testUI.menuActions(menu, subMenu);
//                    }
//                });
//    }
//
//    @Override
//    public SimpleFooterData getSimpleFooterData() {
//        return new SimpleFooterData()
//                .setTitle("Made With ❤ By Mind Coderz")
//                .setDescription("Version 1.0");
//    }
//
//    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(new FlatMacDarkLaf());
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
//        UIManager.put("TitlePane.unifiedBackground", false);
//        UIManager.put("TextComponent.arc", 36);
//        UIManager.put("Button.arc", 36);
//        UIManager.put("Component.arc", 36);
//
//        // Create all your DAOs
//        IWordSearchDAO wordSearchDAO = AbstractDAOFactory.getInstance().createWordSearchDAO();
//        IWebScrapperDAO webScrapperDAO = AbstractDAOFactory.getInstance().createWebScrapperDAO();
//        IWordCrudDAO wordCrudDAO = AbstractDAOFactory.getInstance().createWordCrudDAO();
//        IWordFavDAO worldFavDAO = AbstractDAOFactory.getInstance().createWorldFavDAO();
//        IHistoryDAO historyDAO = AbstractDAOFactory.getInstance().createHistoryDAO();
//        IWordGrammarDAO wordGrammarDAO = AbstractDAOFactory.getInstance().createWordGrammarDAO();
//
//        // Create facades
//        IDALFacade dalFacade = new DALFacade(webScrapperDAO, wordCrudDAO, worldFavDAO,
//                wordSearchDAO, wordGrammarDAO, historyDAO);
//        IBLLFacade bllFacade = new BLLFacade(dalFacade);
//
//        // Create new TESTUI instance with dependency injection
//        new TESTUI(bllFacade);
//    }
//}
