package com.smartcat.etljob.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "kpis")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class KPI {
	@Id
	@Column(name = "kpi_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kpi_generator")
	@SequenceGenerator(name="kpi_generator", sequenceName = "kpi_seq", initialValue = 10)
	private long Id;

	@Column(name = "kpi_name", unique = true)
	@NotNull
	private String kpiName;

	@Column(name = "kpi_date", unique = true)
	@NotNull
	private LocalDate kpiDate;

	@Column(name = "kpi_value")
	@NotNull
	private double kpiValue;

	public KPI(String kpiName, LocalDate kpiDate, double kpiValue) {
		this.kpiName = kpiName;
		this.kpiDate = kpiDate;
		this.kpiValue = kpiValue;
	}
}
