package com.abiquo.commons.amqp.serialization;

import org.codehaus.jackson.map.ObjectMapper;

public class JSONSerializer<T> implements Serializer<T>
{
    final private Class<T> classType;

    public JSONSerializer(Class<T> classType)
    {
        this.classType = classType;
    }

    @Override
    public byte[] toByteArray(T object)
    {
        return serialize(object);
    }

    @Override
    public T fromByteArray(byte[] bytes)
    {
        return deserialize(bytes, classType);
    }

    /**
     * Serializes the given Object to JSON and returns the result in array of bytes.
     * 
     * @param value The object to serialize.
     * @return A byte array representing the JSON serialization of the object. A null value if the
     *         serialization fails.
     */
    protected byte[] serialize(T value)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            return mapper.writeValueAsBytes(value);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Deserializes the given byte array JSON serialization.
     * 
     * @param bytes A byte array representing the JSON serialization of an object.
     * @param type The Class of the object to deserialize
     * @return The deserialized object or null if the process fails.
     */
    protected <T> T deserialize(byte[] bytes, Class<T> type)
    {
        ObjectMapper mapper = new ObjectMapper();
        String content = new String(bytes);

        try
        {
            return (T) mapper.readValue(content, type);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
