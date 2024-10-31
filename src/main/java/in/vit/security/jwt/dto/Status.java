package in.vit.security.jwt.dto;
import lombok.Getter;

@Getter
public enum Status {
    CREATED, ORDERED, CANCELED, PENDING, SHIPPED, DELIVERED;
}