package org.example.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EventListeners extends ListenerAdapter {

    public static final String MENTIONED_USER_ID = "<@827584632262230046>";
    public static final Emoji LAUGH_EMOJI = Emoji.fromUnicode("U+1F923");
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();
        String jumpLink = event.getJumpUrl();

        String message = user.getAsTag() + " reacted to a message with " + emoji + " in the " + channelMention  + " channel!";
        event.getGuild().getDefaultChannel().asTextChannel().sendMessage(message).queue();
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        String avatar = event.getUser().getEffectiveAvatarUrl();
        System.out.println(avatar);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.contains(MENTIONED_USER_ID)) {
            event.getMessage().addReaction(LAUGH_EMOJI).queue();
        }
    }
//    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
//        Message message = event.getMessage();
//        List<User> mentionedUsers = message.getMentions().getUsers();
//
//        for (User user : mentionedUsers) {
//            if (user.getId().equals(MENTIONED_USER_ID)){
//                message.addReaction(LAUGH_EMOJI);
//            }
//        }
//    }

    @Override
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
        User user = event.getUser();
        String message = user.getAsTag() + " updated their status from " + event.getOldOnlineStatus() +" to " + event.getNewOnlineStatus().getKey() + "!";
        event.getGuild().getDefaultChannel().asTextChannel().sendMessage(message).queue();
    }
}

