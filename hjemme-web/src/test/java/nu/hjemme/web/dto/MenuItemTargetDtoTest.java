package nu.hjemme.web.dto;

import nu.hjemme.client.datatype.MenuItemTarget;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuItemTargetDtoTest {

    @Test public void willRemoveContextRootFromRequestedUri() {
        HttpServletRequest mockedHttpServletRequest = mock(HttpServletRequest.class);
        when(mockedHttpServletRequest.getRequestURI()).thenReturn("/hjemme/action");

        MenuItemTarget testMenuItemTarget = new MenuItemTargetDto(mockedHttpServletRequest);

        assertThat("Target", testMenuItemTarget.getTarget(), is(equalTo("action")));
    }
}
