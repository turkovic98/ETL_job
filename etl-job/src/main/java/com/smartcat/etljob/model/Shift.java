package com.smartcat.etljob.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shifts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Shift {
	@Id
	@Column(name = "shift_id", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "shift_date")
	@NotNull
	private LocalDate shiftDate;

	@Column(name = "shift_start")
	@NotNull
	private Timestamp shiftStart;

	@Column(name = "shift_finish")
	@NotNull
	private Timestamp shiftFinish;

	@Column(name = "shift_cost", precision = 13, scale = 4)
	private double shiftCost;

	@JsonManagedReference
	@OneToMany(mappedBy="shift", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Break> breaks = new ArrayList<>();

	@JsonManagedReference
	@OneToMany(mappedBy="shift", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Allowance> allowances = new ArrayList<>();

	@JsonManagedReference
	@OneToMany(mappedBy="shift", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AwardInterpretation> awardInterpretations = new ArrayList<>();

	public Shift(UUID id, LocalDate shiftDate, Timestamp shiftStart, Timestamp shiftFinish, double shiftCost) {
		this.id = id;
		this.shiftDate = shiftDate;
		this.shiftStart = shiftStart;
		this.shiftFinish = shiftFinish;
		this.shiftCost = shiftCost;
	}

	public Shift(LocalDate shiftDate, Timestamp shiftStart, Timestamp shiftFinish, double shiftCost, List<Break> breaks, List<Allowance> allowances, List<AwardInterpretation> awardInterpretations) {
		this.shiftDate = shiftDate;
		this.shiftStart = shiftStart;
		this.shiftFinish = shiftFinish;
		this.shiftCost = shiftCost;
		this.breaks = breaks;
		this.allowances = allowances;
		this.awardInterpretations = awardInterpretations;
	}
}
