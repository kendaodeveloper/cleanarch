package com.example.cleanarch.gateway.eventmanager.publisher.favoriteproduct;

import com.example.cleanarch.entity.enumerable.Event;
import com.example.cleanarch.entity.event.CustomSpringEvent;
import com.example.cleanarch.entity.event.favoriteproduct.FavoriteProductEventRequest;
import com.example.cleanarch.gateway.base.favoriteproduct.AddFavoriteProductAsyncGateway;
import com.example.cleanarch.gateway.base.favoriteproduct.RemoveFavoriteProductAsyncGateway;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FavoriteProductSpringEventPublisher implements
    AddFavoriteProductAsyncGateway, RemoveFavoriteProductAsyncGateway {
  private ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void addFavoriteProductAsync(FavoriteProductEventRequest request) {
    this.applicationEventPublisher.publishEvent(
        new CustomSpringEvent<>(Event.ADD_USER_FAVORITE_PRODUCT, request)
    );
  }

  @Override
  public void removeFavoriteProductAsync(FavoriteProductEventRequest request) {
    this.applicationEventPublisher.publishEvent(
        new CustomSpringEvent<>(Event.REMOVE_USER_FAVORITE_PRODUCT, request)
    );
  }
}