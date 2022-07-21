public class Program {

    public static void main(String[] args) {

        UserList userList = new UserArrayList();
        
        System.out.println("Number of users: " + userList.size());

        userList.addUser(new User("Bob T.", 0));
        userList.addUser(new User("Bob G.", 0));
        userList.addUser(new User("Bob M.", 0));
        userList.addUser(new User("Bob L.", 0));

        System.out.println("Number of users: " + userList.size());

        System.out.println("Id 3: " + userList.getUserById(3));
        System.out.println("Id 1: " + userList.getUserById(1));

        System.out.println("Index 3: " + userList.getUserByIndex(3));
        System.out.println("Index 1: " + userList.getUserByIndex(1));

    }
}