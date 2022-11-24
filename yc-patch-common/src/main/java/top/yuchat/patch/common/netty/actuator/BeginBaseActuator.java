package top.yuchat.patch.common.netty.actuator;

public abstract class BeginBaseActuator implements IActuator {

    @Override
    public int cmd() {
        return 2;
    }
}
