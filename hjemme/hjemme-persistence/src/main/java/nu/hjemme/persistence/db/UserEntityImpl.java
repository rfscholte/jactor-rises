package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import nu.hjemme.persistence.ProfileEntity;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.base.PersistentEntity;
import nu.hjemme.persistence.meta.PersistentMetadata;
import nu.hjemme.persistence.meta.UserMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

import static java.util.Objects.hash;

@Entity
@Table(name = UserMetadata.USER_TABLE)
public class UserEntityImpl extends PersistentEntity<Long> implements UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = PersistentMetadata.ID) @SuppressWarnings("unused") // used by persistence engine
    private Long id;

    @Column(name = UserMetadata.PASSWORD) private String password;
    @Column(name = UserMetadata.USER_NAME) private String userName;
//    @OneToOne(mappedBy = "profileEntity") private ProfileEntity profileEntity;
    @Column(name = UserMetadata.EMAIL) private String emailAddress;

    public UserEntityImpl() { }

    /** @param user is used to create an entity */
    public UserEntityImpl(User user) {
        password = user.getPassword();
        userName = convertFrom(user.getUserName());
//        profileEntity = user.getProfile() != null ? new ProfileEntityImpl(user.getProfile()) : null;
    }

    @Override public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserEntity userEntity = (UserEntity) o;

        return Objects.equals(getPassword(), userEntity.getPassword()) && Objects.equals(getProfile(), userEntity.getProfile()) && Objects.equals(getUserName(), userEntity.getUserName());
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

    @Override public String getPassword() {
        return password;
    }

    @Override public UserName getUserName() {
        return convertTo(userName, UserName.class);
    }

    @Override public ProfileEntity getProfile() {
        return null; //profileEntity;
    }

    @Override public EmailAddress getEmailAddress() {
        return convertTo(emailAddress, EmailAddress.class);
    }

    @Override public void setPassword(String password) {
        this.password = password;
    }

    @Override public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = convertFrom(emailAddress);
    }

    @Override public void setUserName(UserName userName) {
        this.userName = convertFrom(userName);
    }

    @Override public void setProfileEntity(ProfileEntity profileEntity) {
        //this.profileEntity = profileEntity;
    }

    @Override public Long getId() {
        return id;
    }
}
