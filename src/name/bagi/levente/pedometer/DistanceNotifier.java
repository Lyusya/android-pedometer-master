/*
 *
 */

package name.bagi.levente.pedometer;


/**
 * Расчет и отображение пройденного расстояния
 */
public class DistanceNotifier implements StepListener {

    public interface Listener {
        public void valueChanged(float value);
        public void passValue();
    }
    private Listener mListener;
    
    float mDistance = 0;
    
    PedometerSettings mSettings;
    Utils mUtils;
    
    boolean mIsMetric;
    float mStepLength;

    public DistanceNotifier(Listener listener, PedometerSettings settings, Utils utils) {
        mListener = listener;
        mUtils = utils;
        mSettings = settings;
        reloadSettings();
    }
    public void setDistance(float distance) {
        mDistance = distance;
        notifyListener();
    }
    
    public void reloadSettings() {
        mIsMetric = mSettings.isMetric();
        mStepLength = mSettings.getStepLength();
        notifyListener();
    }
    
    public void onStep() {
            mDistance += (float)(//км
                mStepLength // см
                / 100000.0); // см/км
        
        notifyListener();
    }
    
    private void notifyListener() {
        mListener.valueChanged(mDistance);
    }
    
    public void passValue() {
        // Обратный звонок из StepListener - Не реализовано
    }


}

