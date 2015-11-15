package nu.hjemme.web.interceptor;

import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.service.MenuFacade;
import nu.hjemme.web.dto.MenuDto;
import nu.hjemme.web.dto.MenuItemDto;
import nu.hjemme.web.dto.MenuItemTargetDto;
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
public class MenuInterceptor extends HandlerInterceptorAdapter {

    private MenuFacade menuFacade;

    @Override public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) {
        MenuItemTargetDto menuItemTargetDto = new MenuItemTargetDto(request);
        MenuTarget mainMenuTarget = new MenuTarget(menuItemTargetDto, MAIN_MENU);
        MenuTarget personMenuTarget = new MenuTarget(menuItemTargetDto, PERSON_MENU);

        MenuDto mainMenuDto = new MenuDto(menuFacade.fetchMenuItemBy(mainMenuTarget));
        MenuDto personMenuDto = new MenuDto(menuFacade.fetchMenuItemBy(personMenuTarget));

        List<MenuItemDto> menuItemsFromMainMenu = mainMenuDto.getMenuItems();
        List<MenuItemDto> menuItemsFromPersonMenu = personMenuDto.getMenuItems();

        addMenuItemsToModelAndView(modelAndView, menuItemsFromMainMenu, menuItemsFromPersonMenu);
    }

    private void addMenuItemsToModelAndView(
            ModelAndView modelAndView,
            List<MenuItemDto> menuItemsFromMainMenu,
            List<MenuItemDto> menuItemsFromPersonMenu
    ) {
        Map<String, Object> modelMap = modelAndView.getModel();
        modelMap.put(ATTRIBUTE_MAIN_ITEMS, menuItemsFromMainMenu);
        modelMap.put(ATTRIBUTE_PERSON_ITEMS, menuItemsFromPersonMenu);
    }

    @Autowired public void setMenuFacade(MenuFacade menuFacade) {
        this.menuFacade = menuFacade;
    }
}
