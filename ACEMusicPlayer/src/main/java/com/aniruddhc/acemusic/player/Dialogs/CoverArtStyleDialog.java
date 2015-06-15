/*
 * Copyright (C) 2014 Saravan Pantham
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aniruddhc.acemusic.player.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.aniruddhc.acemusic.player.R;

public class CoverArtStyleDialog extends DialogFragment {

    private Activity parentActivity;
    private int selectedThemeIndex;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        parentActivity = getActivity();

        final SharedPreferences sharedPreferences = parentActivity.
                getSharedPreferences("com.aniruddhc.acemusic.player", Context.MODE_PRIVATE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Check which style is currently selected and set the appropriate flag.
        if (sharedPreferences.getString("COVER_ART_STYLE", "CARD_STYLE").equals("CARD_STYLE")) {
            selectedThemeIndex = 0;
        } else {
            selectedThemeIndex = 1;
        }

        //Set the dialog title.
        builder.setTitle(R.string.cover_art_style);
        builder.setSingleChoiceItems(R.array.cover_art_style_choices, selectedThemeIndex, new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {
                    sharedPreferences.edit().putString("COVER_ART_STYLE", "CARD_STYLE").commit();

                } else if (which == 1) {
                    sharedPreferences.edit().putString("COVER_ART_STYLE", "FILL_SCREEN").commit();

                }

                dialog.dismiss();
                getActivity().finish();

            }

        });

        return builder.create();
    }

    @Override
    public void onPause() {
        super.onPause();

        getActivity().finish();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().finish();

    }

}
