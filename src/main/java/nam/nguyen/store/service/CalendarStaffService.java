package nam.nguyen.store.service;

import nam.nguyen.store.model.CalendarStaff;
import nam.nguyen.store.repository.CalendarStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CalendarStaffService {
    @Autowired
    private CalendarStaffRepository calendarStaffRepository;


    public List<CalendarStaff> getAllCalendarStaff() {
        return calendarStaffRepository.findAll();
    }
    public List<CalendarStaff> getAllCalendarUser(Integer id) {
        return calendarStaffRepository.findByUserId(id);
    }
    public CalendarStaff getCalendarStaffById(Integer id) {
        return calendarStaffRepository.findById(id).orElse(null);
    }


    public CalendarStaff addCalendarStaff(CalendarStaff calendarStaff) {
       return calendarStaffRepository.save(calendarStaff);
    }


    public void updateCalendarStaff(CalendarStaff calendarStaff) {
        // Kiểm tra xem calendarStaff có tồn tại trong database không
        CalendarStaff existingCalendarStaff = calendarStaffRepository.findById(calendarStaff.getId()).orElse(null);
        if (existingCalendarStaff != null) {
            // Cập nhật thông tin của existingCalendarStaff với thông tin mới từ calendarStaff
            existingCalendarStaff.setTitle(calendarStaff.getTitle());
            existingCalendarStaff.setStart(calendarStaff.getStart());
            existingCalendarStaff.setEnd(calendarStaff.getEnd());
            existingCalendarStaff.setUser(calendarStaff.getUser());

            // Lưu lại vào database
            calendarStaffRepository.save(existingCalendarStaff);
        }
    }


    public void deleteCalendarStaff(Integer id) {
        calendarStaffRepository.deleteById(id);
    }
}
