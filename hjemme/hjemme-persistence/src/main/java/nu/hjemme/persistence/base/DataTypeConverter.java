package nu.hjemme.persistence.base;

public interface DataTypeConverter<DataType> {
    <PersistentType> DataType convertTo(PersistentType toConvertFrom);
    <PersistentType> PersistentType convertFrom(DataType dataValue);
}
