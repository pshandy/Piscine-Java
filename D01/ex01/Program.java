public class Program {

    public static void main(String[] args) {

        User[] userArray = new User[5];

        userArray[0] = new User("Bob", 0);
        userArray[1] = new User("Tim", 100);
        userArray[2] = new User("Jill", 150);
        userArray[3] = new User("Tom", 20);
        userArray[4] = new User("Jack", 300);

        for (int i = 0; i < 5; i++) {
            System.out.println(userArray[i].getIdentifier());
        }

        UserIdsGenerator generator = UserIdsGenerator.getInstance();
        System.out.println(generator.generateId());
        System.out.println(generator.generateId());

    }
}