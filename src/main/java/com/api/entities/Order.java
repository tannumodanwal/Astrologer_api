package com.api.entities;



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
import jakarta.validation.constraints.PositiveOrZero;



@Entity
@Table(name = "Orders_details")
public class Order {
  
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private int userId;

	
	@NotNull(message = "Astrologer cannot be null")
    private long astrologerId;

    @NotNull(message = "Order date cannot be null")
    private LocalDateTime orderDate; // Order creation date

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getAstrologerId() {
		return astrologerId;
	}

	public void setAstrologerId(long astrologerId) {
		this.astrologerId = astrologerId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", astrologerId=" + astrologerId + ", orderDate=" + orderDate
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", duration=" + duration + ", status=" + status
				+ ", communicationType=" + communicationType + "]";
	}

	public Order(Long id, int userId, @NotNull(message = "Astrologer cannot be null") long astrologerId,
			@NotNull(message = "Order date cannot be null") LocalDateTime orderDate, LocalTime startTime,
			LocalTime endTime, @PositiveOrZero(message = "Duration must be zero or positive") Long duration,
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

    
    
}
