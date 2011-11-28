/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.comu.homescreen;

import java.util.ArrayList;

import org.comu.homescreen.settings.LauncherSettings.Favorites;

import android.graphics.Bitmap;
import android.view.View;


/**
 * Represents a folder containing shortcuts or apps.
 */
class FolderInfo extends IconItemInfo {

    /**
     * Whether this folder has been opened
     */
    boolean opened;

    @Override
    public Bitmap getIcon(IconCache iconCache) {
    	if (mIcon == null)
    		return Utilities.createIconBitmap(
    				iconCache.getContext().getResources().getDrawable(R.drawable.ic_launcher_folder),
    				iconCache.getContext());
    	return super.getIcon(iconCache);
    }

    @Override
	public void executeAction(EditAction action, View view, Launcher launcher) {
		switch(action.getId()) {
			case ACTION_DELETE: {
				if (this.container == Favorites.CONTAINER_DRAWER) {
					ArrayList<IconItemInfo> lst = new ArrayList<IconItemInfo>();
					lst.add(this);
					launcher.getAllAppsView().removeApps(lst);
				}
				else
					launcher.removeDesktopItem(this);
				launcher.getModel().deleteItemFromDatabase(launcher, this);
			} break;
			default:
				super.executeAction(action, view, launcher);
		}
	}
}
