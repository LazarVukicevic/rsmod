package org.rsmod.plugins.net.login.downstream

import org.openrs2.crypto.StreamCipher
import org.rsmod.protocol.packet.Packet

sealed class LoginResponse : Packet {

    data class ConnectOk(
        val rememberDevice: Boolean,
        val playerModLevel: Int,
        val playerMod: Boolean,
        val playerIndex: Int,
        val playerMember: Boolean,
        val accountHash: Long,
        val cipher: StreamCipher
    ) : LoginResponse()

    object ClientOutOfDate : LoginResponse()
    object BadSessionId : LoginResponse()
    object ClientProtocolOutOfDate : LoginResponse()
}
