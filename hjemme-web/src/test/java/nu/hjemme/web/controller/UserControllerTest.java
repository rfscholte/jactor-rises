package nu.hjemme.web.controller;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import nu.hjemme.client.facade.UserFacade;
import nu.hjemme.web.dto.UserDto;
import nu.hjemme.web.html.ParameterConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock private UserFacade mockedUserFacade;

    private UserController testUserController;

    @Before public void setUpUserController() {
        testUserController = new UserController();
        testUserController.setUserFacade(mockedUserFacade);
    }

    @Test public void shouldNotFetchUserByUserNameIfTheUserNameInTheWebRequestIsNullOrAnEmptyString() {
        Map<String, String[]> params = new HashMap<>();

        WebRequest mockedWebRequest = mock(WebRequest.class);
        when(mockedWebRequest.getParameterMap()).thenReturn(params);

        testUserController.doUser(mock(ModelMap.class), mockedWebRequest);

        verify(mockedUserFacade, atMost(0)).findUsing(any(UserName.class));

        String[] value = {" \n\t "};
        params.put("choose", value);

        testUserController.doUser(mock(ModelMap.class), mockedWebRequest);

        verify(mockedUserFacade, atMost(0)).findUsing(any(UserName.class));
    }

    @Test public void shouldFetchTheUserIfChooseParameterExist() {
        WebRequest mockedWebRequest = mock(WebRequest.class);
        ModelMap mockedModelMap = mock(ModelMap.class);
        User mockedUser = mock(User.class);

        when(mockedWebRequest.getParameter(ParameterConstants.CHOOSE_USER)).thenReturn("user");
        when(mockedUserFacade.findUsing(new UserName("user"))).thenReturn(Optional.of(mockedUser));

        testUserController.doUser(mockedModelMap, mockedWebRequest);

        verify(mockedModelMap, atLeastOnce()).put(eq(ControllerValues.ATTRIBUTE_USER), any(UserDto.class));
    }

    @Test public void shouldNotPutTheUserOnTheModelIfNotFound() {
        WebRequest mockedWebRequest = mock(WebRequest.class);
        ModelMap mockedModelMap = mock(ModelMap.class);

        String[] value = {"user"};
        Map<String, String[]> params = new HashMap<>();
        params.put("choose", value);

        when(mockedWebRequest.getParameterMap()).thenReturn(params);
        when(mockedUserFacade.findUsing(new UserName("user"))).thenReturn(null);

        testUserController.doUser(mockedModelMap, mockedWebRequest);

        verify(mockedModelMap, atMost(0)).put(eq(ControllerValues.ATTRIBUTE_USER), any(UserDto.class));
    }
}
