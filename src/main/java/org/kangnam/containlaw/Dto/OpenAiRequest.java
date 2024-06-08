package org.kangnam.containlaw.Dto;

// Nested class to create a proper JSON request for OpenAI API
public class OpenAiRequest {
    public String model = "gpt-4";
    public Message[] messages;

    public OpenAiRequest(String userContent) {
        this.messages = new Message[]{new Message("user", userContent)};
    }

    private static class Message {
        public String role;
        public String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}
