/*
 * Copyright (C) 2009 The Android Open Source Project
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

import java.util.List;

import org.comu.homescreen.settings.LauncherSettings;

import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ContentValues;
import android.view.View;
import android.view.ViewGroup;

/**
 * Represents a widget, which just contains an identifier.
 */
class LauncherAppWidgetInfo extends ItemInfo implements ItemInfo.ItemPackage {

    /**
     * Identifier for this widget when talking with
     * {@link android.appwidget.AppWidgetManager} for updates.
     */
    int appWidgetId;

    /**
     * View that holds this widget after it's been created.  This view isn't created
     * until Launcher knows it's needed.
     */
    AppWidgetHostView hostView = null;

    LauncherAppWidgetInfo(int appWidgetId) {
        itemType = LauncherSettings.Favorites.ITEM_TYPE_APPWIDGET;
        this.appWidgetId = appWidgetId;
    }

    @Override
    void onAddToDatabase(ContentValues values) {
        super.onAddToDatabase(values);
        values.put(LauncherSettings.Favorites.APPWIDGET_ID, appWidgetId);
    }

    @Override
    public String toString() {
        return "AppWidget(id=" + Integer.toString(appWidgetId) + ")";
    }


    @Override
    void unbind() {
        super.unbind();
        hostView = null;
    }

    private static final int ACTION_RESIZE = 1;

	@Override
	public void executeAction(EditAction action, View view, Launcher launcher) {
		switch(action.getId()) {
			case ACTION_DELETE: {
				((ViewGroup) hostView.getParent()).removeView(hostView);
				launcher.removeAppWidget(this);
				launcher.getModel().deleteItemFromDatabase(launcher, this);
			} break;
			case ACTION_RESIZE: {
				launcher.editWidget(view);
			} break;
            default: super.executeAction(action, view, launcher);
		}
	}

	@Override
	public List<EditAction> getAvailableActions(View view, Launcher launcher) {
		List<EditAction> result = super.getAvailableActions(view, launcher);

		result.add(new EditAction(ACTION_RESIZE,
				android.R.drawable.ic_menu_crop,
				R.string.menu_resize));
        addAppInfoAction(view, result, launcher);
        addMarketActions(view, result, launcher);
		return result;
	}

    @Override
    public String getPackageName(Launcher launcher)
    {
        final AppWidgetProviderInfo appWidgetInfo = hostView.getAppWidgetInfo();
        if (appWidgetInfo != null && appWidgetInfo.provider != null)
        {
            return appWidgetInfo.provider.getPackageName();
        }
        return null;
    }
}
