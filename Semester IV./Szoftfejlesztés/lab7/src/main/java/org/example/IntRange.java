package org.example;

import java.util.Objects;

public class IntRange {

    private int min, max;

    private IntRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static IntRange of(){
        return new IntRange(Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    public static IntRange of(int min, int max){
        if(min < max){
            return new IntRange(min,max);
        }
        return new IntRange(max,min);
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public boolean isEmpty(){
        return this.getMin() == this.getMax();
    }

    public boolean contains(int value){
        return this.getMin() <= value &&  value <= this.getMax();
    }

    public boolean contains(IntRange range){
        return this.getMin() <= range.getMin() && range.getMax() <= this.getMax();
    }

    public boolean isOverlapping(IntRange range){
        return (this.getMin() <= range.getMin() && range.getMin() <= this.getMax()) || (range.getMax() <= this.getMax() && this.getMin() <= range.getMax());
    }

    public boolean isDisjoint(IntRange range){
        return !(this.getMin() <= range.getMin() && range.getMin() <= this.getMax()) && !(range.getMax() <= this.getMax() && this.getMin() <= range.getMax());
    }

    public IntRange intersect(IntRange range){
        //TODO
        return null;
    }

    public IntRange intersect(IntRange... ranges){
        //TODO
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntRange intRange = (IntRange) o;
        return min == intRange.min && max == intRange.max;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }


    public String toString() {
        if(isEmpty()){
            return "[Empty]";
        }
        else{
            return String.format("[%d, %d]", min, max);
        }
    }
}
