package com.smartcat.etljob.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "breaks")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Break {
	@Id
	@Column(name = "break_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "break_generator")
	@SequenceGenerator(name="break_generator", sequenceName = "break_seq", initialValue = 10)
	private long Id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shift_id", referencedColumnName = "shift_id")
	@NotNull
	private Shift shift;

	@Column(name = "break_start")
	@NotNull
	private Timestamp breakStart;

	@Column(name = "break_finish")
	@NotNull
	private Timestamp breakFinish;

	@Column(name = "is_paid")
	private Boolean isPaid = false;

	public Break(Shift shift, Timestamp breakStart, Timestamp breakFinish, Boolean isPaid) {
		this.shift = shift;
		this.breakStart = breakStart;
		this.breakFinish = breakFinish;
		this.isPaid = isPaid;
	}
}
