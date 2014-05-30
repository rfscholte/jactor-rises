package nu.hjemme.business.time;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/** @author Tor Egil Jacobsen */
// TODO #8: Flytt til hjemme-test
public class NowMock extends Now {
    public NowMock() {
        Now mockedNow = mock(Now.class);

        LocalDateTime localDateTimeAsPureDate = LocalDateTime.now(); // TODO #8: dato
        when(mockedNow.retrieveTheDateTime()).thenReturn(localDateTimeAsPureDate);

        setInstance(mockedNow);
    }

    public void cleanUpMocking() {
        setInstance(new Now());
    }
}
