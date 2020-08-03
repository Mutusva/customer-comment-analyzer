package com.ikhokha.techcheck.Operations;

import com.ikhokha.techcheck.CommentCategoty;

import java.util.HashMap;
import java.util.Map;

public class OperatorFactory {
    public static Map<String, Operation> operationMap = new HashMap<>();
    static {
        operationMap.put(CommentCategoty.SHORTER_THAN_15.toString(),  new ShorterThan15());
        operationMap.put(CommentCategoty.MOVER_MENTIONS.toString(), new ContainMover());
        operationMap.put(CommentCategoty.SHAKER_MENTIONS.toString(), new ContainShaker());
        operationMap.put(CommentCategoty.QUESTIONS.toString(), new ContainQuestion());
        operationMap.put(CommentCategoty.SPAM.toString(), new Spam());
    }

}
