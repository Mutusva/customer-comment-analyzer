package com.ikhokha.techcheck.Operations;

public class ContainShaker implements Operation {
    @Override
    public boolean apply(String comment) {
        return comment.toUpperCase().contains("SHAKER");
    }
}
