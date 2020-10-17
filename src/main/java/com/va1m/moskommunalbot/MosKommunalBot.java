package com.va1m.moskommunalbot;

import com.va1m.moskommunalbot.interaction.InteractionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/** Contains the bot's logic */
public class MosKommunalBot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LogManager.getLogger(MosKommunalBot.class);

    private final InteractionService interactionService;

    private final String token;

    /** Constructor */
    public MosKommunalBot(InteractionService interactionService, String token) {
        this.interactionService = interactionService;
        this.token = token;
    }

    @Override
    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

            final var chatId = update.getMessage().getChatId();
            final var input = update.getMessage().getText();

            final var reply = interactionService.getReply(chatId, input);

            SendMessage message = new SendMessage()
                    .setChatId(chatId)
                    .setText(reply);
            try {
                // Call method to send the message
                execute(message);
            } catch (TelegramApiException e) {
                LOGGER.error("", e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "komunalkobot";
    }

    @Override
    public String getBotToken() {
        return token;
    }
}