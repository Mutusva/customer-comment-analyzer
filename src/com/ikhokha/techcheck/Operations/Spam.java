package com.ikhokha.techcheck.Operations;

public class Spam implements Operation {
    @Override
    public boolean apply(String comment) {
        return comment.contains("http") || comment.contains("www");
    }
}
