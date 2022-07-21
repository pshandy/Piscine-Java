public class UserArrayList implements UserList {

    private User[] array = new User[10];
    private Integer size = 0;

    @Override
    public void addUser(User user) {
        if (size == array.length)
            resize();
        array[size++] = user;
    }

    @Override
    public User getUserById(Integer id) {
        for (int i = 0; i < size; i++)
            if (array[i].getIdentifier().equals(id))
                return (array[i]);
        throw new UserNotFoundException("User is not found!");
    }

    @Override
    public User getUserByIndex(Integer index) {
        if (index >= 0 && index < size && array[index] != null)
            return (array[index]);
        throw new UserNotFoundException("User is not found!");
    }

    @Override
    public Integer size() {
        return (size);
    }

    private void resize() {
        User[] newArray = new User[array.length + array.length / 2];
        for (Integer i = 0; i < array.length; i++)
            newArray[i] = array[i];
        array = newArray;
    }
    
}
