package org.project.repository;

public interface UserDaoInterface {
    public int saveUser(UserModel user);
    public UserModel getUserByName(String name);
}
