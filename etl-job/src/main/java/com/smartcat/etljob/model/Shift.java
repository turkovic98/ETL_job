package com.smartcat.etljob.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "shifts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Shift {
	@Id
	@Column(name = "shift_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shift_generator")
	@SequenceGenerator(name="shift_generator", sequenceName = "shift_seq", initialValue = 10)
	private long Id;

	@Column(name = "shift_date")
	@NotNull
	private LocalDate shiftDate;

	@Column(name = "shift_start")
	@NotNull
	private Timestamp shiftStart;

	@Column(name = "shift_finish")
	@NotNull
	private Timestamp shiftFinish;

	@Column(name = "shift_cost")
	private double shiftCost;


	public Shift(LocalDate shiftDate, Timestamp shiftStart, Timestamp shiftFinish, double shiftCost) {
		this.shiftDate = shiftDate;
		this.shiftStart = shiftStart;
		this.shiftFinish = shiftFinish;
		this.shiftCost = shiftCost;
	}
}
