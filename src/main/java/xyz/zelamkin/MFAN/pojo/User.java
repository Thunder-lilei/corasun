package xyz.zelamkin.MFAN.pojo;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

public class User {
    private Integer id;

    private Integer dept_id;

    private String username;

	private String password;

    private String sex;

    private String bank_account;

    private Date birthday;

    private String mail;

    private String tel;

    private String address;

    private Date create_time;

    private Date update_time;

    private String role;

    private Integer status;

    private String remark;

    private byte[] image;

    public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] inputStream) {
        this.image = inputStream;
    }

    @Override
	public String toString() {
		return "User [id=" + id + ", deptId=" + dept_id + ", username=" + username + ", password=" + password + ", sex="
				+ sex + ", bankAccount=" + bank_account + ", birthday=" + birthday + ", mail=" + mail + ", tel=" + tel
				+ ", address=" + address + ", createTime=" + create_time + ", updateTime=" + update_time + ", role="
				+ role + ", status=" + status + ", remark=" + remark + ", image=" + Arrays.toString(image) + "]";
	}

}