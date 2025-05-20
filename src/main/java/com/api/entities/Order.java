<<<<<<< HEAD
package com.api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.api.enums.CommunicationType;
import com.api.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "Orders") 
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message="user field must be valid")
     private int userId;

	
    private int astrologerId;

    @NotNull(message = "field Birth Date must be valid")
    private LocalDate orderDate; // Order creation date

    private LocalTime startTime; // Call/chat start time

    private LocalTime endTime; // Call/chat end time

    @PositiveOrZero(message = "Duration must be zero or positive")
    @Column(nullable = false)
    private Long duration; 

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Order status cannot be null")
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Communication type cannot be null")
    private CommunicationType communicationType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAstrologerId() {
		return astrologerId;
	}

	public void setAstrologerId(int astrologerId) {
		this.astrologerId = astrologerId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public CommunicationType getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(CommunicationType communicationType) {
		this.communicationType = communicationType;
	}

	public Order(int id, int userId, @NotNull(message = "Astrologer cannot be null") int astrologerId,
			@NotNull(message = "Order date cannot be null") LocalDate orderDate, LocalTime startTime, LocalTime endTime,
			@PositiveOrZero(message = "Duration must be zero or positive") Long duration,
			@NotNull(message = "Order status cannot be null") OrderStatus status,
			@NotNull(message = "Communication type cannot be null") CommunicationType communicationType) {
		super();
		this.id = id;
		this.userId = userId;
		this.astrologerId = astrologerId;
		this.orderDate = orderDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.status = status;
		this.communicationType = communicationType;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", astrologerId=" + astrologerId + ", orderDate=" + orderDate
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", duration=" + duration + ", status=" + status
				+ ", communicationType=" + communicationType + "]";
	}

    
    
}
=======
package com.api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//import org.antlr.v4.runtime.misc.NotNull;

import com.api.enums.CommunicationType;
import com.api.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "Orders") 
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@NotNull(message="user field must be valid")
	private int userId;

	
    private int astrologerId;

    //@NotNull(message = "Order date cannot be null")
    @NotNull(message = "field Birth Date must be valid")
   // @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of Birth must be in yyyy-MM-dd format")
    private LocalDate orderDate; // Order creation date

    private LocalTime startTime; // Call/chat start time

    private LocalTime endTime; // Call/chat end time

   // @PositiveOrZero(message = "Duration must be zero or positive")
    @Column(nullable = false)
    private int duration=0; 
    
    private double amountPaid;
    private double amountUsed;
    private double ratePerMinute;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Order status cannot be null")
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Communication type cannot be null")
    private CommunicationType communicationType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAstrologerId() {
		return astrologerId;
	}

	public void setAstrologerId(int astrologerId) {
		this.astrologerId = astrologerId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public double getAmountUsed() {
		return amountUsed;
	}

	public void setAmountUsed(double amountUsed) {
		this.amountUsed = amountUsed;
	}

	public double getRatePerMinute() {
		return ratePerMinute;
	}

	public void setRatePerMinute(double ratePerMinute) {
		this.ratePerMinute = ratePerMinute;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public CommunicationType getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(CommunicationType communicationType) {
		this.communicationType = communicationType;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", astrologerId=" + astrologerId + ", orderDate=" + orderDate
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", duration=" + duration + ", amountPaid="
				+ amountPaid + ", amountUsed=" + amountUsed + ", ratePerMinute=" + ratePerMinute + ", status=" + status
				+ ", communicationType=" + communicationType + "]";
	}

	public Order(int id, @NotNull(message = "user field must be valid") int userId, int astrologerId,
			@NotNull(message = "field Birth Date must be valid") LocalDate orderDate, LocalTime startTime,
			LocalTime endTime, int duration, double amountPaid, double amountUsed, double ratePerMinute,
			@NotNull(message = "Order status cannot be null") OrderStatus status,
			@NotNull(message = "Communication type cannot be null") CommunicationType communicationType) {
		super();
		this.id = id;
		this.userId = userId;
		this.astrologerId = astrologerId;
		this.orderDate = orderDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.amountPaid = amountPaid;
		this.amountUsed = amountUsed;
		this.ratePerMinute = ratePerMinute;
		this.status = status;
		this.communicationType = communicationType;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	
    
}
>>>>>>> 6565aec (Updated the file with latest changes)
