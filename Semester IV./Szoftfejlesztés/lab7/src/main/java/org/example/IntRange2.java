package org.example;

import java.util.Arrays;
import java.util.Objects;

public class IntRange2 {

    private int min;
    private int max;

    private IntRange2(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static IntRange2 of(){
        return new IntRange2(Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    public static IntRange2 of(int min, int max){
        if (min > max) {
            return new IntRange2(max, min);
        }
        else{
            return new IntRange2(min, max);
        }
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public boolean isEmpty(){
        if (min == Integer.MAX_VALUE && max == Integer.MIN_VALUE){
            return true;
        }
        return false;
    }

    public boolean contains(int value){
        if (min-1 < value && max+1 > value){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean contains(IntRange range){
        if (range.getMin() > min-1 && range.getMax() < max+1){
            return true;
        }
        return false;
    }

    public boolean isOverLapping(IntRange range){
        if (range.getMin() > min-1 && range.getMin() < max+1) {
            return true;
        } else if (range.getMax() > min-1 && range.getMax() < max+1) {
            return true;
        }
        return false;
    }

    public boolean isDisjoin(IntRange range){
        if (range.getMin() > min-1 && range.getMin() < max+1) {
            return false;
        } else if (range.getMax() > -1 && range.getMax() < max+1) {
            return false;
        }
        return true;
    }

    public IntRange intersect(IntRange range){
        int[] mins = {min, range.getMin()};
        int[] maxs = {max, range.getMax()};
        Arrays.sort(mins);
        Arrays.sort(maxs);
        if (mins[mins.length-1] < maxs[0]){
            if (mins[mins.length-1] == min && maxs[0] == max){
                return this;
            }else {
                return IntRange.of(mins[mins.length - 1], maxs[0]);
            }
        }
        return IntRange.of();
    }

    public IntRange intersect(IntRange... ranges){
        int[] mins = new int[ranges.length+1];
        int[] maxs  = new int[ranges.length+1];
        mins[0] = min;
        maxs[0] = max;
        int i = 0;
        for (IntRange r : ranges) {
            i++;
            mins[i] = r.getMin();
            maxs[i] = r.getMax();
        }
        Arrays.sort(mins);
        Arrays.sort(maxs);
        if (mins[mins.length-1] < maxs[0]){
            return IntRange.of(mins[mins.length-1],maxs[0]);
        }
        return IntRange.of();
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

    public String toString(){
        if(isEmpty()){
            return "[Empty]";
        } else {
            return String.format("[%d, %d]", min, max);
        }
    }
}
