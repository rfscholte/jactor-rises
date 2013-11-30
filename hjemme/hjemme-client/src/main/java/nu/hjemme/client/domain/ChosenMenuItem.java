package nu.hjemme.client.domain;

import java.util.List;

/**
 * A chosen menu item.
 * @author Tor Egil Jacobsen
 */
public interface ChosenMenuItem extends MenuItem {

    @Override
    List<ChosenMenuItem> getChildren();

    boolean isChosen();

    boolean isChildChosen();

    MenuItem getMenuItem();
}
