package nu.hjemme.web.dto;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.Address;
import nu.hjemme.client.domain.Profile;
import nu.hjemme.client.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
/** @author Tor Egil Jacobsen */
public class UserDtoTest {
    @Mock
    Address mockedAddress;

    @Mock
    User mockedUser;

    @Mock
    Profile mockedProfile;

    @Test
    public void willGetTheAddress() {
        mockUserInstance();
        UserDto testUserDto = new UserDto(mockedUser);

        assertThat("Address line 1", testUserDto.getAddressLine1(), is(equalTo("address line 1")));
        assertThat("Address line 2", testUserDto.getAddressLine2(), is(equalTo("address line 2")));
        assertThat("City", testUserDto.getCity(), is(equalTo("somewhere")));
        assertThat("Zip code", testUserDto.getZipCode(), is(equalTo(1234)));
    }

    @Test
    public void willGetTheUser() {
        mockUserInstance();
        UserDto testUserDto = new UserDto(mockedUser);

        assertThat("User name", testUserDto.getUserName(), is(equalTo("user")));
    }

    @Test
    public void willGetTheProfile() {
        mockUserInstance();
        UserDto testUserDto = new UserDto(mockedUser);

        assertThat("First name", testUserDto.getFirstName(), is(equalTo("John")));
        assertThat("Last name", testUserDto.getLastName(), is(equalTo("Smith")));
        assertThat("Description", testUserDto.getDescription(), is(equalTo("description")));
    }

    private void mockUserInstance() {
        when(mockedUser.getUserName()).thenReturn(new UserName("user"));
        when(mockedUser.getProfile()).thenReturn(mockedProfile);
        when(mockedProfile.getAddress()).thenReturn(mockedAddress);
        when(mockedProfile.getDescription()).thenReturn("description");
        when(mockedProfile.getFirstName()).thenReturn(new Name("John"));
        when(mockedProfile.getLastName()).thenReturn(new Name("Smith"));
        when(mockedAddress.getAddressLine1()).thenReturn("address line 1");
        when(mockedAddress.getAddressLine2()).thenReturn("address line 2");
        when(mockedAddress.getCity()).thenReturn("somewhere");
        when(mockedAddress.getZipCode()).thenReturn(1234);
    }
}
