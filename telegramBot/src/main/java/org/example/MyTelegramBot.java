package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyTelegramBot extends TelegramLongPollingBot {

    private final Set<Long> userChatIds = new HashSet<>();
    private final CassandraConnector cassandraConnector = new CassandraConnector();
    private final DatabaseChecker databaseChecker;
    private final ScheduledExecutorService scheduler;

    public MyTelegramBot() {
        cassandraConnector.connect("cassandra", 9042, "moviesdb");
        databaseChecker = new DatabaseChecker(cassandraConnector);
        scheduler = Executors.newScheduledThreadPool(1);

        try {
            scheduler.scheduleAtFixedRate(this::checkAndNotify, 0, 5, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.err.println("Error scheduling database check: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void checkAndNotify() {
        try {
            if (databaseChecker.checkForModifications()) {
                notifyUsersDatabaseModified();
            }
        } catch (Exception e) {
            System.err.println("Error checking for database modifications: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            String text = message.getText();

            // Track users who send /start command
            if ("/start".equals(text)) {
                userChatIds.add(chatId);
                sendMessage(chatId, "Welcome! You have been registered for updates.");
            }

            // Handle other messages if needed
        }
    }

    public void notifyUsersDatabaseModified() {
        sendMessageToAllUsers("Database has been modified recently!");
    }

    private void sendMessageToAllUsers(String messageText) {
        for (Long chatId : userChatIds) {
            sendMessage(chatId, messageText);
        }
    }

    private void sendMessage(Long chatId, String messageText) {
        SendMessage message = new SendMessage(String.valueOf(chatId), messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.err.println("Error sending message to user " + chatId + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "rtptest286941_bot"; // Replace with your bot username
    }

    @Override
    public String getBotToken() {
        return "7094676506:AAEPLKjWN3jtMO8vE_R-QxiYKHnJIz4M9Z0"; // Replace with your bot token
    }

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MyTelegramBot());
            System.out.println("MyTelegramBot has been successfully registered.");
        } catch (TelegramApiException e) {
            System.err.println("Error registering Telegram bot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
