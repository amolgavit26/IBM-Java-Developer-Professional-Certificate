class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    public String sound() {
        return "Woof";
    }
}

class Cat extends Animal {
    public Cat(String name){
        super(name);
    }
    public String sound() {
        return "Meow";
    }
}

class Cow extends Animal {
    public Cow(String name){
        super(name);
    }
    public String sound() {
        return "Moo";
    }
}