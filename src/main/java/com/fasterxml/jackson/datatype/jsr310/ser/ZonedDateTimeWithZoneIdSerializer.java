package com.fasterxml.jackson.datatype.jsr310.ser;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

// TODO deprecate this: SerializationFeature config should be respected,
// default behaviour should be to serialize according to ISO-8601 format
/**
 * @since 2.6
 *
 * @deprecated Since 2.8 only used by deprecated {@link com.fasterxml.jackson.datatype.jsr310.JSR310Module}
 */
@Deprecated
public class ZonedDateTimeWithZoneIdSerializer extends InstantSerializerBase<ZonedDateTime>
{
    private static final long serialVersionUID = 1L;

    public static final ZonedDateTimeWithZoneIdSerializer INSTANCE = new ZonedDateTimeWithZoneIdSerializer();

    protected ZonedDateTimeWithZoneIdSerializer() {
        super(ZonedDateTime.class, new ToLongFunction<ZonedDateTime>() {
                    @Override
                    public long applyAsLong(ZonedDateTime dt) {
                        return dt.toInstant().toEpochMilli();
                    }
                },
                new ToLongFunction<ZonedDateTime>() {
                    @Override
                    public long applyAsLong(ZonedDateTime zonedDateTime) {
                        return zonedDateTime.toEpochSecond();
                    }
                }, new ToIntFunction<ZonedDateTime>() {
                    @Override
                    public int applyAsInt(ZonedDateTime zonedDateTime) {
                        return zonedDateTime.getNano();
                    }
                },
                // Serialize in a backwards compatible way: with zone id, using toString method
                null);
    }

    protected ZonedDateTimeWithZoneIdSerializer(ZonedDateTimeWithZoneIdSerializer base,
            Boolean useTimestamp, DateTimeFormatter formatter) {
        super(base, useTimestamp, formatter);
    }

    @Override
    protected JSR310FormattedSerializerBase<?> withFormat(Boolean useTimestamp, DateTimeFormatter formatter) {
        return new ZonedDateTimeWithZoneIdSerializer(this, useTimestamp, formatter);
    }

}
