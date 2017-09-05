/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Original Source: https://raw.githubusercontent.com/google/gson/master/gson/src/main/java/com/google/gson/DefaultDateTypeAdapter.java
package com.corycharlton.bittrexapi.internal.gson;

import android.annotation.SuppressLint;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.corycharlton.bittrexapi.internal.util.ISO8601Utils;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * This type adapter supports three subclasses of date: Date, Timestamp, and
 * java.sql.Date.
 *
 * @author Inderjeet Singh
 * @author Joel Leitch
 */

// Original source: https://raw.githubusercontent.com/google/gson/master/gson/src/main/java/com/google/gson/DefaultDateTypeAdapter.java
@SuppressWarnings({"unused", "WeakerAccess"})
final class DefaultDateTypeAdapter extends TypeAdapter<Date> {

    private static final String TAG = DefaultDateTypeAdapter.class.getSimpleName();

    private final Class<? extends Date> dateType;
    private final DateFormat enUsFormat;
    private final DateFormat localFormat;

    DefaultDateTypeAdapter(Class<? extends Date> dateType) {
        this(dateType,
                DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US),
                DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT));
    }

    @SuppressLint("SimpleDateFormat")
    DefaultDateTypeAdapter(Class<? extends Date> dateType, String datePattern) {
        this(dateType, new SimpleDateFormat(datePattern, Locale.US), new SimpleDateFormat(datePattern));
    }

    DefaultDateTypeAdapter(Class<? extends Date> dateType, int style) {
        this(dateType, DateFormat.getDateInstance(style, Locale.US), DateFormat.getDateInstance(style));
    }

    public DefaultDateTypeAdapter(int dateStyle, int timeStyle) {
        this(Date.class,
                DateFormat.getDateTimeInstance(dateStyle, timeStyle, Locale.US),
                DateFormat.getDateTimeInstance(dateStyle, timeStyle));
    }

    @SuppressLint("SimpleDateFormat")
    public DefaultDateTypeAdapter(Class<? extends Date> dateType, int dateStyle, int timeStyle) {
        this(dateType,
                DateFormat.getDateTimeInstance(dateStyle, timeStyle, Locale.US),
                DateFormat.getDateTimeInstance(dateStyle, timeStyle));
    }

    DefaultDateTypeAdapter(final Class<? extends Date> dateType, DateFormat enUsFormat, DateFormat localFormat) {
        if ( dateType != Date.class && dateType != java.sql.Date.class && dateType != Timestamp.class ) {
            throw new IllegalArgumentException("Date type must be one of " + Date.class + ", " + Timestamp.class + ", or " + java.sql.Date.class + " but was " + dateType);
        }
        this.dateType = dateType;
        this.enUsFormat = enUsFormat;
        this.localFormat = localFormat;
    }

    // These methods need to be synchronized since JDK DateFormat classes are not thread-safe
    // See issue 162
    // TODO: Add a test for null
    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        synchronized (localFormat) {
            if (value == null) {
                out.nullValue();
            } else {
                String dateFormatAsString = enUsFormat.format(value);
                out.value(dateFormatAsString);
            }
        }
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }

        if (in.peek() != JsonToken.STRING) {
            throw new JsonParseException("The date should be a string value");
        }

        Date date = deserializeToDate(in.nextString());

        if (dateType == Date.class) {
            return date;
        } else if (dateType == Timestamp.class) {
            return new Timestamp(date.getTime());
        } else if (dateType == java.sql.Date.class) {
            return new java.sql.Date(date.getTime());
        } else {
            // This must never happen: dateType is guarded in the primary constructor
            throw new AssertionError();
        }
    }

    private Date deserializeToDate(String s) {
        synchronized (localFormat) {
            try {
                return localFormat.parse(s);
            } catch (ParseException ignored) {}
            try {
                return enUsFormat.parse(s);
            } catch (ParseException ignored) {}
            try {
                return ISO8601Utils.parse(s, new ParsePosition(0));
            } catch (ParseException e) {
                throw new JsonSyntaxException(s, e);
            }
        }
    }

    @Override
    public String toString() {
        return TAG + '(' + localFormat.getClass().getSimpleName() + ')';
    }
}