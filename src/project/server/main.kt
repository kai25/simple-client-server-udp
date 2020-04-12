package project.server

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.time.LocalDate
import java.time.LocalTime

const val UDP_SERVER_PORT = 4747
const val MESSAGE_LENGTH = 256

fun main() {
    val socket = DatagramSocket(UDP_SERVER_PORT)
    val packetBuffer = ByteArray(MESSAGE_LENGTH)

    while (true) {
        val packet = DatagramPacket(packetBuffer, MESSAGE_LENGTH)
        println("Waiting for connection on :$UDP_SERVER_PORT")
        socket.receive(packet)

        val receivedMessage = String(packet.data, 0, packet.length)
        println("Message from user ${packet.address}:${packet.port} - $receivedMessage")

        val responseMessage = when(receivedMessage) {
            "time" -> LocalTime.now().toString()
            "date" -> LocalDate.now().toString()
            "members" -> "IT-91 & IT-93:\nOmelchenko\nTsytovtseva\nNovohatskiy"
            else -> "Unsupported command"
        }

        val responseBytes = responseMessage.toByteArray()
        val responsePackage = DatagramPacket(responseBytes, responseBytes.size, packet.address, packet.port)
        socket.send(responsePackage)
    }
}