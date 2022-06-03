package com.smartcat.etljob.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "award_interpretations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AwardInterpretation {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "award_id", updatable = false, nullable = false)
	private UUID id;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="shift_id", nullable=false)
	private Shift shift;

	@Column(name = "award_date")
	@NotNull
	private LocalDate awardDate;

	@Column(name = "award_units")
	@NotNull
	private float awardUnits;

	@Column(name = "award_cost", precision = 13, scale = 4)
	@NotNull
	private double awardCost;

	public AwardInterpretation(Shift shift, LocalDate awardDate, float awardUnits, double awardCost) {
		this.shift = shift;
		this.awardDate = awardDate;
		this.awardUnits = awardUnits;
		this.awardCost = awardCost;
	}
}
