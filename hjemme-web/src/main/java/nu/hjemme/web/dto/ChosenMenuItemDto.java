package nu.hjemme.web.dto;

import nu.hjemme.client.domain.menu.ChosenMenuItem;

import java.util.ArrayList;
import java.util.List;

/** @author Tor Egil Jacobsen */
public class ChosenMenuItemDto {
    private final ChosenMenuItem chosenMenuItem;
    private final List<ChosenMenuItemDto> chosenChildren = new ArrayList<ChosenMenuItemDto>();

    public ChosenMenuItemDto(ChosenMenuItem chosenMenuItem) {
        this.chosenMenuItem = chosenMenuItem;

        for (ChosenMenuItem chosenChild : chosenMenuItem.getChildren()) {
            chosenChildren.add(new ChosenMenuItemDto(chosenChild));
        }
    }

    public boolean isChosen() {
        return chosenMenuItem.isChosen();
    }

    public boolean isChildChosen() {
        return chosenMenuItem.isChildChosen();
    }

    public List<ChosenMenuItemDto> getChosenChildren() {
        return chosenChildren;
    }
}
