package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import nu.hjemme.persistence.ProfileEntity;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.base.DefaultPersistentEntity;
import nu.hjemme.persistence.meta.UserMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

import static java.util.Objects.hash;

@Entity
@Table(name = UserMetadata.USER_TABLE)
public class DefaultUserEntity extends DefaultPersistentEntity implements UserEntity {

    @Column(name = UserMetadata.PASSWORD, nullable = false) private String password; // the user password
    @Column(name = UserMetadata.USER_NAME, nullable = false) private String userName; // the user name
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) @JoinColumn(name = UserMetadata.PROFILE_ID) private DefaultProfileEntity profileEntity; // the profile to the user
    @Column(name = UserMetadata.EMAIL) private String emailAddress; // the email address to the user
    @Column(name = UserMetadata.EMAIL_AS_NAME, nullable = false) private boolean userNameIsEmailAddress; // if the user uses the email address as the user name

    public DefaultUserEntity() { }

    /** @param user is used to create an entity */
    public DefaultUserEntity(User user) {
        password = user.getPassword();
        userName = user.getUserName() != null ? user.getUserName().getName() : null;
        profileEntity = user.getProfile() != null ? new DefaultProfileEntity(user.getProfile()) : null;
    }

    @Override public boolean equals(Object o) {
        return o == this || o != null && getClass() == o.getClass() &&
                Objects.equals(getPassword(), ((UserEntity) o).getPassword()) &&
                Objects.equals(getProfile(), ((UserEntity) o).getProfile()) &&
                Objects.equals(getUserName(), ((UserEntity) o).getUserName());
    }

    @Override public int hashCode() {
        return hash(password, null /*profileEntity*/, userName);
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getUserName())
                .append(getProfile())
                .toString();
    }

    private DefaultProfileEntity cast(ProfileEntity profileEntity) {
        if (profileEntity instanceof DefaultProfileEntity) {
            return (DefaultProfileEntity) profileEntity;
        }

        throw new IllegalArgumentException("unknown entity: " + profileEntity.getClass());
    }

    @Override public String getPassword() {
        return password;
    }

    @Override public UserName getUserName() {
        return convertTo(userName, UserName.class);
    }

    @Override public ProfileEntity getProfile() {
        return profileEntity;
    }

    @Override public EmailAddress getEmailAddress() {
        return convertTo(emailAddress, EmailAddress.class);
    }

    @Override public boolean isUserNameEmailAddress() {
        return userNameIsEmailAddress;
    }

    @Override public void setPassword(String password) {
        this.password = password;
    }

    @Override public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override public void setUserNameAsEmailAddress() {
        userNameIsEmailAddress = true;
    }

    @Override public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override public void setProfileEntity(ProfileEntity profileEntity) {
        this.profileEntity = cast(profileEntity);
    }
}
