package nu.hjemme.module.persistence;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import nu.hjemme.client.domain.GuestBook;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * A users guest book.
 * @author Tor Egil Jacobsen
 */
@Table(name = "GUEST_BOOK")
public class GuestBookDto extends DtoPersistent {

    private static final long serialVersionUID = 389262250015860762L;

    @Column(name = "HEADER")
    private String header;

    @OneToOne
    @Column(name = "USER_ID")
    private UserDto userDto;

    public GuestBookDto() {}

    public GuestBookDto(GuestBookDto template) {
        super(template);
        header = template.header;
        userDto = template.userDto;
    }

    public GuestBookDto(GuestBook template) {
        super(template);
        header = template.getHeader();
        userDto = new UserDto(template.getUser());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(header).append(userDto).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (! (obj instanceof GuestBookDto )) {
            return false;
        }

        GuestBookDto guestBookDomain = (GuestBookDto) obj;

        return new EqualsBuilder().append(header, guestBookDomain.header).append(userDto, guestBookDomain.userDto).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("header", header).append("userDto", userDto).toString();
    }

    @Override
    public GuestBook getDomain() {
        GuestBook guestBook = newInstance(GuestBook.class);
        guestBook.setHeader(header);

        if (userDto != null) {
            guestBook.setUser(userDto.getDomain());
        }

        return guestBook;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public static Builder opprettBuilder() {
        return new Builder();
    }

    public static class Builder {
        private GuestBookDto guestBookDto;

        Builder() {
            guestBookDto = new GuestBookDto();
        }

        public Builder header(String header) {
            guestBookDto.header = header;
            return this;
        }

        public Builder user(UserDto userDto) {
            guestBookDto.userDto = userDto;
            return this;
        }

        public GuestBookDto build() {
            return guestBookDto;
        }
    }
}
