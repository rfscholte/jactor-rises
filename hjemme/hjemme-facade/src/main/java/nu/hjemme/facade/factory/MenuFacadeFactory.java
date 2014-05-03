package nu.hjemme.facade.factory;

import nu.hjemme.business.facade.MenuFacadeImpl;
import nu.hjemme.client.dto.MenuDto;
import nu.hjemme.client.facade.MenuFacade;

import java.util.List;

/**
 * A factory bean to initiate the {@link MenuFacade} as a singleton using the springframework.
 * @author Tor Egil Jacobsen
 */
public class MenuFacadeFactory extends AbstractFacadeFactory<MenuFacade> {
    private List<MenuDto> menus;

    @Override
    MenuFacade getFacade() {
        return new MenuFacadeImpl(menus);
    }

    @Override
    Class<MenuFacade> getFacadeClass() {
        return MenuFacade.class;
    }

    public void setMenus(List<MenuDto> menus) {
        this.menus = menus;
    }
}
