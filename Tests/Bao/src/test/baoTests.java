import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class baoTests {
    SuperComplexBackEnd backEnd=new SuperComplexBackEnd();

    @BeforeEach
    void setUp() {
        backEnd.addAllEmails(new String[]{"20108791@setu.ie", "lecturer1@setu.ie", "student1@setu.ie",
                "lecturer2@setu.ie", "lecturer3@setu.ie", "student2@setu.ie"});
        backEnd.setTagCount("Trish", "Friendly", 27);
        backEnd.setTagCount("Trish", "Lenient Scoring", 25);
        backEnd.setTagCount("Trish", "Fair", 22);
        backEnd.setTagCount("Trish", "Nice", 21);
        backEnd.setTagCount("Trish", "Harsh Scoring", 1);
        backEnd.setTagCount("Peter", "GOAT", 15);
        backEnd.setTagCount("Jerry", "Hard MCQ", 100000);
        backEnd.setTagCount("Jerry", "Harsh Scoring", 100000);
    }

    @AfterEach
    void tearDown() {
        backEnd.clearEmails();
        backEnd.clearTags();
    }

    @Test
    void emailTest() {
        //Adding valid email
        assertEquals("OK", backEnd.addEmail("20113304@setu.ie"));
        assertTrue(backEnd.containsEmail("20113304@setu.ie"));

        //Adding invalid email - outside email
        assertEquals("Email does not belong to SETU", backEnd.addEmail("randomGoofball@gmail.com"));
        assertFalse(backEnd.containsEmail("randomGoofball@gmail.com"));

        //Adding invalid email - duplicate email
        assertEquals("Email already exists", backEnd.addEmail("20113304@setu.ie"));
    }

    @Test
    void tagTest()
    {
        //Test top 3 with >3 entries
        ArrayList <String> top3Trish=backEnd.getTopThreeTags("Trish");
        assertTrue(top3Trish.contains("Fair"));
        assertTrue(top3Trish.contains("Friendly"));
        assertTrue(top3Trish.contains("Lenient Scoring"));
        System.out.println("Trish\n");
        for (String tag : top3Trish)
            System.out.println(tag);

        //Test adding more tag counts
        backEnd.addTag("Trish", "Nice");
        backEnd.addTag("Trish", "Nice");
        top3Trish=backEnd.getTopThreeTags("Trish");
        assertTrue(top3Trish.contains("Nice"));
        assertFalse(top3Trish.contains("Fair"));

        //Test top 3 with <3 entries
        ArrayList <String> top3Peter=backEnd.getTopThreeTags("Peter");
        System.out.println("\n\n\nPeter\n");
        for (String tag : top3Peter)
            System.out.println(tag);
        assertTrue(top3Peter.contains("GOAT"));
        assertEquals(1, top3Peter.size());

        //Test equality handling
        ArrayList <String> top3Jerry=backEnd.getTopThreeTags("Jerry");
        System.out.println("\n\n\nJerry\n");
        for (String s : top3Jerry) System.out.println(s);
        assertTrue(top3Jerry.contains("Hard MCQ"));
        assertTrue(top3Jerry.contains("Harsh Scoring"));
        assertEquals(2, top3Jerry.size());

        //Test non-existent
        assertNull(backEnd.getTopThreeTags("Joey"));
    }
}