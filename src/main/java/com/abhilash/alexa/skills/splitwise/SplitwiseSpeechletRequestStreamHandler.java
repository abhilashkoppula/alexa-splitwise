package com.abhilash.alexa.skills.splitwise;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by abhilash on 11/20/16.
 */
public class SplitwiseSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<String>();
    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        supportedApplicationIds.add("amzn1.ask.skill.a2bb58b1-3c12-4d9f-9a88-6aa5088586a8");
    }

    public SplitwiseSpeechletRequestStreamHandler() {
        super(new SplitwiseSpeechlet(), supportedApplicationIds);
    }
}
