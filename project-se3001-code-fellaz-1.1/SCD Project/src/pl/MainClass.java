package pl;

import com.formdev.flatlaf.themes.*;
import javax.swing.*;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.*;

public class MainClass extends SimpleDrawerBuilder {

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        return new SimpleHeaderData()
                .setTitle("APMS")
                .setDescription("Arabic Poems Management System");
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {
        String[][] menu = {
            {"~Poem~"},
            {"Poem CRUD", "Add Poem", "Import Poem", "Update Poem", "Delete Poem"},
            {"View Single Poem"},
            {"Add New Verses To Existing Poem"},
            {"~Book~"},
            {"Book CRUD", "Create Book", "Read Book", "Update Book", "Delete Book"},
            {"View Single Book"},
            {"~Root~"},
            {"Root CRUD", "Add Root", "Read Root", "Update Root", "Delete Root"},
            {"Verify and Update Root"},
            {"View All Roots"},
            {"View Single Root"},
            {"~Tag~"},
            {"Assign Tags"},
            {"View Tags"},
            {"~Tokenization~"},
            {"Assign Tokens"}
        };
        String[] icons = {
            "poem.svg", "poem.svg", "poem.svg", "book.svg", "book.svg", "root.svg", "root.svg", "root.svg", "root.svg", "tag.svg", "tag.svg", "token.svg"
        };
        return new SimpleMenuOption()
                .setMenus(menu)
                .setIcons(icons)
                .setBaseIconPath("pl/icons")
                .setIconScale(0.45f)
                .addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction action, int menu, int subMenu) {
                        PresentationLayer.getInstance().menuActions(menu, subMenu);
                    }
                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setTitle("Made With ❤ By Code-Fellaz.")
                .setDescription("Version 1.1");
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        UIManager.put("TitlePane.unifiedBackground", false);
        UIManager.put("TextComponent.arc", 36);
        UIManager.put("Button.arc", 36);
        UIManager.put("Component.arc", 36);

        PresentationLayer.getInstance();
    }
}
