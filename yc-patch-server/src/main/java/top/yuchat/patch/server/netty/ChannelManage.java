package top.yuchat.patch.server.netty;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelManage {

    private static final ChannelManage CHANNEL_MANAGE = new ChannelManage();

    private static final Map<String, UserChannel> CHANNEL_MAP = new ConcurrentHashMap<>();

    private ChannelManage() {
    }

    public static ChannelManage getChannelManage() {
        return CHANNEL_MANAGE;
    }

    public void put(String userName, Channel channel) {
        UserChannel userChannel = CHANNEL_MAP.get(userName);
        if (userChannel == null) {
            Map<String, Channel> map = new ConcurrentHashMap<>();
            map.put(channel.id().asLongText(), channel);
            UserChannel uc = new UserChannel();
            uc.setUserName(userName);
            uc.setChannels(map);
            CHANNEL_MAP.put(userName, uc);
            return;
        }
        Map<String, Channel> channels = userChannel.getChannels();
        channels.put(channel.id().asLongText(), channel);
        userChannel.setChannels(channels);
    }

    public void remove(String userName, Channel channel) {
        UserChannel userChannel = CHANNEL_MAP.get(userName);
        if (userChannel == null) {
            return;
        }
        Map<String, Channel> channels = userChannel.getChannels();
        channels.remove(channel.id().asLongText());
        userChannel.setChannels(channels);
    }

    public UserChannel getUserChannel(String userName) {
        return CHANNEL_MAP.get(userName);
    }
}
