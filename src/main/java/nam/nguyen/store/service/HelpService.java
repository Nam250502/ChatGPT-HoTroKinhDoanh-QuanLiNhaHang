package nam.nguyen.store.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HelpService {
    private List<String> temporaryMessageList = new ArrayList<>();

    public void addMessage(String message) {
        temporaryMessageList.add(message);
    }

    public List<String> getAllMessages() {
        return temporaryMessageList;
    }

    public void removeMessage(String message) {
        temporaryMessageList.remove(message);
    }

    // Các phương thức khác
}
