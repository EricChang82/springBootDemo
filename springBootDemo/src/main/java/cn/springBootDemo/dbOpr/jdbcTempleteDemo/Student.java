package cn.springBootDemo.dbOpr.jdbcTempleteDemo;

import lombok.ToString;

@ToString
public class Student {
   private Integer age;
   private String name;
   private Integer id;
/**
 * @return Returns the age.
 */
public Integer getAge() {
    return age;
}
/**
 * @param age The age to set.
 */
public void setAge(Integer age) {
    this.age = age;
}
/**
 * @return Returns the name.
 */
public String getName() {
    return name;
}
/**
 * @param name The name to set.
 */
public void setName(String name) {
    this.name = name;
}
/**
 * @return Returns the id.
 */
public Integer getId() {
    return id;
}
/**
 * @param id The id to set.
 */
public void setId(Integer id) {
    this.id = id;
}
}