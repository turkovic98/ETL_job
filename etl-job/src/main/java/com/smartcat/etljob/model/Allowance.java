package com.smartcat.etljob.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "allowances")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Allowance {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "allowance_id", updatable = false, nullable = false)
	private UUID id;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="shift_id", nullable=false)
	private Shift shift;

	@Column(name = "allowance_value")
	@NotNull
	private float allowanceValue;

	@Column(name = "allowance_cost", precision = 13, scale = 4)
	@NotNull
	private double allowanceCost;

	public Allowance(Shift shift, float allowanceValue, double allowanceCost) {
		this.shift = shift;
		this.allowanceValue = allowanceValue;
		this.allowanceCost = allowanceCost;
	}
}
