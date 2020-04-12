package project.client

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

const val UDP_SERVER_PORT = 4747
const val MESSAGE_LENGTH = 256

val UDP_SERVER_ADDRESS = InetAddress.getByName("0.0.0.0")


fun main() {
    val socket = DatagramSocket()
    val consoleReader = BufferedReader(InputStreamReader(System.`in`))
    val packetBuffer = ByteArray(MESSAGE_LENGTH)

    while (true) {
        println("Waiting for input from console(time, date, members):")
        val messageBytes = consoleReader.readLine().toByteArray()
        val packet = DatagramPacket(messageBytes, messageBytes.size, UDP_SERVER_ADDRESS, UDP_SERVER_PORT)
        socket.send(packet)

        val receivedPackage = DatagramPacket(packetBuffer, MESSAGE_LENGTH)
        socket.receive(receivedPackage)

        val receivedMessage = String(receivedPackage.data, 0, receivedPackage.length)
        println("Message from server: $receivedMessage")
    }
}
