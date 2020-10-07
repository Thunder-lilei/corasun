package xyz.zelamkin.MFAN.pojo;

public class Department {
    private Integer id;

    private String dept_name;

    private String dept_info;

    public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDept_info() {
		return dept_info;
	}

	public void setDept_info(String dept_info) {
		this.dept_info = dept_info;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}