package com.ikhokha.techcheck.Operations;

public class ShorterThan15 implements Operation {
    @Override
    public boolean apply(String comment) {
        return comment.length() < 15;
    }
}
