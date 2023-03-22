package com.ticket.ticket_selling_system.service;

import com.ticket.ticket_selling_system.model.Show;
import com.ticket.ticket_selling_system.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
    }

    public Show saveShow(Show show) {
        return showRepository.save(show);
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Show getShow(String title) {
        for (Show show : showRepository.findAll()) {
            if (show.getTitle().equals(title)) {
                return show;
            }
        }
        return null;
    }

    public Boolean canSellTickets(Integer id, Integer newSold){
        Optional<Show> show = showRepository.findById(id);
        Show show1 = show.get();
        if(show1.getTicketsSold() + newSold > show1.getMaximumNumberTickets())
            return false;
        return true;
    }

    public Boolean canModifyMaximum(Integer id, Integer newMaximum){
        Optional<Show> show = showRepository.findById(id);
        Show show1 = show.get();
        if(show1.getTicketsSold() > newMaximum){
            return false;
        }
        return true;
    }
    public Optional<Show> getShowById(Integer id){
        return showRepository.findById(id);
    }
    public void delete(Integer id){
        showRepository.deleteById(id);
    }

    public Show modifyTitle(Integer id, String newTitle){
        showRepository.findById(id).map(show ->{
            show.setTitle(newTitle);
            return showRepository.save(show);
        });

        return null;
    }

    public Show modifyArtist(Integer id, String newArtist){
        showRepository.findById(id).map(show ->{
            show.setArtist(newArtist);
            return showRepository.save(show);
        });
        return null;
    }

    public Show modifyGenre(Integer id, String newGenre){
        showRepository.findById(id).map(show ->{
            show.setGenre(newGenre);
            return showRepository.save(show);
        });
        return null;
    }

    public Show modifyDate(Integer id, String newDate){
        showRepository.findById(id).map(show ->{
            show.setDate(newDate);
            return showRepository.save(show);
        });
        return null;
    }

    public Show modifyTime(Integer id, String newTime){
        showRepository.findById(id).map(show ->{
            show.setTime(newTime);
            return showRepository.save(show);
        });
        return null;
    }

    public Show modifyMaximumNumber(Integer id, Integer newMaximum){
        if(canModifyMaximum(id,newMaximum)) {
            showRepository.findById(id).map(show -> {
                show.setMaximumNumberTickets(newMaximum);
                return showRepository.save(show);
            });
        }
        return null;
    }

    public Show modifyTicketsSold(Integer id, Integer newSold){
        showRepository.findById(id).map(show ->{
            show.setTicketsSold(show.getTicketsSold()-newSold);
            return showRepository.save(show);
        });
        return null;
    }

}
