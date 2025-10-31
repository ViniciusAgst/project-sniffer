# 🕵️‍♂️ Network Sniffer - Java Spring Boot
http://127.0.0.1:57785/?redirect_uri=vscode%3A%2F%2Fvscode.github-authentication%2Fdid-authenticate%3Fnonce%3D80c6e51fd74a3d64%26windowId%3D1&app_name=Visual%20Studio%20Code
![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.2.2-green?logo=spring&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue)

## Descrição

O **Network Sniffer** é uma aplicação desenvolvida em **Java** utilizando **Spring Boot** que captura pacotes de rede em tempo real, armazena informações relevantes e disponibiliza uma interface web para visualização. É ideal para fins educacionais, monitoramento de redes ou análise de tráfego.

O projeto integra:
- Captura de pacotes via [Pcap4J](https://www.pcap4j.org/)
- Armazenamento de pacotes em lista rotativa para eficiência
- Interface web em tempo real com **Thymeleaf**
- Filtros básicos por protocolo, MAC, IP e porta

---

## 💻 Funcionalidades

- Captura de pacotes Ethernet, IPv4/IPv6, TCP, UDP e ICMP  
- Atualização em tempo real da lista de pacotes na interface web  
- Histórico limitado com lista rotativa (ex.: últimos 10000 pacotes)  
- Visualização detalhada de cada pacote  
- Interface responsiva usando Spring Boot + Thymeleaf
- Banco de dados integrado

---

## 🚀 Tecnologias Utilizadas

- **Java 17**  
- **Spring Boot 3.2.x**  
- **Pcap4J** para captura de pacotes  
- **Thymeleaf** para renderização de páginas HTML  
- **Gradle** como gerenciador de dependências
- **MySQL** como banco de dados

---

## 📦 Instalação e Uso

### Pré-requisitos

- Java 17 ou superior  
- Gradle  
- Biblioteca nativa do **Pcap4J** instalada e configurada  
- Permissões de administrador/root para captura de pacotes em algumas interfaces  

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/network-sniffer-java.git
cd network-sniffer-java
