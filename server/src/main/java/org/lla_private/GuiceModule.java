package org.lla_private;

import javax.inject.Singleton;

import org.lla_private.service.satzdreher.ISatzDreherService;
import org.lla_private.service.satzdreher.SatzDreherService;

import com.google.inject.Binder;
import com.google.inject.Module;


public class GuiceModule implements Module {
  @Override
  public void configure(Binder binder) {
      binder.bind(ISatzDreherService.class).to(SatzDreherService.class).in(Singleton.class);
  }
}
