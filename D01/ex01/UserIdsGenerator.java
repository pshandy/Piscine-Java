public class UserIdsGenerator {
    private static Integer id = 0;
    private static UserIdsGenerator instance;

    private UserIdsGenerator() {
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null)
            instance = new UserIdsGenerator();
        return (instance);
    }

    public Integer generateId() {
        return (id == Integer.MAX_VALUE ? -1 : ++id);
    }
}
