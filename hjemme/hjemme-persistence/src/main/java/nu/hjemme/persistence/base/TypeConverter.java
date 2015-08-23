package nu.hjemme.persistence.base;

public interface TypeConverter<To, From> {
    To convert(From from);
}
