# Proyecto Cliente-Servidor en Java

Este repositorio contiene una implementación sencilla de una arquitectura cliente-servidor utilizando sockets en Java. El servidor está diseñado para ser multihilo: por cada cliente que se conecta, se crea una instancia de la clase `HiloEscuchador`, lo que permite atender múltiples clientes de forma simultánea.

## Estructura

El proyecto está dividido en dos partes:

- `servidor/`: contiene las clases del servidor (`Servidor.java` y `HiloEscuchador.java`)
- `cliente/`: contiene la clase del cliente (`Cliente.java`)

## ¿Cómo funciona?

1. El servidor se pone en escucha en un puerto específico.
2. Cada vez que un cliente se conecta, el servidor crea un nuevo hilo usando la clase `HiloEscuchador`.
3. Cada `HiloEscuchador` se encarga de gestionar la comunicación con un cliente concreto, de forma independiente al resto.
4. El cliente se conecta al servidor y puede enviar o recibir mensajes a través del socket.

## Requisitos

- Java JDK 8 o superior
- Consola o terminal para compilar y ejecutar los archivos `.java`

## Compilación y ejecución

1. **Compilar todos los archivos:**

   ```bash
   javac servidor/Servidor.java servidor/HiloEscuchador.java cliente/Cliente.java
