package com.example.cleanarch.entity.event.favoriteproduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteProductEventRequest {
  private Long userId;
  private Long productId;
}
