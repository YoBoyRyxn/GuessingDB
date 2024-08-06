package org.example;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

import java.util.Random;

public class App {

    public static int Numbers() {
        Random r = new Random();
        return r.nextInt(0, 10);
    }

    public static void main(String[] args) {
        String token = "";
        DiscordClient d = DiscordClient.create(token);
        GatewayDiscordClient gate = d.login().block();

        int n = Numbers();
        gate.on(MessageCreateEvent.class).subscribe(e -> {
            Message message = e.getMessage();
            MessageChannel c = message.getChannel().block();



            if (message.getContent().startsWith("/Guess")
                    && message.getContent().startsWith(String.valueOf(n))) {
                assert c != null;
                c.createMessage("Correct").block();
            }
            else if (message.getContent().startsWith("/Guess")
            && !message.getContent().startsWith(String.valueOf(n))) {

                assert c != null;
                c.createMessage("Wrong Number The Guessing Number was: " + n).block();
            }

        });

        gate.onDisconnect().block();

    }

}