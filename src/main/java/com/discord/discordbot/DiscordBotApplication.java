package com.discord.discordbot;

import com.discord.discordbot.listener.KikiListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;

@SpringBootApplication
public class DiscordBotApplication {

    public static void main(String[] args) throws LoginException {
        ApplicationContext context = SpringApplication.run(DiscordBotApplication.class, args);
        DiscordBotToken discordBotTokenEntity = context.getBean(DiscordBotToken.class);
        String discordBotToken = discordBotTokenEntity.getDiscordBotToken();

        JDA jda = JDABuilder.createDefault(discordBotToken)
                .setActivity(Activity.playing("메시지 기다리는중!"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new KikiListener())
                .build();
    }

    @Component
    class DiscordBotToken {

        @Value("${discord.bot.token}")
        private String discordToken;
        public String getDiscordBotToken() {
            return discordToken;
        }
    }
}
