package nu.hjemme.web.dto;

import nu.hjemme.client.domain.ChosenMenuItem;

import java.util.ArrayList;
import java.util.List;

/** @author Tor Egil Jacobsen */
public class MenuDto {
    private List<ChosenMenuItemDto> chosenMenuItemDtos = new ArrayList<ChosenMenuItemDto>();

    public MenuDto(List<ChosenMenuItem> chosenMenuItems) {
        for (ChosenMenuItem chosenMenuItem : chosenMenuItems) {
            chosenMenuItemDtos.add(new ChosenMenuItemDto(chosenMenuItem));
        }
    }

    public List<ChosenMenuItemDto> getChosenMenuItems() {
        return chosenMenuItemDtos;
    }
}
