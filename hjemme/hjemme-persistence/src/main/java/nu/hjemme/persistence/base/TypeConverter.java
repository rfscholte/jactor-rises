package nu.hjemme.persistence.base;

public interface TypeConverter<To, From> {
    To convertTo(From from);

    From convertFrom(To to);
}
