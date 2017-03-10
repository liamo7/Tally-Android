package com.loh.tally.ui.base.dagger.component;

import android.content.Context;
import android.content.res.Resources;

import com.loh.tally.TallyApp;
import com.loh.tally.ui.base.dagger.module.ApplicationModule;
import com.loh.tally.ui.base.dagger.module.ViewModule;
import com.loh.tally.ui.base.dagger.scope.ApplicationScope;
import com.squareup.otto.Bus;

import dagger.Component;

/**
 * File: ApplicationComponent.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Components can be viewed as the injectors and act as a bridge between @Inject and @Module.
 * The component is constrained to be a singleton to only provide one instance per application.
 * Dagger does not expose types from modules unless explicitly told to do so. This is why context is
 * made available.
 */
@ApplicationScope
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(TallyApp application);

    ViewComponent plusViewComponent(ViewModule module);

    // expose dependencies
    Context context();

    Resources resources();

    Bus bus();

}
