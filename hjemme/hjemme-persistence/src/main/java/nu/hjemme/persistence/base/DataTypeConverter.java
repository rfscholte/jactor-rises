package nu.hjemme.persistence.base;

public interface DataTypeConverter<DataType> {
    <PersistentType> DataType convert(PersistentType toConvertFrom);
}
