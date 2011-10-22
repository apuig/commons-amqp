package com.abiquo.commons.amqp.serialization;

public interface Serializer<T>
{
    public byte[] toByteArray(T object);

    public T fromByteArray(byte[] bytes);
}
