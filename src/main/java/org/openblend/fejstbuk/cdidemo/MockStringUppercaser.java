package org.openblend.fejstbuk.cdidemo;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Stereotype;

/**
 * Created with IntelliJ IDEA.
 * User: priprave04
 * Date: 12.7.12
 * Time: 10:01
 * To change this template use File | Settings | File Templates.
 */
@Alternative
public class MockStringUppercaser extends StringUppercaser{

    public String toUppercase(String s) {
        return "FOO";
    }
}