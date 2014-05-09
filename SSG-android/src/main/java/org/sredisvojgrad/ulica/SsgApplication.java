package org.sredisvojgrad.ulica;

import android.app.Application;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
/**
 * Created by Home on 25.4.2014..
 */
@ReportsCrashes(formKey = "", // will not be used
        mailTo = "arnela06@gmail.com",
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.crash_toast_text)
public class SsgApplication  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // The following line triggers the initialization of ACRA
        ACRA.init(this);
    }


}
