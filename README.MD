# UDP-SIMPLE-CLIENT-SERVER

Simple server and client using UDP protocol to communicate.

Implemented commands:
- time
- date
- members

# Stack:
- Kotlin1.3
- Standard Java Lib - java.net.*

# How to run:
````
    docker build -t simple-udp-app .
    docker run --rm -it --net=host -e "TARGET=server" simple-udp-app
    docker run --rm -it --net=host -e "TARGET=client" simple-udp-app
