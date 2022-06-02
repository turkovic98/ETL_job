package com.smartcat.etljob.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "allowances")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Allowance {
	@Id
	@Column(name = "allowance_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allowance_generator")
	@SequenceGenerator(name="allowance_generator", sequenceName = "allowance_seq", initialValue = 10)
	private long Id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shift_id", referencedColumnName = "shift_id")
	@NotNull
	private Shift shift;

	@Column(name = "allowance_value")
	@NotNull
	private float allowanceValue;

	@Column(name = "allowance_cost")
	@NotNull
	private double allowanceCost;

	public Allowance(Shift shift, float allowanceValue, double allowanceCost) {
		this.shift = shift;
		this.allowanceValue = allowanceValue;
		this.allowanceCost = allowanceCost;
	}
}
