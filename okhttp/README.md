# BittrexApi OkHttp extension #

## Description ##

The OkHttp extension is a [Downloader][] implementation using Square's [OkHttp][].

[Downloader]: https://corycharlton.github.io/BittrexApi/reference/com/corycharlton/bittrexapi/Downloader.html
[OkHttp]: https://square.github.io/okhttp/

## Getting the extension ##

The easiest way to use the extension is to add it as a gradle dependency:

```gradle
compile 'com.corycharlton.bittrexapi:extension-okhttp:X.X.X'
```

where `X.X.X` is the version, which must match the version of the BittrexApi library being used.

## Using the extension ##

BittrexApi requests data through `Downloader` instances. These instances are configured via the `BittrexApiClient.Builder`

```
	new BittrexApiClient.Builder()
		...
		.downloader(new OkHttpDownloader())
		...
```

