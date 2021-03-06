package com.smartcat.etljob.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "kpis", uniqueConstraints = { @UniqueConstraint(columnNames = {"kpi_name", "kpi_date"}, name = "kpi_unique_constraint")})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KPI {
	@Id
	@Column(name = "kpi_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kpi_generator")
	@SequenceGenerator(name="kpi_generator", sequenceName = "kpi_seq", initialValue = 10)
	private long Id;

	@Column(name = "kpi_name")
	@NotNull
	private String kpiName;

	@Column(name = "kpi_date")
	@NotNull
	private LocalDate kpiDate;

	@Column(name = "kpi_value", precision = 8, scale = 2)
	@NotNull
	private double kpiValue;

	public KPI(String kpiName, LocalDate kpiDate, double kpiValue) {
		this.kpiName = kpiName;
		this.kpiDate = kpiDate;
		this.kpiValue = kpiValue;
	}
}
