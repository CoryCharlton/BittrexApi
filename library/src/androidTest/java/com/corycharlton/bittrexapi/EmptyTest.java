package com.corycharlton.bittrexapi;

import android.support.annotation.Keep;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
@SuppressWarnings("ConstantConditions")
public class EmptyTest {

    @Keep
    @Test
    public void This_test_is_empty() {
        Assert.assertTrue(true != false);
    }
}
