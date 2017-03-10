package com.loh.tally.ui.base.dagger.module;

import android.content.Context;
import android.content.res.Resources;

import com.google.firebase.auth.FirebaseAuth;
import com.loh.tally.TallyApp;
import com.loh.tally.domain.authentication.AuthenticationManager;
import com.loh.tally.domain.authentication.AuthenticationManagerImpl;
import com.loh.tally.ui.base.dagger.scope.ApplicationScope;
import com.squareup.otto.Bus;

import dagger.Module;
import dagger.Provides;

/**
 * File: ApplicationModule.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Details how all components scoped by {@link ApplicationScope} are provided.
 */
@Module
public class ApplicationModule {

    private final TallyApp application;

    public ApplicationModule(TallyApp application) {
        this.application = application;
    }

    @Provides
    @ApplicationScope
    public Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @ApplicationScope
    public Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    @ApplicationScope
    public Bus provideEventBus() {
        return new Bus();
    }

    @Provides
    @ApplicationScope
    public FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @ApplicationScope
    public AuthenticationManager provideAuthenticationManager(FirebaseAuth firebaseAuth, Bus bus) {
        return new AuthenticationManagerImpl(firebaseAuth, bus);
    }
}
