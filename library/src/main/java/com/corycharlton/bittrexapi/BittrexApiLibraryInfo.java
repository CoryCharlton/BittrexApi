package com.corycharlton.bittrexapi;

public final class BittrexApiLibraryInfo {

    public static final String TAG = "BittrexApi";
    public static final String USERAGENT_VERSION = "com.corycharlton.bittrexapi/0.1.0";
    public static final String VERSION = "0.1.0";

    /**
     * The version of the library expressed as an integer, for example 1002003.
     * <p>
     * Three digits are used for each component of {@link #VERSION}. For example "1.2.3" has the
     * corresponding integer version 1002003 (001-002-003), and "123.45.6" has the corresponding
     * integer version 123045006 (123-045-006).
     */
    // Intentionally hardcoded. Do not derive from other constants (e.g. VERSION) or vice versa.
    public static final int VERSION_INT = 1000;

    public static final boolean ASSERTIONS_ENABLED = true;

    public static final boolean TRACE_ENABLED = true;

    private BittrexApiLibraryInfo() {} // Cannot instantiate.
}
