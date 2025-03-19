# Roel Hotel - AI Assistant - backend

The backend part of a fullstack simple hotel booking management system with AI assistant that can interact with booking data. For educational/experimental purpose to show case the usage of Langchain4J in interacting with java functions through its LLM tooling capabilities.

## Tech stack

Frontend: React, Javascript, Typescript

Backend: Java, Spring Boot, MySQL, AWS S3 bucket, JWT token, Langchain4j, Open AI API

## Feature

common: login, register, find available rooms for certain dates

guest: book a room, view bookings, cancel a booking, find a booking, view/edit profile

admin: add/edit room, manage bookings 

The AI asistant currently can find and cancel a booking for a guest. 

## Steps to Run the Backend

1. Clone the project
2. Configure the application.properties file with:
   - A local or remote MySQL connection. Create a new database instance (without tables).
   - Create or use an existing AWS S3's access key, secret key and a bucket.
   - Create or use existing Open AI Key on langchain4j.open-ai.chat-model.api-key and langchain4j.open-ai.embedding-model.api-key. You can use "demo" as key for free provided by LangChain4j but i haven't try.
3. Run the RoelHotelApplication with Maven.
4. Use API tool like Postman to try the API endpoints. The AI chat functionality required an authorized bearer token (users need to be registered and loggedin first)
   ````
   ```
   endpoint: POST localhost:4040/auth/register
   body:
   {
    "email":"guest@roelhotel.com",
    "password":"guest",
    "phoneNumber":"12345678",
    "name":"Guest 1",
    "role":"USER"
   }

   endpoint: POST localhost:4040/auth/login
   body:
   {
    "email":"guest@roelhotel.com",
    "password":"guest"
   }

   endpoint: GET localhost:4040/chat?chatid=1&message="could you please get booking details of 9AZ951P9DQ".
   Auth type: bearer token from the login step
  ```
  ````
## Important Parts for the LangChain4J

1. Declare dependencies (file: pom.xml)
   ```xml
   <!-- LangChain4j dependencies -->
		<dependency>
			<groupId>dev.langchain4j</groupId>
			<artifactId>langchain4j-spring-boot-starter</artifactId>
			<version>${langchain4j.version}</version>
		</dependency>
		<dependency>
			<groupId>dev.langchain4j</groupId>
			<artifactId>langchain4j-open-ai-spring-boot-starter</artifactId>
			<version>${langchain4j.version}</version>
		</dependency>
		<dependency>
			<groupId>dev.langchain4j</groupId>
			<artifactId>langchain4j-embeddings-all-minilm-l6-v2</artifactId>
			<version>${langchain4j.version}</version>
		</dependency>
		<dependency>
			<groupId>dev.langchain4j</groupId>
			<artifactId>langchain4j-reactor</artifactId>
			<version>${langchain4j.version}</version>
		</dependency>
   ```
2. Declare an AI service for the role it is playing (file: LangChain4jAssistant.java)
   ```java
   @AiService
    public interface LangChain4jAssistant {
    
        @SystemMessage("""
                You are a customer chat support agent of a Hotel named "Roel Hotel".
                Respond in a friendly, helpful, and joyful manner.
                You are interacting with customers through an online chat system.
                Before providing information about a booking or cancelling a booking.
                Check the message history for this information before asking the user.
                Before changing a booking, you MUST ensure it is permitted by the terms.
                If there is a charge for the change, you MUST ask the user to consent before proceeding.
                Use the provided functions to fetch booking details and cancel bookings.
                Today is {{current_date}}.
                """)
        String chat(@MemoryId String chatId, @UserMessage String userMessage);
    }
   ```
3. Define LangChain4j configurations like chatMemoryProvider (important so that the asistant can remember previous messages), embeddingStore, contentRetriever (file: LangChain4jConfig.java)
4. Define LangChain4j tools. This is where the LangChain4J can find the available tools/functions to be called based on chat interaction. (file: LangChain4jTools.java)
5. Publish the API endpoint for sending the chat message (file: ChatController.java)



## References and Inpirations 

1. https://docs.langchain4j.dev/tutorials/spring-boot-integration/<br/>
2. https://github.com/marcushellberg<br/>
3. https://github.com/achalise<br/>
4. https://github.com/phegondev<br/>

## Screenshots

![Screenshot 2025-03-17 at 12 40 26](https://github.com/user-attachments/assets/050f43b8-cda3-437b-9f41-0e53dfe9814c)
