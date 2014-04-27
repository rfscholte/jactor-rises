package nu.hjemme.module.persistence;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import nu.hjemme.module.persistence.mutable.MutableProfile;
import nu.hjemme.module.persistence.mutable.MutableUser;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static java.util.Objects.hash;
import static nu.hjemme.module.persistence.meta.UserMetadata.PASSWORD;
import static nu.hjemme.module.persistence.meta.UserMetadata.USER_ID;
import static nu.hjemme.module.persistence.meta.UserMetadata.USER_NAME;

/** @author Tor Egil Jacobsen */
public class UserEntity extends PersistentBean implements MutableUser {

    @Id
    @Column(name = USER_ID)
    void setUserId(Long userId) {
        setId(userId);
    }

    @Column(name = PASSWORD)
    private String password;

    @Column(name = USER_NAME)
    // Add persistence type
    private UserName userName;

    @OneToOne(mappedBy = "profileEntity")
    private ProfileEntity profileEntity;

    public UserEntity() {
    }

    /** @param user is used to create an entity */
    public UserEntity(User user) {
        password = user.getPassword();
        userName = user.getUserName();
        profileEntity = user.getProfile() != null ? new ProfileEntity(user.getProfile()) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserEntity userEntity = (UserEntity) o;

        return new EqualsBuilder()
                .append(getId(), userEntity.getId())
                .append(getPassword(), userEntity.getPassword())
                .append(getProfile(), userEntity.getProfile())
                .append(getUserName(), userEntity.getUserName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return hash(getPassword(), getProfile(), getUserName());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getUserName())
                .append(getProfile())
                .toString();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public UserName getUserName() {
        return userName;
    }

    @Override
    public ProfileEntity getProfile() {
        return profileEntity;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    @Override
    public <T extends MutableProfile> void setProfileEntity(T mutableProfile) {
        this.profileEntity = (ProfileEntity) mutableProfile;
    }

    public MutableUser getMutableUser() {
        return profileEntity.getMutableUser();
    }
}
