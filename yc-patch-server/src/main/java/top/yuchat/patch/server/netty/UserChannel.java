package top.yuchat.patch.server.netty;

import io.netty.channel.Channel;
import lombok.Data;
import top.yuchat.patch.common.netty.actuator.IActuator;

import java.util.Map;
import java.util.Random;

@Data
public class UserChannel {

    private String userName;

    private Map<String, Channel> channels;

    public void exec(IActuator actuator) {
        Random random = new Random();
        String[] keys = channels.keySet().toArray(new String[0]);
        int index = random.nextInt(keys.length);
        actuator.exec(channels.get(keys[index]));
    }
}
