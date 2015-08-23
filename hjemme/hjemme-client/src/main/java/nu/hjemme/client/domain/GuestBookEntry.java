package nu.hjemme.client.domain;

public interface GuestBookEntry extends Persistent<Long> {

    GuestBook getGuestBook();

    Entry getEntry();
}

