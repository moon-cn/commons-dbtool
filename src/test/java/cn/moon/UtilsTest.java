package cn.moon;


import cn.moon.dbtool._Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {

    @Test
    public void testToUnderlineCase() {
        assertEquals("student", _Util.underline("Student"));
        assertEquals("student", _Util.underline("student"));
        assertEquals("good_student", _Util.underline("goodStudent"));
        assertEquals("good_student", _Util.underline("GoodStudent"));
    }
}
