package nu.hjemme.web.interceptor;

import nu.hjemme.client.MenuFacade;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.web.dto.ChosenMenuItemDto;
import nu.hjemme.web.dto.MenuDto;
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
import static nu.hjemme.web.interceptor.InterceptorValues.ATTRIBUTE_PROFILE_ITEMS;
import static nu.hjemme.web.interceptor.InterceptorValues.MAIN_MENU;
import static nu.hjemme.web.interceptor.InterceptorValues.PROFILE_MENU;

/**
 * The {@link MenuInterceptor} is a {@link HandlerInterceptorAdapter}  which put the menus on the model.
 * @author Tor Egil Jacobsen
 */
@Component
public class MenuInterceptor extends HandlerInterceptorAdapter {

    private MenuFacade menuFacade;

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) {
        MenuItemTargetDto menuItemTargetDto = new MenuItemTargetDto(request);
        MenuTarget mainMenuTarget = new MenuTarget(menuItemTargetDto, MAIN_MENU);
        MenuTarget profileMenuTarget = new MenuTarget(menuItemTargetDto, PROFILE_MENU);

        MenuDto mainMenuDto = new MenuDto(menuFacade.retrieveChosenMenuItemBy(mainMenuTarget));
        MenuDto profileMenuDto = new MenuDto(menuFacade.retrieveChosenMenuItemBy(profileMenuTarget));

        List<ChosenMenuItemDto> chosenMenuItemsFromMainMenu = mainMenuDto.getChosenMenuItems();
        List<ChosenMenuItemDto> chosenMenuItemsFromProfileMenu = profileMenuDto.getChosenMenuItems();

        addMenuItemsToModelAndView(modelAndView, chosenMenuItemsFromMainMenu, chosenMenuItemsFromProfileMenu);
    }

    private void addMenuItemsToModelAndView(
            ModelAndView modelAndView,
            List<ChosenMenuItemDto> chosenMenuItemsFromMainMenu,
            List<ChosenMenuItemDto> chosenMenuItemsFromProfileMenu
    ) {
        Map<String, Object> modelMap = modelAndView.getModel();
        modelMap.put(ATTRIBUTE_MAIN_ITEMS, chosenMenuItemsFromMainMenu);
        modelMap.put(ATTRIBUTE_PROFILE_ITEMS, chosenMenuItemsFromProfileMenu);
    }

    @Autowired
    public void setMenuFacade(MenuFacade menuFacade) {
        this.menuFacade = menuFacade;
    }
}
