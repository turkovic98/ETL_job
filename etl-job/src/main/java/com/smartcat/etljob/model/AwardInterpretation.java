package com.smartcat.etljob.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "award_interpretations")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AwardInterpretation {
	@Id
	@Column(name = "award_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "award_generator")
	@SequenceGenerator(name="award_generator", sequenceName = "award_seq", initialValue = 10)
	private long Id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shift_id", referencedColumnName = "shift_id")
	@NotNull
	private Shift shift;

	@Column(name = "award_date")
	@NotNull
	private LocalDate awardDate;

	@Column(name = "award_units")
	@NotNull
	private float awardUnits;

	@Column(name = "award_cost")
	@NotNull
	private double awardCost;

	public AwardInterpretation(Shift shift, LocalDate awardDate, float awardUnits, double awardCost) {
		this.shift = shift;
		this.awardDate = awardDate;
		this.awardUnits = awardUnits;
		this.awardCost = awardCost;
	}
}
