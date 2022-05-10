package com.amazonaws.util;

import static org.junit.Assert.assertArrayEquals;

import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.RealJenkinsRule;

public class Base64Test {

    private static final String sample1 = "mO4TyLWG7vjFWdKT8IJcVbZ/jwc=";
    private static final byte[] sample1Bytes;
    private static final String sample2 = "F4I4p8Vf/mS+Kxvri3FPoMcqmJ1f";
    private static final byte[] sample2Bytes;
    private static final String sample3 = "UJmEdJYodqHJmd7Rtv6/OP29/jUEFw==";
    private static final byte[] sample3Bytes;

    static {
        try {
            sample1Bytes = Hex.decodeHex("98ee13c8b586eef8c559d293f0825c55b67f8f07");
            sample2Bytes = Hex.decodeHex("178238a7c55ffe64be2b1beb8b714fa0c72a989d5f");
            sample3Bytes = Hex.decodeHex("50998474962876a1c999ded1b6febf38fdbdfe350417");
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    @Rule public RealJenkinsRule rr = new RealJenkinsRule();

    @Test
    public void smokes() throws Throwable {
        rr.then(Base64Test::_smokes);
    }

    private static void _smokes(JenkinsRule r) {
        assertArrayEquals("1".getBytes(StandardCharsets.US_ASCII), Base64.decode("MQ=="));
        assertArrayEquals(sample1Bytes, Base64.decode(sample1));
        assertArrayEquals(sample2Bytes, Base64.decode(sample2));
        assertArrayEquals(sample3Bytes, Base64.decode(sample3));
    }
}
