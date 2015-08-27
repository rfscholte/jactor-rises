package nu.hjemme.web.dto;

import nu.hjemme.client.domain.menu.ChosenMenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuDto {
    private List<ChosenMenuItemDto> chosenMenuItems = new ArrayList<>();

    public MenuDto(List<ChosenMenuItem> chosenMenuItems) {
        for (ChosenMenuItem chosenMenuItem : chosenMenuItems) {
            this.chosenMenuItems.add(new ChosenMenuItemDto(chosenMenuItem));
        }
    }

    public List<ChosenMenuItemDto> getChosenMenuItems() {
        return chosenMenuItems;
    }
}
