package com.smartcat.etljob.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "breaks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Break {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "break_id", updatable = false, nullable = false)
	private UUID id;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="shift_id", nullable=false)
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

	public Break(Shift shift, Timestamp breakStart, Timestamp breakFinish) {
		this(shift, breakStart, breakFinish, false);
	}

	@Override
	public String toString() {
		return "\nBreak{" +
			       "id=" + id +
			       ", breakStart=" + breakStart +
			       ", breakFinish=" + breakFinish +
			       ", isPaid=" + isPaid +
			       '}';
	}
}
