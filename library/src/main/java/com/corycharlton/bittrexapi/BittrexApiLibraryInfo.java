package com.corycharlton.bittrexapi;

@SuppressWarnings({"unused", "WeakerAccess"})
public final class BittrexApiLibraryInfo {

    public static final String TAG = "BittrexApi";
    public static final String VERSION = "1.0.0";
    public static final String USERAGENT = "com.corycharlton.bittrexapi/" + VERSION;

    /**
     * The version of the library expressed as an integer, for example 1002003.
     * <p>
     * Three digits are used for each component of {@link #VERSION}. For example "1.2.3" has the
     * corresponding integer version 1002003 (001-002-003), and "123.45.6" has the corresponding
     * integer version 123045006 (123-045-006).
     */
    public static final int VERSION_INT = 1000000;

    private BittrexApiLibraryInfo() {} // Cannot instantiate.
}
