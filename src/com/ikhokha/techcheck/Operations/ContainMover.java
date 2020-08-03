package com.ikhokha.techcheck.Operations;

public class ContainMover implements Operation {
    @Override
    public boolean apply(String comment) {
        return comment.toUpperCase().contains("MOVER");
    }
}
