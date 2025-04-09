# 65DSD-T1-Sockets - Socket Communication System
Project developed for the Distributed Systems course (65DSD) that implements a client-server communication system using Java sockets.

## 📋 Description

This project demonstrates inter-process communication using TCP/IP sockets, where:
- A central server accepts connections from multiple clients
- Clients can send messages to the server
- The server processes messages and sends responses

## ⚙️ Features

### Server
- Accepts multiple simultaneous client connections
- Manages connections in separate threads
- Processes received messages

### Client
- Establishes connection with the server
- Sends messages to the server
- Receives and displays server responses

## 🚀 How to Run

### Running the Server
```bash
java -jar Servidor.jar

### Running the Client
```bash
java -jar Cliente.jar
