package pers.bohan.adService.responsetype;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int err;
    private String msg;
}
