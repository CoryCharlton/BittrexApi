# BittrexApi 

BittrexApi is an Android library that implements Bittrex api calls. It currently implements all documented calls from the 1.1 api version that is documented. Work is in progress to reverse engineer the 2.0 api calls and websockets implementation.

## Documentation ##

* The [class reference][] documents the BittrexApi library classes.
* The [api reference][] documents the Bittrex api calls

[class reference]: https://corycharlton.github.io/BittrexApi/reference
[api reference]: https://bittrex.com/home/api

## Using BittrexApi ##

BittrexApi modules can be obtained via jCenter. It's also possible to clone the
repository and depend on the modules locally.

### Via jCenter ### 

[![Download](https://api.bintray.com/packages/corycharlton/bittrexapi/bittrexapi/images/download.svg) ](https://bintray.com/corycharlton/bittrexapi/bittrexapi/_latestVersion)

The easiest way to get started using BittrexApi is to add it as a gradle
dependency. You need to make sure you have the jcenter repository included in
the `build.gradle` file in the root of your project:

```gradle
repositories {
    jcenter()
}
```

Next add a gradle compile dependency to the `build.gradle` file of your app
module. The following will add a dependency to the library:

```gradle
compile 'com.corycharlton.bittrexapi:bittrexapi:0.1.0'
```

Additionally include any extensions modules you need. The available modules
are:

* `extension-okhttp`: OkHttp extension for BittrexApi. 

For more details, see the project on [Bintray][]. For information about the
latest versions, see the [Release notes][].

[Bintray]: https://bintray.com/corycharlton/BittrexApi
[Release notes]: https://github.com/CoryCharlton/BittrexApi/blob/master/RELEASENOTES.md

## Build Status ##

master:

[![Build Status](https://travis-ci.org/CoryCharlton/Android-Bittrex-Api.svg?branch=master)](https://travis-ci.org/CoryCharlton/Android-Bittrex-Api)

dev:

[![Build Status](https://travis-ci.org/CoryCharlton/Android-Bittrex-Api.svg?branch=dev)](https://travis-ci.org/CoryCharlton/Android-Bittrex-Api)
