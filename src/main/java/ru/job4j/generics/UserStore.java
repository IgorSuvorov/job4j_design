package ru.job4j.generics;

public class UserStore implements Store<User> {

    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        boolean rsl = false;
        if (store.replace(id, model)) {
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        if (store.delete(id)) {
            rsl = true;
        }
        return rsl;
    }

    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}