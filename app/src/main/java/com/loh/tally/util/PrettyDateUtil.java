package com.loh.tally.util;

import org.ocpsoft.pretty.time.PrettyTime;

import java.util.Date;

/**
 * File: PrettyDateUtil.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
public class PrettyDateUtil {

    private PrettyDateUtil() {
    }

    public static String getPretty(long time) {
        return new PrettyTime().format(new Date(time));
    }

}
