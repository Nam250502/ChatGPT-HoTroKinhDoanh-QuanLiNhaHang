package nam.nguyen.store.service;

import nam.nguyen.store.model.FeedBack;
import nam.nguyen.store.repository.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FeedBackService {
    @Autowired
    private FeedBackRepository feedBackRepository;
    public FeedBack addFeedBack(FeedBack feedBack){
        return feedBackRepository.save(feedBack);
    }
    public Page<FeedBack> getAllFeedBack(Pageable pageable){
        return feedBackRepository.findAll(pageable);
    }
    public List<FeedBack> getAllFeedBack1(){
        return feedBackRepository.findAll();
    }
    public  List<FeedBack> getNewFeedBack(){
        return  feedBackRepository.findFeedBackNew();
    }

    public Page<FeedBack> getFeedBackByStar(Integer star, Pageable pageable) {
        return feedBackRepository.getFeedBackByStars(star, pageable);
    }
}
