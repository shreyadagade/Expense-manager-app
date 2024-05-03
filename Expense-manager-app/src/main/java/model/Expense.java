package model;



import lombok.*;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Expense {
    private int id;
    private String remark;
    private int amount;
    private String payment_mode;
    private LocalDateTime time;
}
