package nu.hjemme.web.interceptor;

import nu.hjemme.web.menu.Menu;
import nu.hjemme.web.menu.MenuFacade;
import nu.hjemme.web.menu.MenuItem;
import nu.hjemme.web.menu.MenuItemTarget;
import nu.hjemme.web.menu.MenuTarget;
import nu.hjemme.web.menu.MenuTargetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static nu.hjemme.web.interceptor.InterceptorValues.ATTRIBUTE_MAIN_ITEMS;
import static nu.hjemme.web.interceptor.InterceptorValues.ATTRIBUTE_PERSON_ITEMS;
import static nu.hjemme.web.interceptor.InterceptorValues.MAIN_MENU;
import static nu.hjemme.web.interceptor.InterceptorValues.PERSON_MENU;

/** The {@link MenuInterceptor} is a {@link HandlerInterceptorAdapter}  which put the menus on the model. */
@Component
class MenuInterceptor extends HandlerInterceptorAdapter {

    private MenuFacade menuFacade;

    @Override public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) {
        MenuItemTarget menuItemTarget = new MenuItemTarget(request);
        MenuTargetRequest mainMenuTargetRequest = new MenuTargetRequest(new MenuTarget(menuItemTarget, MAIN_MENU));
        MenuTargetRequest personMenuTargetRequest = new MenuTargetRequest(new MenuTarget(menuItemTarget, PERSON_MENU));

        Menu mainMenu = new Menu(MAIN_MENU, menuFacade.fetchMenuItemBy(mainMenuTargetRequest));
        Menu personMenu = new Menu(PERSON_MENU, menuFacade.fetchMenuItemBy(personMenuTargetRequest));

        List<MenuItem> menuItemsFromMainMenu = mainMenu.getMenuItems();
        List<MenuItem> menuItemsFromPersonMenu = personMenu.getMenuItems();

        addMenuItemsToModelAndView(modelAndView, menuItemsFromMainMenu, menuItemsFromPersonMenu);
    }

    private void addMenuItemsToModelAndView(
            ModelAndView modelAndView,
            List<MenuItem> menuItemsFromMainMenu,
            List<MenuItem> menuItemsFromPersonMenu
    ) {
        Map<String, Object> modelMap = modelAndView.getModel();
        modelMap.put(ATTRIBUTE_MAIN_ITEMS, menuItemsFromMainMenu);
        modelMap.put(ATTRIBUTE_PERSON_ITEMS, menuItemsFromPersonMenu);
    }

    @Autowired public void setMenuFacade(MenuFacade menuFacade) {
        this.menuFacade = menuFacade;
    }
}
