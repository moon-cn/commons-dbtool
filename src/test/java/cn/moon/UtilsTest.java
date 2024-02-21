package cn.moon;


import cn.moon.dbtool.Helpers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {

    @Test
    public void testToUnderlineCase() {
        assertEquals("student", Helpers.underline("Student"));
        assertEquals("student", Helpers.underline("student"));
        assertEquals("good_student", Helpers.underline("goodStudent"));
        assertEquals("good_student", Helpers.underline("GoodStudent"));
    }
}
