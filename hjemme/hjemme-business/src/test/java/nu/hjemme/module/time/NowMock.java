package nu.hjemme.module.time;

import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/** @author Tor Egil Jacobsen */
public class NowMock extends Now {
    public NowMock() {
        Now mockedNow = mock(Now.class);

        LocalDateTime localDateTimeAsPureDate = new LocalDateTime(new LocalTime());
        when(mockedNow.retrieveTheDateTime()).thenReturn(localDateTimeAsPureDate);

        setInstance(mockedNow);
    }

    public void cleanUpMocking() {
        setInstance(new Now());
    }
}
