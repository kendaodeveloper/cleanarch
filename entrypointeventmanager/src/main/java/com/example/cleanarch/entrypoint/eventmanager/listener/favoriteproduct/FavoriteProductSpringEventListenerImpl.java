package com.example.cleanarch.entrypoint.eventmanager.listener.favoriteproduct;

import com.example.cleanarch.entity.event.CustomSpringEvent;
import com.example.cleanarch.entity.event.favoriteproduct.FavoriteProductEventRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class FavoriteProductSpringEventListenerImpl {
  @EventListener(condition = "#request.event.name() == 'ADD_USER_FAVORITE_PRODUCT'")
  public void addFavoriteProductAsync(CustomSpringEvent<FavoriteProductEventRequest> request) {
    log.info("ADD_USER_FAVORITE_PRODUCT event was processed");
    // TODO!
  }

  @EventListener(condition = "#request.event.name() == 'REMOVE_USER_FAVORITE_PRODUCT'")
  public void removeFavoriteProductAsync(CustomSpringEvent<FavoriteProductEventRequest> request) {
    log.info("REMOVE_USER_FAVORITE_PRODUCT event was processed");
    // TODO!
  }
}
