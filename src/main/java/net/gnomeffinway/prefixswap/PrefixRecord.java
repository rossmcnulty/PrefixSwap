package net.gnomeffinway.prefixswap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;

@Entity
@Table(name="ps_log")
public class PrefixRecord {
	@Id
	@Column(name="id")
	private int id;

	@NotNull
	@Column(name="prefix")
	private String prefix;

	@NotEmpty
	@Column(name="target")
	private String target;

	@NotEmpty
	@Column(name="desc_short")
	private String descShort;

	@NotEmpty
	@Column(name="desc_long")
	private String descLong;

	@NotNull
	@Column(name="time")
	private long time;

	@NotNull
	@Column(name="hidden")
	private boolean hidden;

	@Enumerated
	@Column(name="state")
	private PrefixState state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getDescShort() {
		return descShort;
	}

	public void setDescShort(String descShort) {
		this.descShort = descShort;
	}

	public String getDescLong() {
		return descLong;
	}

	public void setDescLong(String descLong) {
		this.descLong = descLong;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isHidden() {
		return hidden;
	}


	public PrefixState getState() {
		return state;
	}

	public void setState(PrefixState state) {
		this.state = state;
	}
}
