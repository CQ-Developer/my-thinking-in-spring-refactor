package huhu.io.thinking.in.spring.dependency.model;

import huhu.io.thinking.in.spring.dependency.annotation.Super;

/**
 * extends from user with annotation mark,
 * for annotation lookup in demo.
 *
 * @author huhu
 * @since 2020/12/16
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" + "address='" + address + '\'' + "} " + super.toString();
    }

}
