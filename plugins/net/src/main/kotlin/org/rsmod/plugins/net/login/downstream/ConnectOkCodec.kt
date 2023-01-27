package org.rsmod.plugins.net.login.downstream

import io.netty.buffer.ByteBuf
import org.openrs2.crypto.StreamCipher
import org.rsmod.protocol.packet.VariableByteLengthPacketCodec
import javax.inject.Singleton

@Singleton
class ConnectOkCodec : VariableByteLengthPacketCodec<LoginResponse.ConnectOk>(
    type = LoginResponse.ConnectOk::class.java,
    opcode = 2
) {

    override fun decode(buf: ByteBuf, cipher: StreamCipher): LoginResponse.ConnectOk {
        val rememberDevice = buf.readBoolean()
        buf.readInt() // TODO: do something with client identifier
        val playerModLevel = buf.readByte().toInt()
        val playerMod = buf.readBoolean()
        val playerIndex = buf.readUnsignedShort()
        val playerMember = buf.readBoolean()
        val accountHash = buf.readLong()
        return LoginResponse.ConnectOk(
            rememberDevice = rememberDevice,
            playerModLevel = playerModLevel,
            playerMod = playerMod,
            playerIndex = playerIndex,
            playerMember = playerMember,
            accountHash = accountHash,
            cipher = cipher
        )
    }

    override fun encode(packet: LoginResponse.ConnectOk, buf: ByteBuf, cipher: StreamCipher) {
        buf.writeBoolean(packet.rememberDevice)
        if (packet.rememberDevice) {
            // TODO: write device/client identifier
            for (i in 0 until 4) {
                val scrambled = 0 + packet.cipher.nextInt()
                buf.writeByte(scrambled)
            }
        } else {
            buf.writeInt(0)
        }
        buf.writeByte(packet.playerModLevel)
        buf.writeBoolean(packet.playerMod)
        buf.writeShort(packet.playerIndex)
        buf.writeBoolean(packet.playerMember)
        buf.writeLong(packet.accountHash)
    }
}
