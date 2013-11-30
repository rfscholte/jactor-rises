package nu.hjemme.i18n.menu;

import java.util.List;

import nu.hjemme.i18n.AbstractI18nComponentManager;

public class MenuComponentManager extends AbstractI18nComponentManager <MenuMutable> {

    private void validateArgument(List <MenuMutable> componentsToManage) {
        if (componentsToManage == null) {
            throw new IllegalArgumentException("The menus to manage cannot be null!");
        }

        if (componentsToManage.isEmpty()) {
            throw new IllegalArgumentException("The menus to manage cannot be empty!");
        }
    }

    public void setComponentsToManage(List <MenuMutable> componentsToManage) {
        validateArgument(componentsToManage);

        for (MenuMutable menu : componentsToManage) {
            setComponentToManage(menu);
        }
    }
}
