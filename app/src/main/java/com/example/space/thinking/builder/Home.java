package com.example.space.thinking.builder;

public class Home {

    private String location;

    private String window;

    private String room;

    private String garden;

    private String pool;

    private String other;

    public Home(Builder builder){
        this.location=builder.location;
        this.window=builder.window;
        this.room=builder.room;
        this.garden=builder.garden;
        this.pool=builder.pool;
        this.other=builder.other;

    }

    public static class Builder{
        private String location;

        private String window;

        private String room;

        private String garden;

        private String pool;

        private String other;

        public Builder (String location){
            this.location=location;
        }


        public Builder setWindow(String window) {
            this.window = window;
            return this;
        }

        public Builder setRoom(String room) {
            this.room = room;
            return this;
        }

        public Builder setGarden(String garden) {
            this.garden = garden;
            return this;
        }

        public Builder setPool(String pool) {
            this.pool = pool;
            return this;
        }

        public Builder setOther(String other) {
            this.other = other;
            return this;
        }

        public Home build(){
            return new Home(this);
        }
    }
}
/**
 * public class Computer {
    private String cpu;
    private String screen;
    private String memory;
    private String mainboard;
    public Computer(String cpu, String screen, String memory, String mainboard) {
        this.cpu = cpu;
        this.screen = screen;
        this.memory = memory;
        this.mainboard = mainboard;
    }
}
public class NewComputer {
    private String cpu;
    private String screen;
    private String memory;
    private String mainboard;
    public NewComputer() {
        throw new RuntimeException(“can’t init”);
    }
    private NewComputer(Builder builder) {
        cpu = builder.cpu;
        screen = builder.screen;
        memory = builder.memory;
        mainboard = builder.mainboard;
    }
    public static final class Builder {
        private String cpu;
        private String screen;
        private String memory;
        private String mainboard;

        public Builder() {}

        public Builder cpu(String val) {
            cpu = val;
            return this;
        }
        public Builder screen(String val) {
            screen = val;
            return this;
        }
        public Builder memory(String val) {
            memory = val;
            return this;
        }
        public Builder mainboard(String val) {
            mainboard = val;
            return this;
        }
        public NewComputer build() {
            return new  NewComputer(this);}
    }
}
**/