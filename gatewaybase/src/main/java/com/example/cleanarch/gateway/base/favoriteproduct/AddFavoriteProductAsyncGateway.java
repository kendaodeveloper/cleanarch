package com.example.cleanarch.gateway.base.favoriteproduct;

import com.example.cleanarch.entity.event.favoriteproduct.FavoriteProductEventRequest;

public interface AddFavoriteProductAsyncGateway {
  void addFavoriteProductAsync(FavoriteProductEventRequest request);
}
