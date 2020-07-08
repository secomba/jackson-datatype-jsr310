package com.fasterxml.jackson.datatype.jsr310.ser;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class OffsetDateTimeSerializer extends InstantSerializerBase<OffsetDateTime>
{
    private static final long serialVersionUID = 1L;

    public static final OffsetDateTimeSerializer INSTANCE = new OffsetDateTimeSerializer();

    protected OffsetDateTimeSerializer() {
        super(OffsetDateTime.class, new ToLongFunction<OffsetDateTime>() {
                    @Override
                    public long applyAsLong(OffsetDateTime dt) {
                        return dt.toInstant().toEpochMilli();
                    }
                },
                new ToLongFunction<OffsetDateTime>() {
                    @Override
                    public long applyAsLong(OffsetDateTime offsetDateTime) {
                        return offsetDateTime.toEpochSecond();
                    }
                }, new ToIntFunction<OffsetDateTime>() {
                    @Override
                    public int applyAsInt(OffsetDateTime offsetDateTime) {
                        return offsetDateTime.getNano();
                    }
                },
                DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    protected OffsetDateTimeSerializer(OffsetDateTimeSerializer base,
            Boolean useTimestamp, DateTimeFormatter formatter) {
        super(base, useTimestamp, formatter);
    }

    @Override
    protected JSR310FormattedSerializerBase<?> withFormat(Boolean useTimestamp, DateTimeFormatter formatter) {
        return new OffsetDateTimeSerializer(this, useTimestamp, formatter);
    }
}
