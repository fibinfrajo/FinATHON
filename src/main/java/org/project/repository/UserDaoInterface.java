package org.project.repository;

public interface UserDaoInterface {
    public int saveUser(UserModel user);
    public String getPasswordByName(String name);
}
