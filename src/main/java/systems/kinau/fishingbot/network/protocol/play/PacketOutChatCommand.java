/*
 * Created by David Luedtke (MrKinau)
 * 2019/5/5
 */

package systems.kinau.fishingbot.network.protocol.play;

import com.google.common.io.ByteArrayDataOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import systems.kinau.fishingbot.network.protocol.NetworkHandler;
import systems.kinau.fishingbot.network.protocol.Packet;
import systems.kinau.fishingbot.network.utils.ByteArrayDataInputWrapper;

@AllArgsConstructor
public class PacketOutChatCommand extends Packet {

    @Getter
    private String command;

    @Override
    public void write(ByteArrayDataOutput out, int protocolId) {
        writeString(getCommand(), out);
        out.writeLong(System.currentTimeMillis());  // timestamp

        // this is most likely very illegal, but it seems like the server does not care about the signature
        out.writeLong(System.currentTimeMillis());  // arg sig salt
        writeVarInt(0, out);                  // arg sig map
        out.writeBoolean(false);                 // signed preview
    }

    @Override
    public void read(ByteArrayDataInputWrapper in, NetworkHandler networkHandler, int length, int protocolId) {
        // Only outgoing packet
    }
}
