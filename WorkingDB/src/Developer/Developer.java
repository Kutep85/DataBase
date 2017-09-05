package Developer;

public class Developer {
	
	private Integer id;
	private String name;
	private String surname;
	private String project;
	private String company;
	private Integer salary;

	public Developer() {
		// TODO Auto-generated constructor stub
	}
	
	public Developer(String name, String surname, String project, String company, Integer salary) {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	public String toString() {
		String str = "Name Developer:  " + name + 
				"\nSurname Developer:  " + surname +
				"\nProject:  " + project +
				"\nCompany:  " + company + 
				"\nSalary:  " + salary;
		return str;
	}

}
