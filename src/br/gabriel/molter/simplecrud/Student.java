package br.gabriel.molter.simplecrud;


/**
 * POJO de Studante para receber os dados do DB
 * 
 * @author Gabriel
 *
 */
public class Student {
	long id;
	String name;
	String grade;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}