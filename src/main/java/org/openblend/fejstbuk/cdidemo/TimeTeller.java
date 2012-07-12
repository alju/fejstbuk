package org.openblend.fejstbuk.cdidemo;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: priprave04
 * Date: 12.7.12
 * Time: 9:59
 * To change this template use File | Settings | File Templates.
 */
public class TimeTeller {
    @Inject
    private StringUppercaser su;

    public String getTime() {

        String ret = "The time is " + new Date().toString();
        return su.toUppercase(ret);
    }
}
