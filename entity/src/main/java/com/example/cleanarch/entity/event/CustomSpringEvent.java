package com.example.cleanarch.entity.event;

import com.example.cleanarch.entity.enumerable.Event;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CustomSpringEvent<T> extends ApplicationEvent {
  private final Event event;

  public CustomSpringEvent(Event event, T source) {
    super(source);
    this.event = event;
  }

  @Override
  public T getSource() {
    return (T) super.getSource();
  }
}