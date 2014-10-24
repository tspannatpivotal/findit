package io.pivotal.findit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NameValue")
public class NameValue {

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private String value;
    
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NameValue [id=").append(id).append(", name=")
				.append(name).append(", value=").append(value).append("]");
		return builder.toString();
	}
	public NameValue(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	protected NameValue() {
		super();
	}
	public NameValue(long id, String name, String value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}
}