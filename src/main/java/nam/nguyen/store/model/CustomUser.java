package nam.nguyen.store.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomUser {
    private String username;
    private List<String> role;
}
