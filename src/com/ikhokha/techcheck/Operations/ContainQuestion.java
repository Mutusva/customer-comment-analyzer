package com.ikhokha.techcheck.Operations;

public class ContainQuestion implements Operation {
    @Override
    public boolean apply(String comment) {
        return comment.contains("?");
    }
}
