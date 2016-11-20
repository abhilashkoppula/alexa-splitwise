package com.abhilash.alexa.skills.splitwise;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by abhilash on 11/20/16.
 */
public class SplitwiseSpeechlet implements Speechlet {
    private static final Logger LOG = LoggerFactory.getLogger(SplitwiseSpeechlet.class);

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException {
        LOG.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        // Establish connection to Splitwise API ???
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {
        LOG.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return getWelcomeResponse();
    }



    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException {
        LOG.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;
        LOG.info("onIntent intentName={}", intentName);
        if ("FriendExpenseIntent".equals(intentName)) {
            return getFriendExpenseIntentResponse(intent);
        } else if("GroupExpenseIntent".equals(intentName)) {
            return getGroupExpenseIntentResponse(intent);
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException {

    }
    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Welcome to the Abhilash Splitwise. Ask to split expenses";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Splitwise");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    public SpeechletResponse getFriendExpenseIntentResponse(final Intent intent) {
        double expense = Double.valueOf(intent.getSlot("Expense").getValue());
        String friend = (intent.getSlot("FriendName").getValue());

        String speechText = String.format("Created an expense of %s sharing with %s", String.valueOf(expense), friend);
        SimpleCard card = new SimpleCard();
        card.setTitle("Splitwise-FriendExpense");
        card.setContent(speechText);

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    public SpeechletResponse getGroupExpenseIntentResponse(final Intent intent) {
        double expense = Double.valueOf(intent.getSlot("Expense").getValue());
        String friend = (intent.getSlot("FriendName").getValue());

        String speechText = String.format("Created an expense of %s sharing with %s", String.valueOf(expense), friend);
        SimpleCard card = new SimpleCard();
        card.setTitle("Splitwise-GroupExpense");
        card.setContent(speechText);

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    public SpeechletResponse getHelpResponse() {
        String speechText = "You can say get my expenses !";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Splitwise");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
}
