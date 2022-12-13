package ru.job4j.generics;

public class RoleStore implements Store<Role> {

    private final Store<Role> store = new MemStore<>();

    @Override
    public void add(Role model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
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
    public Role findById(String id) {
        return store.findById(id);
    }
}