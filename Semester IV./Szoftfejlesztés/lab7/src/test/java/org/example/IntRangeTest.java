package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntRangeTest {

    private IntRange underTest;

    @BeforeEach
    void setUp() {
        underTest = IntRange.of(10, 30);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void of() {
        IntRange range = IntRange.of(3, 8);
        assertEquals(3, range.getMin());
        assertEquals(8, range.getMax());

        IntRange swapped = IntRange.of(103, 2);
        assertEquals(2, swapped.getMin());
        
    }

    @Test
    void testOf() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void contains() {
        assertTrue(underTest.contains(15));
        assertFalse(underTest.contains(5));
        assertFalse(underTest.contains(40));
        assertTrue(underTest.contains(10));
        assertTrue(underTest.contains(30));
        assertFalse(underTest.contains(9));
        assertFalse(underTest.contains(31));
        assertFalse(underTest.contains(-4));
    }

    @Test
    void testContains() {
        assertTrue(underTest.contains(IntRange.of(15,20)));
        assertTrue(underTest.contains(IntRange.of(10,30)));

        assertFalse(underTest.contains(IntRange.of(9,29)));
        assertFalse(underTest.contains(IntRange.of(11,31)));

        assertTrue(underTest.contains(IntRange.of()));
        assertFalse((IntRange.of().contains(underTest)));

    }

    @Test
    void isOverlapping() {
        IntRange range1 = IntRange.of(20,40);
        IntRange range2 = IntRange.of(32,50);

        assertTrue(underTest.isOverlapping(range1));
        assertTrue(range1.isOverlapping(range2));
        assertFalse(underTest.isOverlapping(range2));
    }

    @Test
    void isDisjoint() {
        IntRange range1 = IntRange.of(20,40);
        IntRange range2 = IntRange.of(32,50);

        assertFalse(underTest.isDisjoint(range1));
        assertFalse(range1.isDisjoint(range2));
        assertTrue(underTest.isDisjoint(range2));

    }

    @Test
    void intersect() {
        IntRange range1 = IntRange.of(12,32);
        IntRange range2 = IntRange.of(40,50);


        assertEquals(IntRange.of(12,30),underTest.intersect(range1));
        assertEquals(IntRange.of(),underTest.intersect(range2));
        assertEquals(IntRange.of(),underTest.intersect());

        assertSame(underTest,underTest.intersect(underTest));
    }

    @Test
    void testIntersect() {
        IntRange range1 = IntRange.of(16,42);
        IntRange range2 = IntRange.of(20,35);

        assertEquals(IntRange.of(20,30), underTest.intersect(range1,range2));
        assertEquals(IntRange.of(20,30), underTest.intersect(range2,range1));
        assertEquals(IntRange.of(20,30), range1.intersect(range2,underTest));
        assertEquals(IntRange.of(20,30), range1.intersect(underTest,range2));
        assertEquals(IntRange.of(20,30), range2.intersect(range1,underTest));
        assertEquals(IntRange.of(20,30), range2.intersect(underTest,range1));




    }
}