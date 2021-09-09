/*
 *   Copyright 2020-2021 Leon Latsch
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package dev.leonlatsch.photok.main.navigation

import androidx.navigation.NavController
import dev.leonlatsch.photok.NavGraphDirections
import javax.inject.Inject

class MainNavigator @Inject constructor() {

    fun navigate(navigationEvent: NavigationEvent, navController: NavController) {
        when (navigationEvent) {
            is NavigationEvent.OnBoarding -> navigateOnBoarding(navController)
            is NavigationEvent.Setup -> navigateSetup(navController)
            is NavigationEvent.Unlock -> navigateUnlock(navController)
        }
    }

    private fun navigateOnBoarding(navController: NavController) {
        navController.navigate(NavGraphDirections.actionGlobalOnBoardingFragment())
    }

    private fun navigateSetup(navController: NavController) {
        navController.navigate(NavGraphDirections.actionGlobalSetupFragment())
    }

    private fun navigateUnlock(navController: NavController) {
        navController.navigate(NavGraphDirections.actionGlobalUnlockFragment())
    }
}