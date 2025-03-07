/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.android.systemui.statusbar.policy;

import com.android.systemui.statusbar.policy.KeyguardMonitor.Callback;

public interface KeyguardMonitor extends CallbackController<Callback> {

    boolean isSecure();
    boolean canSkipBouncer();
    boolean isShowing();
    boolean isOccluded();
    boolean isKeyguardFadingAway();
    boolean isKeyguardGoingAway();
    boolean isLaunchTransitionFadingAway();
    long getKeyguardFadingAwayDuration();
    long getKeyguardFadingAwayDelay();
    long calculateGoingToFullShadeDelay();

    /**
     * @return a shortened fading away duration similar to
     * {{@link #getKeyguardFadingAwayDuration()}} which may only span half of the duration, unless
     * we're bypassing
     */
    default long getShortenedFadingAwayDuration() {
        if (isBypassFadingAnimation()) {
            return getKeyguardFadingAwayDuration();
        } else {
            return getKeyguardFadingAwayDuration() / 2;
        }
    }

    default boolean isDeviceInteractive() {
        return false;
    }

    default void setLaunchTransitionFadingAway(boolean b) {
    }

    default void notifyKeyguardGoingAway(boolean b) {
    }

    /**
     * @return {@code true} if the current fading away animation is the fast bypass fading.
     */
    default boolean isBypassFadingAnimation() {
        return false;
    }

    /**
     * Notifies that the Keyguard is fading away with the specified timings.
     * @param delay the precalculated animation delay in milliseconds
     * @param fadeoutDuration the duration of the exit animation, in milliseconds
     * @param isBypassFading is this a fading away animation while bypassing
     */
    default void notifyKeyguardFadingAway(long delay, long fadeoutDuration,
            boolean isBypassFading) {
    }

    default void notifyKeyguardDoneFading() {
    }

    default void notifyKeyguardState(boolean showing, boolean methodSecure, boolean occluded) {
    }

    interface Callback {
        default void onKeyguardShowingChanged() {}
        default void onKeyguardFadingAwayChanged() {}
    }
}
