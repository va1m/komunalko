package com.va1m.moskommunalbot.interaction.stateprocessors;

import com.va1m.moskommunalbot.interaction.InteractionContext;
import com.va1m.moskommunalbot.interaction.State;

/** Prepare output message text for {@link State#WAITING_FOR_CURRENT_ELECTRICITY_METERS} state and then handles user input data */
public class CurrentElectricityMetersStateProcessor implements StateProcessor {

    @Override
    public State forState() {
        return State.WAITING_FOR_CURRENT_ELECTRICITY_METERS;
    }

    @Override
    public void processInput(String input, InteractionContext interactionContext) {
        storeIfValid(input, interactionContext::setCurrentElectricityMeters);
    }

    @Override
    public String processOutput(InteractionContext interactionContext) {
        return "Введите текущее показание счетчика электричества.";
    }
}