package com.discord.discordbot.listener;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

@Slf4j
public class KikiListener extends ListenerAdapter {

    /**
     * <b>디스코드 사용자 메시지를 받게 되면 처리하게 되는 Method</b>
     * @param event Message를 통해 Event를 처리할 수 있는 JDA 객체
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User user = event.getAuthor();
        TextChannel textChannel = event.getChannel().asTextChannel(); // 채널 정보를 가져올 수 있도록 함
        Message message = event.getMessage();

        log.info("get message : " + message.getContentDisplay());

        if (user.isBot()) {
            return;
        } else if (message.getContentDisplay().equals("")) {
            log.info("디스코드 Message 문자열 값 공백");
        }

        String[] messageArray = message.getContentDisplay().split(" ");

        if(messageArray[0].equalsIgnoreCase("키키야")) {

            String returnMessage = "";


            switch (messageArray[1]) {
                case "안녕" : returnMessage = user.getName() + "님 안녕하세요? 키키에요!";
                    break;
                case "test" : returnMessage = user.getAsTag() + "님 테스트 중이세요?";
                    break;
                case "누구야" : returnMessage = user.getAsMention() + "님 저는 mackwin님이 JAVA로 생성한 Bot이에ㅛ!";
                    break;
                case "사다리" : returnMessage = getRandomName(messageArray[2]) + "님 회의록 작성 부탁드려요 ㅎㅎ";
                    break;

                default: returnMessage = "명령어를 확인해 주세요";
                    break;

            }

            textChannel.sendMessage(returnMessage).queue();

        }
    }

    public String getRandomName(String namesString) {
        // 입력된 이름을 공백을 기준으로 리스트로 변환
        String[] namesArray = namesString.split(",");

        // 랜덤으로 인덱스를 선택하여 이름을 반환
        Random random = new Random();
        int randomIndex = random.nextInt(namesArray.length);
        return namesArray[randomIndex];
    }
}
