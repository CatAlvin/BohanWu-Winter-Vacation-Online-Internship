package pers.bohan.authService.responsetype;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjectResponse<T> {
    private T data;
}
