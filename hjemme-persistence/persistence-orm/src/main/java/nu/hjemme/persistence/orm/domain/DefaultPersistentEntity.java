package nu.hjemme.persistence.orm.domain;

import nu.hjemme.client.datatype.Country;
import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.Persistent;
import nu.hjemme.persistence.client.converter.CountryConverter;
import nu.hjemme.persistence.client.converter.DescriptionConverter;
import nu.hjemme.persistence.client.converter.EmailAddressConverter;
import nu.hjemme.persistence.client.converter.LocalDateConverter;
import nu.hjemme.persistence.client.converter.LocalDateTimeConverter;
import nu.hjemme.persistence.client.converter.NameConverter;
import nu.hjemme.persistence.client.converter.TypeConverter;
import nu.hjemme.persistence.client.converter.UserNameConverter;
import nu.hjemme.persistence.orm.meta.PersistentMetadata;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
public abstract class DefaultPersistentEntity implements Persistent<Long> {

    private static Map<Class<?>, TypeConverter> dataTypeConverters = initKnownConverters();

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = PersistentMetadata.ID) Long id;

    @Column(name = PersistentMetadata.CREATION_TIME) @Type(type = "timestamp") private Date creationTime;
    @Column(name = PersistentMetadata.CREATED_BY) private String createdBy;
    @Column(name = PersistentMetadata.UPDATED_TIME) @Type(type = "timestamp") private Date updatedTime;
    @Column(name = PersistentMetadata.UPDATED_BY) private String updatedBy;

    public DefaultPersistentEntity() {
        createdBy = "todo";
        creationTime = new Date();
        updatedBy = "todo";
        updatedTime = new Date();
    }

    @SuppressWarnings("unchecked") public <To, From> To convertTo(From from, Class<To> classType) {
        if (isValidValue(classType)) {
            return (To) dataTypeConverters.get(classType).convertTo(from);
        }

        throw new IllegalArgumentException(classType + " is not a type known for any converter!");
    }

    @SuppressWarnings("unchecked") protected <To, From> From convertFrom(To to, Class<To> classType) {
        if (isValidValue(classType)) {
            return (From) dataTypeConverters.get(classType).convertFrom(to);
        }

        throw new IllegalArgumentException(classType + " is not a type known for any converter!");
    }

    private <DataType> boolean isValidValue(Class<DataType> classType) {
        return !(classType != null && !dataTypeConverters.containsKey(classType));
    }

    @SuppressWarnings("unchecked") public <Implementation extends Obj, Obj> Implementation castOrInitializeCopyWith(Obj object, Class<Implementation> implementation) {
        if (object == null) {
            return null;
        }

        if (implementation.isAssignableFrom(object.getClass())) {
            return (Implementation) object;
        }

        if (!implementation.isAssignableFrom(object.getClass())) {
            throw new IllegalArgumentException(createErrorMessageUsing(object, implementation));
        }

        return initializeCopyWith(object, implementation);
    }

    @SuppressWarnings("unchecked") protected <Implementation extends Obj, Obj> Implementation initializeCopyWith(Obj object, Class<Implementation> implementation) {
        if (object != null) {
            for (Constructor<?> constructor : implementation.getConstructors()) {
                if (constructor.getParameterCount() == 1) {
                    try {
                        return (Implementation) constructor.newInstance(object);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new IllegalArgumentException(createErrorMessageUsing(object, implementation), e);
                    }
                }
            }
        }

        return null;
    }

    private String createErrorMessageUsing(Object object, Class<?> implementation) {
        return "Unable to cast or initialize " + object.getClass() + " to " + implementation;
    }

    @Override public String toString() {
        return getClass().getSimpleName() + (id != null ? "#" + id : "");
    }

    @Override public Name getCreatedBy() {
        return convertTo(createdBy, Name.class);
    }

    @Override public LocalDateTime getCreationTime() {
        return convertTo(creationTime, LocalDateTime.class);
    }

    @Override public Long getId() {
        return id;
    }

    @Override public Name getUpdatedBy() {
        return convertTo(updatedBy, Name.class);
    }

    @Override public LocalDateTime getUpdatedTime() {
        return convertTo(updatedTime, LocalDateTime.class);
    }

    private static Map<Class<?>, TypeConverter> initKnownConverters() {
        Map<Class<?>, TypeConverter> knownConverters = new HashMap<>();
        knownConverters.put(Name.class, new NameConverter());
        knownConverters.put(EmailAddress.class, new EmailAddressConverter());
        knownConverters.put(UserName.class, new UserNameConverter());
        knownConverters.put(Description.class, new DescriptionConverter());
        knownConverters.put(LocalDateTime.class, new LocalDateTimeConverter());
        knownConverters.put(Country.class, new CountryConverter());
        knownConverters.put(LocalDate.class, new LocalDateConverter());

        return knownConverters;
    }
}